package com.example.spring_mvc_study1_servlet.web.frontcontroller.v3;

import com.example.spring_mvc_study1_servlet.web.frontcontroller.ModelView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.MyView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.ControllerV2;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*") // v3으로 들어오는 요청은 해당 controller가 먼저 받음
public class FrontControllerServletV3 extends HttpServlet {

    // String의 URL이 들어오면, 그에 맞는 Controller를 반환해주기 위함
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI(); // localhost8080 뒤의 URL을 받음 예시 -> /front-controller/v2/members/new-form

        // 다형성에 의해 자식 객체 new MemberFormControllerV2(), MemberSaveControllerV2(), MemberListControllerV2() 을 ControllerV2으로 받을 수 있음
        ControllerV3 controller = controllerMap.get(requestURI);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

//        MyView view = controller.process(request, response);
//        view.render(request, response);

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); // 논리 이름 -> "new-form"
        MyView view = viewResolver(viewName); // "/WEB-INF/views/new-form.jsp" 처럼의 이름으로 변경
        view.render(mv.getModel(), request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
