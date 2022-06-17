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
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false"></jsp:include>
    <h2>슨상 로그인하쇼</h2>
    <form action="/member/login" method="post">
        <input type="text" name="memberId" placeholder="아이디">
        <input type="text" name="memberPassword" placeholder="비번">
        <input type="submit" value="로그인">
    </form>
<style>
    body{
        background-image: url("/resources/img/15-60.jpg");
        background-size: cover;
    }
</style>
</body>
</html>
