<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Wish List (${email })</title>
<!-- Magnific Popup core CSS file -->
<link rel="stylesheet" href="resources/magnific-popup/magnific-popup.css"> 
<!-- Magnific Popup core JS file -->
</head>
<body id="myWishBody">
<c:if test="${view=='success' }">
	<ul id="pageUl" class="pageUl">
		<li>
			<a class="myListTopButton" href="myList.html">진행</a>
			<a class="myListTopButton" href="mySucFailList.html?view=fail">실패</a>
		</li>
	</ul>
</c:if>
<c:if test="${view=='fail' }">
	<ul id="pageUl" class="pageUl">
		<li>
			<a class="myListTopButton" href="myList.html">진행</a>
			<a class="myListTopButton" href="mySucFailList.html?view=success">성공</a>
		</li>
	</ul>
</c:if>
<!-- WishList -->	
	<div id="wishList">
		<ul id="wishUl" class="wishUl">
			<c:forEach var="wishlist" items="${myWishList}">
				<fmt:parseDate value="${wishlist.remainDate}" pattern="yyyy-MM-dd" var="parsedDate"/>
				<li class="wishLi">
					<span class="productSpan">${wishlist.product }</span>
					<div class="wishDiv">
						<div id="imgDiv_${wishlist.wishNo }" class="imgDiv">
							<c:if test="${wishlist.img != null}">
								<img alt="img_${wishlist.product }" src="${path }${wishlist.img }">
							</c:if>
							<c:if test="${wishlist.img == null}">
								<img alt="img_${wishlist.product }" src="resources/img/noImg.gif">
							</c:if>
						</div>
						<div class="priceDiv overDiv">금&nbsp;&nbsp;액 : <fmt:formatNumber pattern="#,###" value="${wishlist.price }"></fmt:formatNumber> 원</div>
						<div class="remainDateDiv overDiv">종료일 : <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd"/> 까지</div>
						<input type="hidden" name="wishNo" class="wishNo" value="${wishlist.wishNo }">
					</div>
				</li>
			</c:forEach>
		</ul>
		<div><a href='#writeDiv' class='openPopup newA newAText' onclick="newItem()">물건 추가</a></div>
	</div>
	<div id="pageForm">
		<ul id="pageUl" class="pageUl">
		<c:if test="${pg.startPage > pg.pageBlock }">
			<li class="pageUl"><a class="pageA forwardArrow" href="mySucFailList.html?view=${view }&currentPage=${pg.startPage-pg.pageBlock }">◀</a></li>
		</c:if>
		<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
			<li class="pageUl">
				<c:if test="${pg.currentPage==i }">
						<a class="pageA bold" href="mySucFailList.html?view=${view }&currentPage=${i }">${i }</a>
				</c:if>
				<c:if test="${pg.currentPage!=i }">
						<a class="pageA" href="mySucFailList.html?view=${view }&currentPage=${i }">${i }</a>
				</c:if>
			</li>
		</c:forEach>
		<c:if test="${pg.endPage < pg.totalPage }">
			<li class="pageUl"><a class="pageA nextArrow" href="myList.html?currentPage=${pg.startPage+pg.pageBlock }">▶</a></li>
		</c:if>
		</ul>
	</div>
	<div id="chart" style="display: none;">
		<div id="wishChart" style="min-width: 310px; height: 400px; width:500px; margin: 0 auto">
		</div>
	</div>
	
</body>
</html>