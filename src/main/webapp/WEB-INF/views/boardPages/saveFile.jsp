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

<div class="container">
    <h2 class="">슨상들 글싸지르는 곳 saveFile.jsp</h2>
    <div class="">

        <form action="/board/saveFile" method="post" enctype="multipart/form-data">
            <input class="" type="text" name="boardTitle" placeholder="제목"><br>
            <input class="" type="text" name="boardWriter" value="${sessionScope.loginId}" readonly><br>

        </form>
    </div>

</div>

</body>
</html>
