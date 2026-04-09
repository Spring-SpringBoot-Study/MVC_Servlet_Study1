package com.example.spring_mvc_study1_servlet.web.frontcontroller.v4;

import com.example.spring_mvc_study1_servlet.web.frontcontroller.ModelView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.MyView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.ControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*") // v4으로 들어오는 요청은 해당 controller가 먼저 받음
public class FrontControllerServletV4 extends HttpServlet {

    // String의 URL이 들어오면, 그에 맞는 Controller를 반환해주기 위함
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = request.getRequestURI(); // localhost8080 뒤의 URL을 받음 예시 -> /front-controller/v2/members/new-form

        // 다형성에 의해 자식 객체 new MemberFormControllerV2(), MemberSaveControllerV2(), MemberListControllerV2() 을 ControllerV2으로 받을 수 있음
        ControllerV4 controller = controllerMap.get(requestURI);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

//        MyView view = controller.process(request, response);
//        view.render(request, response);

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model); // 논리 이름 -> "new-form"

        MyView view = viewResolver(viewName); // "/WEB-INF/views/new-form.jsp" 처럼의 이름으로 변경
        view.render(model, request, response);
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
