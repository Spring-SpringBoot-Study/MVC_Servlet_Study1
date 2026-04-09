package com.example.spring_mvc_study1_servlet.web.frontcontroller.v5;

import com.example.spring_mvc_study1_servlet.web.frontcontroller.ModelView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.MyView;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.example.spring_mvc_study1_servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*") // v4으로 들어오는 요청은 해당 controller가 먼저 받음
public class FrontControllerServletV5 extends HttpServlet {

    // String의 URL이 들어오면, 그에 맞는 Controller를 반환해주기 위함
    private final Map<String, Object> handlerMappingMap = new HashMap<>(); // v1,v2,v3... 등 다양한 타입이 들어가야 하기 때문에 Object 사용
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); // 여러개의 adapter 중에서 필요한 것을 사용

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 예시 : MemberFormControllerV4() 반환
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 예시 : ControllerV4HandlerAdapter 반환
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }

        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
