package com.example.spring_mvc_study1_servlet.web.frontcontroller.v2;

import com.example.spring_mvc_study1_servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {

    // v1에서의 void 타입이 아닌 Myview 타입으로 만듬 -> dispather를 생성해서, forward하는 과정을 MyView에서 render() 함수로 처리
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
