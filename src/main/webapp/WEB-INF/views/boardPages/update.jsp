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

<h2>슨상들 수정처리 update.jsp</h2>
<form action="/board/update" method="post" name="updateForm">
    <input type="text" name="id" value="${boardUpdate.id}" class="" readonly><br>
    <input type="text" name="boardTitle" value="${boardUpdate.boardTitle}" class=""><br>
    <input type="text" name="boardPassword" id="passwordConfirm" class="" placeholder="비번"><br>
    <input type="text" name="boardWriter" value="${boardUpdate.boardWriter}" class="" readonly><br>
    <textarea name="boardContents" id="" cols="30" rows="10">${boardUpdate.boardContents}</textarea><br>
    <input type="button" class="" value="수정" onclick="boardUpdate()"><br>
</form>
</body>
<script>
    const boardUpdate = () =>{
        const passwordConfirm = document.getElementById("passwordConfirm").value;
        const passwordDB = '${boardUpdate.boardPassword}';
        if (passwordConfirm == passwordDB){
            updateForm.submit();
        }else {
            alert("비번이 틀리잖어! 장난하냐잉~")
        }
    }
</script>
</html>
