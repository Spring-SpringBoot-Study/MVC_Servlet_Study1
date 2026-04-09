package com.example.spring_mvc_study1_servlet.web.frontcontroller.v1;

import com.example.spring_mvc_study1_servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // v1으로 들어오는 요청은 해당 controller가 먼저 받음
public class FrontControllerServletV1 extends HttpServlet {

    // String의 URL이 들어오면, 그에 맞는 Controller를 반환해주기 위함
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); // localhost8080 뒤의 URL을 받음 예시 -> /front-controller/v1/members/new-form

        // 다형성에 의해 자식 객체 new MemberFormControllerV1(), MemberSaveControllerV1(), MemberListControllerV1() 을 ControllerV1으로 받을 수 있음
        ControllerV1 controller = controllerMap.get(requestURI);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);

    }
}
