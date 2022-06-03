<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-06-03
  Time: 오전 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <h2>선상님들~ 로그인후 보이는 임시 페이지랑께~ main.jsp</h2>
    로그인 회원 정보:${loginMember}<br>
    세션에 담은 회원아이디:${sessionScope.loginId}<br>
    세션에 담은 id:${sessionScope.loginId}<br>
    <a href="/">index 시작페이지로 이동</a><br>
    <button class="btn btn-primary" onclick="updateForm()">수정</button>
    <button class="btn btn-outline-success" onclick="saveFileForm()">글작성(file)</button>
    <button class="btn btn-outline-success" onclick="paging()">페이징목록</button>
    <c:if test="${sessionScope.loginMemberId eq 'admin'}">
        <a href="/findAll">관리자전용 회원목록</a>
    </c:if>
</body>
<script>
    const updateForm = () => {
        location.href ="/member/update-form";
    }
    const saveFileForm = () =>{
        location.href ="/board/saveFile";
    }
    const paging = () =>{
        location.href = "/board/paging";
    }
</script>
</html>
