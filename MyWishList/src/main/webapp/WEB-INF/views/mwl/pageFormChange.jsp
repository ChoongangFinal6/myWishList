<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>

<c:if test="${pg.startPage > pg.pageBlock }">
	<input type="hidden" id="preNum" value="${pg.startPage-pg.pageBlock }">
	<a id="prePage">[이전]</a>
</c:if>
<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
	<a class="pageNum" id="${i }">[${i }]</a>
</c:forEach>
<c:if test="${pg.endPage < pg.totalPage }">
	<input type="hidden" id="nextNum" value="${pg.startPage+pg.pageBlock }">
	<a id="nextPage">[다음]</a>
</c:if>