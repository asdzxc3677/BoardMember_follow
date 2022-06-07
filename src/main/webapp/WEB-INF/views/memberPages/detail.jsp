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

    <script src="/resources/js/jquery.js"></script>
</head>
<body>

<h2>내정보 datail.jsp</h2>
<div class="container">
    <table class="table">
        <tr>
            <th>회원번호</th>
            <th>아이디</th>
            <th>비번</th>
            <th>이름</th>
            <th>나이</th>
            <th>폰번호</th>
            <th>정보수정</th>
            <th>삭제</th>
        </tr>
            <tr>
                <td>${member.id}</td>
                <td>${member.memberId}</td>
                <td>${member.memberPassword}</td>
                <td>${member.memberName}</td>
                <td>${member.memberAge}</td>
                <td>${member.memberPhone}</td>
                <td><button class="btn btn-outline-info" onclick="update()">정보수정</button></td>
                <td><a href="/member/delete?id=${member.id}">탈퇴</a></td>
            </tr>
    </table>
    <div id="detail"></div>
</div>
</body>
<script>
    function update() {
        location.href="/member/update-form"
    }
</script>
</html>
