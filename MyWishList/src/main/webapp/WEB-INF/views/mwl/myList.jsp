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
		<c:forEach var="wishlist" items="${myWishList}">
			<div class="wishDiv" style="width: 250px; height: 200px; float: left;">
				${wishlist.product }<br>
				${wishlist.price }<br>
				${wishlist.remainDate }<br>
				<c:if test="${wishlist.img != null}">
					<img alt="" src="${wishlist.img }">
				</c:if>
				<input type="hidden" name="wishNo" class="wishNo" value="${wishlist.wishNo }">
			</div>
		</c:forEach>
	</div>
	<div style="width: 800px;">
		<input type="button" id="createBank" value="은행등록">
		<div id="bankCreForm"></div>
		<br>
		<select id="bankSelect" style="display: none;">
			<option value="All">전체</option>
			<c:forEach var="opt" items="${bankList }">
				<option value="${opt.bank }">${opt.bank }</option>
			</c:forEach>
		</select>
		<div id="chart" style="display: none;">
			<div id="wishChart" style="min-width: 310px; height: 400px; width:500px; margin: 0 auto"></div>
		</div>
		<input type="button" id="buyBtn" style="display: none;" value="구입">
		<div id="buyDiv" style="display: none;">
			<input type="password" id="password" placeholder="암호">
			<input type="button" id="buy" value="구입하기">
		</div>
		<div id="pageForm">
			<c:if test="${pg.startPage > pg.pageBlock }">
				<a href="myList.html?currentPage=${pg.startPage-pg.pageBlock }">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
				<a href="myList.html?currentPage=${i }">[${i }]</a>
			</c:forEach>
			<c:if test="${pg.endPage < pg.totalPage }">
				<a href="myList.html?currentPage=${pg.startPage+pg.pageBlock }">[다음]</a>
			</c:if>
		</div>
		<input type="hidden" id="imsiWishNo" value="">
		<input type="hidden" id="imsiWishNoMoney" value="">
		<input type="hidden" id="imsiBank" value="">
		<input type="hidden" id="imsiBankMoney" value="">
	</div>
</body>
</html>