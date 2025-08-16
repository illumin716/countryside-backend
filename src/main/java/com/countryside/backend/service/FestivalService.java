
package com.countryside.backend.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class FestivalService {

    private final List<String> festivals;
    private final Random random = new SecureRandom();

    public FestivalService() {

        this.festivals = List.of(
                "대전 0시 축제",
                "유성온천문화축제",
                "대전 청년축제",
                "대전 사이언스 페스티벌",
                "드론·항공 축제",
                "빛·물·숲 페스티벌",
                "대전 힙합 페스티벌",
                "대전 국제 와인&다인 페스티벌"
        );
    }

    public List<String> getRandomCurrentFestivals() {
        int count = 3 + random.nextInt(2); // 3 또는 4
        List<String> copy = new ArrayList<>(festivals);
        Collections.shuffle(copy, random);
        return copy.subList(0, Math.min(count, copy.size()));
    }
}
