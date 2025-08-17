package com.countryside.backend.controller;

import com.countryside.backend.domain.Event;
import com.countryside.backend.repository.EventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events") // <--- 경로를 events로 변경
public class EventController { // <--- 클래스 이름을 EventController로 변경
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) { // <--- 생성자 이름 변경
        this.eventRepository = eventRepository;
    }

    /**
     * 전체 이벤트 중 4개를 무작위로 선택하여 반환합니다.
     * @return 무작위로 선택된 이벤트 리스트
     */
    @GetMapping("/random") // <--- 경로를 random으로 변경
    public List<Event> getRandomEvents() {
        List<Event> allEvents = eventRepository.findAll();
        Random random = new Random();

        return random.ints(4, 0, allEvents.size())
                .distinct()
                .mapToObj(allEvents::get)
                .collect(Collectors.toList());
    }
}