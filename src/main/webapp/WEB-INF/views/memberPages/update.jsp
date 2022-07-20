<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-06-03
  Time: 오전 9:02
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
<h2>슨상님들(개인회원) 수정처리할곳 update.jsp</h2>
<form action="/member/update" method="post" name="updateForm">
    번호:<input type="text" name="id" value="${updateMember.id}" readonly><br>
    계정:<input type="text" name="memberId" value="${updateMember.memberId}" readonly><br>
    비번:<input type="text" name="memberPassword" id="pwConfirm" placeholder="비번입력하라쇼잉~"><br>
    이름:<input type="text" name="memberName" value="${updateMember.memberName}"><br>
    나이:<input type="text" name="memberAge" value="${updateMember.memberBirth}"><br>
    전화번호:<input type="text" name="memberPhone" value="${updateMember.memberPhone}">
    <input class="btn btn-primary" type="button" onclick="memberUpdate()" value="정보수정">

</form>
</body>
<script>
    const memberUpdate = () =>{
        console.log("update 함수호출");
        const pwConfirm = document.getElementById("pwConfirm").value;
        const pwDB = '${updateMember.memberPassword}';
        console.log("pwConfirm" + pwConfirm + ",pwDB:" + pwDB);
        if (pwConfirm == pwDB){
            updateForm.submit();
        }else {
            alert("슨상! 비번이안맞는다야 정신차려라 알긋냐")
        }
    }
</script>
</html>
