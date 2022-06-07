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

</head>
<body>

<h2>슨상님들 수정처리할곳</h2>
<form action="/member/update" method="post" name="updateForm">
    번호:<input type="text" name="id" value="${updateMember.id}"><br>

</form>
</body>
</html>
