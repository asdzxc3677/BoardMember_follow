<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-06-02
  Time: 오후 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="./layout/header.jsp" flush="false"></jsp:include>
<h1>신안드레스에 오신걸 환영합니다 index.jsp</h1>
<c:if test="${sessionScope.loginId ==null}">
<a href ="/member/save-form">슨상 회원가입 하쇼잉</a><br>
</c:if>
<style>
    body {
        background-image: url("/resources/img/l_2020050101000105200003241.webp" );
        background-size: 100% 100%;
        background-repeat: no-repeat;
    }

</style>

</body>
</html>
