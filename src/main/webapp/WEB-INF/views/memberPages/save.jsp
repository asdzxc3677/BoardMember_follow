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
<h2>회원가입 하쇼잉</h2>
<div class="container">
    <form action="/member/save" method="post">
        계정:<input type="text" onblur="duplicateCheck()" id="memberId" name="memberId" placeholder="아이디"><br>
        <p id="dup-check-result"></p>
        비번:<input type="text" id="memberPassword" name="memberPassword" placeholder="비번"><br>
        이름:<input type="text" id="memberName" name="memberName" placeholder="이름"><br>
        나이:<input type="text" id="memberAge" name="memberAge" placeholder="나이"><br>
        폰번호: <input type="text" id="memberPhone" name="memberPhone" placeholder="폰번호">
            <input type="submit" value="회원가입">

    </form>
</div>
</body>
<script> <%-- 아이디중복체크 --%>
    const duplicateCheck = () => {
        const memberId = document.getElementById("memberId").value;
        const checkResult = document.getElementById("dup-check-result");
        $.ajax({
            type:"post",
            url:"/member/duplicate-check",
            data:{"memberId":memberId},
            dataType:"text",
            success:function (result) {
                if (result == "ok") {
                    checkResult.innerHTML = "슨상 사용가능한 아이디랑께~"
                    checkResult.style.color = "green";
                } else {
                    checkResult.innerHTML = "아야~ 슨상 이미사용한 아이디잖어 제데로 확인하그라~ 알긋냐"
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