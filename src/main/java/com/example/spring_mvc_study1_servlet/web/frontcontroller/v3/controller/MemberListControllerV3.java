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
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap){
        System.out.println("MvcMemberListServlet.service");
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;

//        request.setAttribute("members", members);

//        String viewPath = "/WEB-INF/views/members.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);

//         return new MyView("/WEB-INF/views/members.jsp");
    }
}
