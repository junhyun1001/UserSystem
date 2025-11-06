<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<nav>
    <a href="<%= request.getContextPath() %>/">메인</a>
    <a href="<%= request.getContextPath() %>/join">가입</a>
</nav>
<section>
    <form method="post">
        <input name="username" placeholder="유저이름"><br>
        <input name="password" type="password" placeholder="비밀번호">
        <button>로그인</button>
    </form>
</section>
</body>
</html>