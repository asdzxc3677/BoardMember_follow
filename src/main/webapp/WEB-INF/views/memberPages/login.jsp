<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-06-03
  Time: 오전 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <h2>슨상 로그인하쇼</h2>
    <form action="/member/login" method="post">
        <input type="text" name="memberId" placeholder="아이디">
        <input type="text" name="memberPassword" placeholder="비번">
        <input type="submit" value="로그인">
    </form>

</body>
</html>
