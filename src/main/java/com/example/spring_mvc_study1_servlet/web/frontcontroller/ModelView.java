package com.example.spring_mvc_study1_servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {
    private String viewName; // 논리 이름 -> "new-form"
    private Map<String, Object> model = new HashMap<>(); // 화면에 뿌릴 데이터(model)

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

}
