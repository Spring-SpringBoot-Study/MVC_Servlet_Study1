package com.example.spring_mvc_study1_servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // request의 Body의 내용을 얻음(바이트 형식으로)

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 바이트를 문자열로 변환할 때 어떤한 방식으로 변환하는지 -> UTF_8
        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("ok");
    }
}
