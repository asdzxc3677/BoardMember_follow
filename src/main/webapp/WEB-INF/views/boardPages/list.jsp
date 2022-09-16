<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  &lt;%&ndash; forEach쓸때 필요한 테그 &ndash;%&gt;
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  &lt;%&ndash;  날짜 forEach쓸때 필요한 테그 &ndash;%&gt;
<html>
<head>
    <title>Title</title>-aaa-%>
    <link rel ="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<table class="container">
    <tr class="Table">
    <tr>
        <th>글번호</th>
        <th>작성자</th>
        <th>제목</th>
        <th>작성시간</th>
        <th>내용</th>
        <th>작성시간</th>&lt;%&ndash;(fmt적용) &ndash;%&gt;
        <th>조회수</th>
    </tr>
    </tr>
    <c:forEach items="${boardList}" var="board">
        <tr>
            <td>${board.id}</td>
            <td>${board.boardWriter}</td>
            <td><a href="/board/detail?id=${board.id}">${board.boardTitle}</a></td>&lt;%&ndash; 제목링크 걸기 처리 &ndash;%&gt;
            <td>${board.boardCreatedDate}</td>
            <td>${board.boardContents}</td>
            &lt;%&ndash; 날짜출력 작업 &ndash;%&gt;
            <td><fmt:formatDate  pattern="yyyy-MM-dd hh:mm:ss" value="${board.boardCreatedDate}"></fmt:formatDate></td>
            <td>${board.boardHits}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>