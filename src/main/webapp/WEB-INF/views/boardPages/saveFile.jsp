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
            <input class="" type="text" name="boardPassword" placeholder="비번"><br>
            <textarea class="" name="boardContents" rows="10" cols="20" placeholder="내용"></textarea> <%-- rows="5" 5줄까지 쓸수 있는 문법 --%>
            첨부파일:<input type="file" name="boardFile">
            <input class=""  type="submit" value="글작성하쇼 이상한글 싸지르지 말고">
        </form>
    </div>

</div>

</body>
</html>
