package com.example.spring_mvc_study1_servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form"); // viewreSolver가 /WEB-INF/views/new-form.jsp의 물리 주소로 바꾸어서 찾아줌
    }
}
