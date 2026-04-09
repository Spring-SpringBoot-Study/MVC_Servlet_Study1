package com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.controller;

import com.example.spring_mvc_study1_servlet.web.frontcontroller.MyView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String viewPath = "/WEB-INF/views/new-form.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);

        // 위의 주석의 내용을 MyView에서 처리함
        // MyView myView = new MyView("/WEB-INF/views/new-form.jsp");
        // return myView;
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
