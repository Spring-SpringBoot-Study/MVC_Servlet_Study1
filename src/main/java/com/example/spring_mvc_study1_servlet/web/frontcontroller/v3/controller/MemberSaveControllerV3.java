package com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller;

import com.example.spring_mvc_study1_servlet.domain.Member.Member;
import com.example.spring_mvc_study1_servlet.domain.Member.MemberRepository;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.ModelView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.MyView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.ControllerV2;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap){
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

//        //Model에 데이터를 보관한다.
//        request.setAttribute("member", member);
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;

//        String viewPath = "/WEB-INF/views/save-result.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);

//        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
