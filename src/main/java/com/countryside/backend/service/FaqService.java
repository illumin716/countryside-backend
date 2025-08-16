package com.countryside.backend.service;

import com.countryside.backend.domain.Faq;
import com.countryside.backend.repository.FaqRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    // 모든 FAQ 조회 (관리자 페이지용)
    public List<Faq> getAllFaqs() {
        return faqRepository.findAll();
    }

    public Optional<Faq> findById(Long id) {
        return faqRepository.findById(id);
    }

    // FAQ 추가
    public Faq createFaq(Faq faq) {
        return faqRepository.save(faq);
    }

    // FAQ 수정
    public Optional<Faq> updateFaq(Long id, Faq updatedFaq) {
        return faqRepository.findById(id).map(faq -> {
            faq.setQuestion(updatedFaq.getQuestion());
            faq.setAnswer(updatedFaq.getAnswer());
            return faqRepository.save(faq);
        });
    }

    // FAQ 삭제
    public void deleteFaq(Long id) {
        faqRepository.deleteById(id);
    }

    // 사용자 메시지와 가장 잘 매칭되는 FAQ를 찾는 메서드
    public Optional<Faq> findMatchingFaq(String userMessage) {
        // TODO: 여기에 실제 FAQ 매칭 로직 구현
        // 단순 contains 체크, 유사도 분석, 정규식 등 다양한 방법 고려 가능
        // 현재는 간단한 예시로 모든 FAQ를 가져와서 contains로 체크
        List<Faq> allFaqs = faqRepository.findAll();
        String lowerCaseMessage = userMessage.toLowerCase();

        for (Faq faq : allFaqs) {
            // FAQ 질문이 사용자 메시지에 포함되어 있는지 확인 (단순 예시)
            if (lowerCaseMessage.contains(faq.getQuestion().toLowerCase())) {
                return Optional.of(faq);
            }
            // TODO: '근처 카페'와 같은 키워드를 FAQ로 관리한다면,
            // FAQ 질문 필드에 "근처 카페"를 넣고, 답변 필드에 "카페" 카테고리를 조회하는 로직을 담을 수는 없을 것입니다.
            // 이 경우, FAQ 답변 필드에 일반적인 답변을 넣고, "카페"나 "축제"처럼 특정 동작이 필요한 경우에는
            // 여전히 ChatbotService 내에서 별도의 로직으로 처리해야 할 수 있습니다.
            // 아니면 Faq 엔티티에 'actionType' 같은 필드를 추가하여 특정 동작을 지시하도록 설계할 수도 있습니다.
            // 초기에는 단순히 질문-답변 매핑만 구현하는 것이 좋습니다.
        }
        return Optional.empty(); // 매칭되는 FAQ 없음
    }
}