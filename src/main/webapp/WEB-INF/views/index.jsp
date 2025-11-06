<%--
  Created by IntelliJ IDEA.
  User: junhyun
  Date: 2025. 11. 6.
  Time: 오후 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>나의 페이지</title>
</head>
<body>
<nav>
    <a href="<%= request.getContextPath() %>/login">로그인</a>
    <a href="<%= request.getContextPath() %>/join">회원가입</a>
</nav>
<section>
    <%--    <img style="width: 320px; border-radius: 16px;" src="./img/cat.jpeg" alt="미미">--%>
    <p>환영합니다.</p>
</section>
</body>
</html>
