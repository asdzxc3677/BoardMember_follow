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
<style>
    body{
        background-image: url("/resources/img/72525_11054_2021.jpg");
        background-size: cover;
    }
</style>

<div class="container">
    <h1 class="display-4 fw-normal">슨상들 회원가입 save.jsp</h1>
    <div class="py-5 text-center">

        <form action ="/member/save" method="post">
            <input type="text" class="form-control mb-2" onblur="duplicateCheck()" id="memberId" name="memberId" placeholder="아이디"><br>
            <p id="dup-check-result"></p>
            <input type="password" class="form-control mb-2" onblur="passCheck()" id="Password" name="memberPassword" placeholder="비번"><br>
            <p id="pw-check-result"></p>
            <input type="password" class="form-control mb-2" onblur="passCheck()" id="passwordCheck" placeholder="비번확인"><br>
            <input type="text" class="form-control mb-2" id="memberName" name="memberName" placeholder="이름"><br>

            <div class="input-group">
            <input type="text" class="form-control mb-2" id="memberBirth" name="memberBirth" placeholder="생년">
            <input type="text" class="form-control mb-2" id="BirthDay" name="memberBirth" placeholder="월일">
            </div><br> <%-- 생년월일 처럼 input 막대기가 2개로 이쁘게 되려면  <div class="input-group"> 적어야된다. --%>

            <input type="text" class="form-control mb-2" id="memberGender" name="memberGender" placeholder="성별"><br>

            <input type="text" class="form-control mb-2" id="memberEmail" name="memberEmail" placeholder="이메일"><br>
            <input type="text" class="form-control mb-2" id="memberPhone" name="memberPhone" placeholder="핸드폰"><br>
            <input type="submit" value="슨상 으기서 회원가입 하쇼잉~">
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
            url:"/member/duplicate-check",<%-- 리턴값을 받을 Mapping이름(주소값) Mapping : 컨트룰러를 찿아가는 주소값 --%>
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
    const passCheck = () =>{ <%-- 비밀번호 alert 형태로 확인 문법 추가함  --%>
        const memberPassword = document.getElementById("Password").value;
        const passCheck = document.getElementById("passwordCheck").value;
        const checkResult = document.getElementById("pw-check-result");
        if (memberPassword.length < 3){
            alert('입력한 글자가 3글자 이상이어야 된다 알긋냐잉')
        }
        if (memberPassword != passCheck){
            checkResult.innerHTML = "으따 우리 슨상 비번이 일치하지 않는당께 제데로 입력하그라"
            checkResult.style.color = "red";
        }else {
            checkResult.innerHTML = "일치한다 그라 "
            checkResult.style.color = "blue";
        }
    }
</script>
</html>
