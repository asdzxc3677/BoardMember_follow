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
    <h2>선상님들 로그인후 보이는 임시 페이지 main.jsp</h2>
    로그인 회원 정보:${loginMember}<br>
    세션에 담은 회원아이디:${sessionScope.loginId}<br>
    세션에 담은 id:${sessionScope.loginId}<br>
<%--<button class="btn btn-primary" onclick="updateForm()">수정</button> 더이상 필요가 읎다!--%>
    <button class="btn btn-outline-success" onclick="saveFileForm()">글작성(file)</button>
    <button class="btn btn-outline-success" onclick="paging()">페이징목록</button>
    <c:if test="${sessionScope.loginMemberId eq 'admin'}">
        <a href="/findAll">관리자전용 회원목록</a>
    </c:if>
    <c:if test="${sessionScope.loginId eq 'admin'}"> <%-- 관리자만 보일수 있게 처리 --%>
        <button type="button" class="btn btn-warning" onclick="memberList()">회원정보</button>
    </c:if>
        <button type="button" class="btn btn btn-warning" onclick="logout()">로그아웃</button>

    <button type="button" class="btn btn-warning" onclick="detail()">내정보</button>
</body>
<script>
    // const updateForm = () => {
    //     location.href ="/member/update-form"; 더이상 필요가 읎다!
    // }
    const saveFileForm = () =>{
        location.href ="/board/saveFile";
    }
    const paging = () =>{
        location.href = "/board/paging";
    }
    const memberList = () =>{
        location.href = "/member/findAll";
    }
    const logout = () =>{
        location.href = "/member/logout";
    }
    const detail = () =>{
        location.href = "/member/detail";
    }

</script>
</html>
