
package com.countryside.backend.dto;

import java.util.List;

public class HomeFestivalsResponse {
    private final String title = "현재 진행중인 축제";
    private final List<String> festivals;

    public HomeFestivalsResponse(List<String> festivals) {
        this.festivals = festivals;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getFestivals() {
        return festivals;
    }
}
