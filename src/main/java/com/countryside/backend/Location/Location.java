package com.countryside.backend;
/*
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String englishName;
    private String koreanName;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("location")
    private List<Landmark> landmarks = new ArrayList<>();

    protected Location() {}

    public Location(String englishName, String koreanName) {
        this.englishName = englishName;
        this.koreanName = koreanName;
    }

    // Getter 메서드 생략 가능
    public Long getId() { return id; }
    public String getEnglishName() { return englishName; }
    public String getKoreanName() { return koreanName; }
    public List<Landmark> getLandmarks() { return landmarks; }
}
*/