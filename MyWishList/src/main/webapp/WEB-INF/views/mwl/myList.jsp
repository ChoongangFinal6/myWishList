<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Wish List (${email })</title>
</head>
<body>
	<div id="wishList">
		<div>
			<c:forEach var="wishlist" items="${myWishList}">
				<div style="width: 250px; height: 200px; float: left;">
					${wishlist.product }<br>
					${wishlist.price }<br>
					${wishlist.remainDate }<br>
					<c:if test="${wishlist.img != null}">
					<img alt="" src="${wishlist.img }">
					</c:if>
					<input type="hidden" name="wishNo" >
				</div>
			</c:forEach>
		</div>
	</div>
	<div style="width: 800px;">
		<input type="button" id="createBank" value="은행등록">
		<div id="bankCreForm"></div>
		<br>
		<form>
			<div id="pageForm">
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
			</div>
		</form>
	</div>
</body>
</html>