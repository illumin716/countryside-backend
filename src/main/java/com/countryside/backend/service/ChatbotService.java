package com.countryside.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.countryside.backend.domain.Place;
import com.countryside.backend.repository.PlaceRepository;
import com.countryside.backend.domain.Faq; // Faq 엔티티 임포트
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity; // 추가: HttpEntity 임포트
import org.springframework.http.HttpHeaders; // 추가: HttpHeaders 임포트
import org.springframework.http.MediaType; // 추가: MediaType 임포트
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; // GPT와 통신할 도구
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional; // Optional 임포트
import java.util.Set;     // <-- 새로 추가할 import
import java.util.HashSet; // <-- 새로 추가할 import
import java.util.stream.Collectors;

@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);

    private final RestTemplate restTemplate; // GPT와 대화할 도구
    private final PlaceRepository placeRepository; // 데이터 저장소
    private final FaqService faqService; // FaqService 주입!


    @Value("${openai.model.name}") // application.properties에서 GPT 모델 이름 가져오기
    private String model;

    @Value("${openai.api.url}") // application.properties에서 OpenAI API 주소 가져오기
    private String apiUrl;

    @Value("${openai.api.key}") // 추가: OpenAI API 키를 주입받기 위한 필드
    private String openaiApiKey;

    public ChatbotService(RestTemplate restTemplate, PlaceRepository placeRepository, FaqService faqService) {
        this.restTemplate = restTemplate;
        this.placeRepository = placeRepository;
        this.faqService = faqService; // 주입받은 FaqService 할당
    }

    public String getChatbotResponse(String userMessage) {
        String lowerCaseMessage = userMessage.toLowerCase();

        // 1. FAQ 데이터베이스에서 매칭되는 질문이 있는지 확인
        Optional<Faq> matchedFaq = faqService.findMatchingFaq(lowerCaseMessage);

        if (matchedFaq.isPresent()) {
            return matchedFaq.get().getAnswer();
        }

        // 2. FAQ에 매칭되는 것이 없으면, 하드코딩된 특정 키워드 로직 확인 (여기 수정!)

        // --- "근처 카페" 로직 개선 ---
        if (lowerCaseMessage.contains("근처 카페") || lowerCaseMessage.contains("카페 추천") || lowerCaseMessage.contains("카페")) {
            // 카테고리에서 '카페'를 포함하는 명소 검색
            List<Place> cafesByCategory = placeRepository.findByCategoryContainingIgnoreCase("카페");
            // 태그에서 '카페'를 포함하는 명소 검색
            List<Place> cafesByTags = placeRepository.findByTagsContainingIgnoreCase("카페");

            // 두 검색 결과 합치고 중복 제거 (Set 사용)
            Set<Place> combinedCafes = new HashSet<>(cafesByCategory);
            combinedCafes.addAll(cafesByTags);


            if (!combinedCafes.isEmpty()) {
                // 주소 정보까지 함께 반환하여 사용자에게 더 구체적인 정보를 제공
                return "찾으시는 카페 정보가 있어요: " + combinedCafes.stream()
                        .map(place -> place.getName() + " (" + place.getAddress() + ")")
                        .collect(Collectors.joining(", ")) + " 등이 있습니다. 더 자세한 정보가 필요하시면 명소 이름을 말씀해주세요.";
            } else {
                return "죄송합니다, 현재 데이터에 등록된 카페 정보가 없습니다.";
            }
        }

        // --- "축제" 로직 개선 ---
        if (lowerCaseMessage.contains("축제") || lowerCaseMessage.contains("오늘 진행 중인 축제") || lowerCaseMessage.contains("페스티벌")) { // 키워드 확장
            // 카테고리에서 '축제'를 포함하는 명소 검색
            List<Place> festivalsByCategory = placeRepository.findByCategoryContainingIgnoreCase("축제");
            // 태그에서 '축제'를 포함하는 명소 검색
            List<Place> festivalsByTags = placeRepository.findByTagsContainingIgnoreCase("축제");

            // 두 검색 결과 합치고 중복 제거
            Set<Place> combinedFestivals = new HashSet<>(festivalsByCategory);
            combinedFestivals.addAll(festivalsByTags);

            if (!combinedFestivals.isEmpty()) {
                // 축제 관련 명소 정보를 주소와 함께 반환
                return "현재 진행 중인 또는 예정된 축제 관련 명소 정보입니다: " + combinedFestivals.stream()
                        .map(place -> place.getName() + " (" + place.getAddress() + ")")
                        .collect(Collectors.joining(", ")) + " 등이 있습니다. 자세한 내용은 해당 명소의 설명을 확인해주세요.";
            } else {
                return "죄송합니다, 현재 데이터에 등록된 축제 정보가 없습니다.";
            }
        }

        // --- 기존 "안녕" 로직은 이제 FaqService를 통해 DB에서 관리되므로 삭제하거나 주석 처리 ---
        // if (lowerCaseMessage.contains("안녕") || lowerCaseMessage.contains("안녕하세요")) {
        //     return "안녕하세요! 저는 지역 명소 안내 챗봇입니다. 무엇을 도와드릴까요?";
        // }

        // 3. 사용자 질문에서 명소 관련 키워드를 찾기
        // 여기서는 간단하게 질문 전체를 키워드로 사용하거나, 특정 명소 이름을 직접 찾아보는 방식 활용
        // 추후에 더 똑똑하게 질문 분석
        List<Place> foundPlaces = placeRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrTagsContainingIgnoreCase(
                userMessage, userMessage, userMessage, userMessage
        );

        // 4. GPT에게 전달할 프롬프트 작성
        String prompt;
        if (!foundPlaces.isEmpty()) {
            // 명소 정보를 찾았다면 찾은 명소 정보를 깔끔하게 정리해서 프롬프트 넣기
            String placeInfo = foundPlaces.stream().map(
                    place -> "명소 이름: " + place.getName() +
                            ", 설명: " + place.getDescription() +
                            ", 카테고리: " + place.getCategory() +
                            ", 주소: " + place.getAddress() +
                            ", 태그: " + place.getTags() +
                            (place.getOperatingHours() != null ? ", 운영시간: " +
                                    place.getOperatingHours() : "") +
                            (place.getAverageRating() != null ? ", 평점: " +
                                    place.getAverageRating() : "")
            )
                    .collect(Collectors.joining("\n---\n"));

            prompt = "당신은 지역 명소 안내 챗봇입니다. " +
                    "다음 명소 정보를 참고하여 사용자의 질문에 친절하고 상세하게 답변해주세요. " +
                    "만약 관련 정보가 없다면, 다른 명소를 추천하거나 더 자세히 질문해달라고 안내해주세요.\n\n" +
                   "참고 명소 정보:\n" + placeInfo + "\n\n" +
            "사용자 질문: " + userMessage;
        }
        else {
            prompt = "당신은 지역 명소 안내 챗봇입니다. " +
                    "사용자의 질문에 친절하고 상세하게 답변해주세요. " +
                    "지역 명소에 대해 질문해달라고 안내해주세요.\n\n" +
                    "사용자 질문: " + userMessage;
        }

        // --- 여기에 디버그 라인 추가 ---
        logger.debug("Loaded OpenAI Model Name: [{}]", model);
        logger.debug("Loaded OpenAI API URL: [{}]", apiUrl);
        logger.debug("Model variable value at API call: [{}]", model);
        logger.debug("Loaded OpenAI API Key (masked): [{}]", (openaiApiKey != null && openaiApiKey.length() > 10 ? openaiApiKey.substring(0, 10) + "..." : "N/A"));
        // --- 디버그 라인 추가 끝 ---

        // 5. GPT에게 내용 보내고 답장 받기(OpenAI API 호출)
        Map<String, Object> requestBodyMap = new HashMap<>(); // 변수명 변경 (body와 겹치지 않도록)
        requestBodyMap.put("model", model);
        requestBodyMap.put("messages", List.of(
                Map.of("role", "system", "content",
                        "You are a helpful assistant that provides information about famous places in South Korea based on the provided context."),
                Map.of("role", "user", "content", prompt)
        ));
        requestBodyMap.put("max_tokens", 500);

        // HTTP 헤더 설정: Content-Type 및 Authorization (API Key)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Content-Type 설정
        headers.setBearerAuth(openaiApiKey); // Authorization 헤더 설정 (가장 중요!)

        // 요청 본문과 헤더를 포함하는 HttpEntity 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBodyMap, headers);

        try {
            Map<String, Object> response = restTemplate.postForObject(apiUrl, requestEntity, Map.class);

            if (response != null && response.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                if (!choices.isEmpty() && choices.get(0).containsKey("message")) {
                    Map<String, Object> message = (Map<String, Object>)
                            choices.get(0).get("message");
                    if (message.containsKey("content")) {
                        return (String) message.get("content");
                    }
                }
            }
            return "답변을 생성하는 데 문제가 발생했습니다.";
        } catch (Exception e) {
            // 기존: e.printStackTrace();
            // 변경: logger.error() 사용
            logger.error("챗봇 응답 생성 중 오류 발생: {}", e.getMessage(), e); // 에러 메시지와 예외 객체를 함께 기록
            return "챗봇 시스템에 오류가 발생했습니다. 잠시 후 다시 시도해주세요.";
        }
    }
}
