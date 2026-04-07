package com.example.spring_mvc_study1_servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // "/hello"로 요청이 오면 이것이 실행
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");


        System.out.println("request = " + request); // org.apache.catalina.connector.RequestFacade@23c7728c
        System.out.println("response = " + response); // org.apache.catalina.connector.ResponseFacade@432c5aa8

        String username = request.getParameter("username"); // http://localhost:8080/hello?username=kim 요청시 getParameter로 requset의 쿼리 파라미터를 꺼낼 수 있음
        System.out.println("username = " + username);

        // response의 Header 정보에 들어감
        response.setContentType("text/plain"); // 단순 문자열로 응답을 보낸다
        response.setCharacterEncoding("UTF-8"); // 인코딩 정보

        response.getWriter().println("Hello " + username);
    }
}
