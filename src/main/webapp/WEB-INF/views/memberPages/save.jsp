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
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <style>
        .container{
            max-width: 500px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="display-4 fw-normal">슨상들 회원가입 save.jsp</h1>
    <div class="py-5 text-center">

        <form action ="/member/save" method="post">
            <input type="text" class="form-control mb-2" onblur="duplicateCheck()" id="memberId" name="memberId" placeholder="아이디"><br>
            <p id="dup-check-result"></p>
            <input type="password" class="form-control mb-2" id="memberPassword" name="memberPassword" placeholder="비번"><br>
            <input type="text" class="form-control mb-2" id="memberName" name="memberName" placeholder="이름"><br>
            <input type="text" class="form-control mb-2" id="memberAge" name="memberAge" placeholder="나이"><br>
            <input type="text" class="form-control mb-2" id="memberPhone" name="memberPhone" placeholder="핸드폰"><br>
            <input type="submit" value="회원가입">
        </form>
    </div>
</div>
</body>

<script> <%-- 아이디중복체크 --%>
    const duplicateCheck = () => {
        const memberId = document.getElementById("memberId").value;
        const checkResult = document.getElementById("dup-check-result");
        $.ajax({
            type:"post", <%-- Mapping 타입 get 이냐 post 이느냐  --%>
            url:"/member/duplicate-check",<%-- 리턴값을 받을 Mapping이름 --%>
            data:{"memberId":memberId},
            <%-- $.ajax의 데이터가 담기는 과정: <input type="text" onblur="duplicateCheck()" id="memberId" name="memberId" placeholder="아이디">
               ===>  const memberId = document.getElementById("memberId").value; ==> data:{"memberId":memberId},
 --%>
            dataType:"text", <%-- 데이타타입 : text --%>
            success:function (result) {
                if (result == "ok") {
                    checkResult.innerHTML = "슨상 사용가능한 아이디랑께~"
                    checkResult.style.color = "green";
                } else {
                    checkResult.innerHTML = "아야~ 슨상 이미사용한 아이디잖어 똑바로 확인하그라~ 알긋냐"
                    checkResult.style.color = "red";
                }
            },
           error: function (){
                alert("오타체크");
           }
        });
    }
</script>
</html>
