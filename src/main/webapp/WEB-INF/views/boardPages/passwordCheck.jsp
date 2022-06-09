<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-06-03
  Time: 오전 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h2>비번체크 passwordCheck.jsp</h2>
    <form action="/board/detail" method="post">
        <lable for="passwordConfirm">슨상 비번입력하쇼잉</lable>
        <input type="text" id="passwordConfirm"><br>
        <input type="button" onclick="passwordCheck()">
    </form>
</body>
<script>
    const passwordCheck = () =>{
        const passwordConfirm = document.getElementById("passwordConfirm").value;
        const passwordDB = '${board.boardPassword}';
        if(passwordConfirm == passwordDB){
            location.href = "/board/delete?id=${board.id}";
        }else {
            alert("슨상 비번이 틀리당께!! 똑바로 입력하그라")
            location.href = "/board/detail?id=${board.id}";
        }
    }
</script>
</html>
