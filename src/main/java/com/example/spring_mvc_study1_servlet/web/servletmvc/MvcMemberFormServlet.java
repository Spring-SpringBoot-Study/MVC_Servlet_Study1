package com.example.spring_mvc_study1_servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; // WEB-INF 경로 안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없음 -> 항상 컨트롤러를 통해서 JSP를 호출
        // dispatcher.forward(request, response)에서 MvcMemberFormServlet라는 컨트롤러를 통해 WEB-INF 안의 JSP에 접근

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // controller에서 view로 이동할 때 사용
        dispatcher.forward(request, response); // 서블릿에서 JSP를 호출 -> 다른 서블릿이나 JSP로 이동할 수 있는 기능, 서버 내부에서 다시 호출이 발생
    }
}
