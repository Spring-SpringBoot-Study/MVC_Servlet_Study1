package com.example.spring_mvc_study1_servlet.web.frontcontroller.v2;

import com.example.spring_mvc_study1_servlet.web.frontcontroller.MyView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v1.ControllerV1;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*") // v2으로 들어오는 요청은 해당 controller가 먼저 받음
public class FrontControllerServletV2 extends HttpServlet {

    // String의 URL이 들어오면, 그에 맞는 Controller를 반환해주기 위함
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI(); // localhost8080 뒤의 URL을 받음 예시 -> /front-controller/v2/members/new-form

        // 다형성에 의해 자식 객체 new MemberFormControllerV2(), MemberSaveControllerV2(), MemberListControllerV2() 을 ControllerV2으로 받을 수 있음
        ControllerV2 controller = controllerMap.get(requestURI);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request, response);

    }
}
