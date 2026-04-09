<%@ page import="com.example.spring_mvc_study1_servlet.domain.Member.MemberRepository" %>
<%@ page import="com.example.spring_mvc_study1_servlet.domain.Member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 여기에는 자바 코드를 넣을 수 있음 -> 비지니스 로직 작성
  MemberRepository memberRepository = MemberRepository.getInstance(); // MemberRepository는 import 해줘야함

  System.out.println("MemberSaveServlet.service");
  String username = request.getParameter("username"); // request, response는 import 없이 그냥 사용 가능
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  memberRepository.save(member);
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
