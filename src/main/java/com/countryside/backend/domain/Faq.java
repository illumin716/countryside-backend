package com.countryside.backend.domain;

import jakarta.persistence.*; // Spring Boot 3.x 이상에서는 javax.persistence 대신 jakarta.persistence 사용

@Entity
@Table(name = "faqs") // 테이블 이름 지정 (선택 사항, 기본적으로 클래스 이름의 소문자)
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 생성 전략
    private Long id;

    @Column(nullable = false, length = 500) // 질문 필드, null 불가능, 최대 길이 500
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT") // 답변 필드, null 불가능, 긴 텍스트 저장을 위해 TEXT 타입 사용
    private String answer;

    // 기본 생성자 (JPA 필수)
    public Faq() {
    }

    // 모든 필드를 포함하는 생성자 (편의상)
    public Faq(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters and Setters (롬복(@Data)을 사용하면 생략 가능)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}