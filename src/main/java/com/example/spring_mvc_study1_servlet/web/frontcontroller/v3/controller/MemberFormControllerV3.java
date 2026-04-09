package com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller;

import com.example.spring_mvc_study1_servlet.web.frontcontroller.ModelView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.MyView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.ControllerV2;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap){
//        String viewPath = "/WEB-INF/views/new-form.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);

//        return new MyView("/WEB-INF/views/new-form.jsp");
        return new ModelView("new-form");
    }
}
