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
    <link rel="stylesheet" href="../../../resources/css/bootstrap.min.css">
    <script src="/resources/js/jquery.js"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false"></jsp:include>
<h2>내정보 detail.jsp</h2>
<a href="/">index 시작페이지로 이동</a><br>
<div class="container">
    <table class="table">
        <tr>
            <th>회원번호</th>
            <th>아이디</th>
            <th>비번</th>
            <th>이름</th>
            <th>생년월일</th>
            <th>성별</th>
            <th>이메일</th>
            <th>폰번호</th>
            <th>정보수정</th>
            <th>삭제</th>
        </tr>
        <tr>
            <td>${member.id}</td>
            <td>${member.memberId}</td>
            <td>${member.memberPassword}</td>
            <td>${member.memberName}</td>
            <td>${member.memberBirth}</td>
            <td>${member.memberGender}</td>
            <td>${member.memberEmail}</td>
            <td>${member.memberPhone}</td>
            <td>
                <button class="btn btn-outline-info" onclick="update()">정보수정</button>
            </td>
            <td>
                <button class="btn btn-primary" id="delete" onclick="deleteMember()">탈주</button>
            </td>
        </tr>
    </table>
    <div id="detail"></div>
</div>

<style>
    body {
        background-image: url("/resources/img/2019101401000385200022682.jpg");
        background-size: cover;
    }
</style>
</body>
<script>
    function update() { <%-- 개인 회원수정 처리--%>
        location.href = "/member/update-form"
    }

    function deleteMember() { <%-- 개인 회원탈퇴 할때 확인용도 처리 --%>
        let result = confirm("진짜로 갈끄냐잉")
        <%--confirm 확인창 띄어준다 메서드 --%>
        const memberId = "${member.id}";
        if (result) {
            location.href = "/member/delete?id=" + memberId;
            alert("슨상 언제든 다시오쇼 언제든 열려 있으니께")
        } else {
            alert("잘 선택했소 슨상")
        }
    }
</script>
</html>
