<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>나의 페이지</title>
</head>
<body>
<% if (session.getAttribute("username") == null) { %>
<nav>
    <a href="<%= request.getContextPath() %>/login">로그인</a>
    <a href="<%= request.getContextPath() %>/join">회원가입</a>
</nav>
<% } else { %>
<nav>
    <a href="<%= request.getContextPath() %>/logout">로그아웃</a>
    <a href="<%= request.getContextPath() %>/content/free">무료</a>
    <a href="<%= request.getContextPath() %>/content/premium">프리미엄</a>
</nav>
<% } %>
<section>
    <% if (session.getAttribute("username") == null) { %>
    <p>환영합니다!</p>
    <% } else { %>
    <p>환영합니다! <%= session.getAttribute("username") %>
    </p>
    <% } %>
</section>
</body>
</html>