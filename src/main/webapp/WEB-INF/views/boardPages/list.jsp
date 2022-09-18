<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<div id="contentsArea">
    <section id="titlename" class="qnaBoard">
        <h1>사원 리스트</h1>
        <div id="infoArea">
            <section class="search">
                <form name="search" action="./BoardSearchList.do" method="post">
                    <fieldset>
                        <legend>검색</legend>
                        <label for="keyword"></label>
                        <select name="keyfield" class="b_search">
                            <option value="all" selected="selected">전체 검색</option>
                            <option value="subject"<c:if test="${'subject' == keyfield}">selected="selected"</c:if>>제목</option>
                            <option value="name"<c:if test="${'name' == keyfield}">selected="selected"</c:if>> 글쓴이</option>
                            <option value="content"<c:if test="${'content' == keyfield}">selected="selected"</c:if>>내용</option>
                        </select>
                        <input type="search" id="keyword" name="keyword" required="required" placeholder="검색어 입력">
                        <button type="submit">검색</button>
                    </fieldset>
                </form>
            </section>
        </div>
        <p class="allPost">
            전체 글: &nbsp; <strong><c:out value="${listcount}" /> </strong>개
        </p>
        <table class="boardTable">
            <caption>사원 리스트</caption>
            <c:choose>
            <c:when test="${listcount>0}">
            <thead>
            <tr>
                <th scope="col"><input type="checkbox" id="checkAll"></th>
                <th scope="col">한글명</th>
                <th scope="col">영문명</th>
                <th scope="col">한문명</th>
                <th scope="col">기술등급</th>
                <th scope="col">주민등록번호 앞자리</th>
                <th scope="col">주민등록번호 뒷자리</th>
                <th scope="col">생년월일</th>
                <th scope="col">연차</th>
                <th scope="col">핸드폰번호</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="member" items="${memberList}" varStatus="status">
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>
                            <a href="./BoardDetail.do?num=<c:out value="${board.num}" />">
                                <c:out value="${member.korName}" />
                            </a>
                        </td>
                        <td><c:out value="${member.engName}" /></td>
                        <td><c:out value="${member.chinaName}" /></td>
                        <td><c:out value="${member.techLevel}" /></td>
                        <td><c:out value="${member.regNo1}" /></td>
                        <td><c:out value="${member.regNo2}" /></td>
                        <td><c:out value="${member.birthDate}" /></td>
                        <td><c:out value="${member.year}" /></td>
                        <td><c:out value="${member.phoneNum}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
            </c:when>
            </c:choose>
        </table>
        <div align="center">
            <table id="boardTableNe" class="boardTableNe">
                <tbody>
                <c:if test="${searchlistcount==0}">
                    <tr>
                        <td colspan="4"></td>
                        <td>등록된 글이 없습니다.</td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="5">
                        <a href="/member/list?page=${startpage}">[처음으로]</a>
                        <c:choose>
                            <c:when test="${page <= 1}"> [이전]&nbsp; </c:when>
                            <c:otherwise>
                                <a href="/member/list?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp; </c:otherwise>
                        </c:choose>
                        <c:forEach var="start" begin="${startpage}" end="${endpage}" step="1">
                            <c:choose>
                                <c:when test="${start eq page}"> [<c:out value="${start}" />] </c:when>
                                <c:otherwise>
                                    <a href="/member/list?page=<c:out value="${start}" />">[<c:out value="${start}" />]</a>&nbsp; </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${page >= maxpage}">[다음] </c:when>
                            <c:otherwise>
                                <a href="/member/list.do?page=<c:out value="${page+1}" />">[다음]</a>
                            </c:otherwise>
                        </c:choose>
                        <a href="/member/list?page=${endpage}">[마지막으로]</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="btnJoinAreb">
                <button type="button" value="button" onclick="location.href='/member/memberReg'">
                    글쓰기
                </button>
                <button type="button" value="button" >
                    수정
                </button>
                <button type="button" value="button" >
                    삭제
                </button>
            </div>
        </div>
    </section>
</div>
</body>
</html>