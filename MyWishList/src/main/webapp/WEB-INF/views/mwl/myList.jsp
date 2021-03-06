<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Wish List (${email })</title>
<!-- Magnific Popup core CSS file -->
<link rel="stylesheet" href="resources/magnific-popup/magnific-popup.css">
<script src="resources/magnific-popup/jquery.magnific-popup.js"></script>
<!-- Magnific Popup core JS file -->
<script type="text/javascript">
	$(function(){
		$('.openPopup').magnificPopup({
			type:'inline',
			midClick: true // Allow opening popup on middle mouse click. Always set it to true if you don't provide alternative source in href.
		});
	});
</script>
</head>
<body id="myWishBody">
<!-- <ul id="pageUl" class="pageUl">
	<li>
		<a class="myListTopButton" href="mySucFailList.html?view=success">성공</a>
		<a class="myListTopButton" href="mySucFailList.html?view=fail">실패</a>
	</li>
</ul> -->
<select id="wishSucFailPage">
	<option value="All">전체 페이지</option>
	<option value="success">성공 페이지</option>
	<option value="fail">실패 페이지</option>
</select>
<!-- 계좌목록 -->
	<div id="mwl_account">
		계좌정보<button onclick="accountDetail()">상세</button><button onclick="manageAccount()">관리</button>
		<div id="mwl_acc_list"></div>
	</div>
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
						<div class="buttons">
							<span onclick="modify(${wishlist.wishNo })" class="buttonSpan"><a href='#writeDiv' class='openPopup'>수정</a></span>
							<span onclick="del(${wishlist.wishNo })" class="buttonSpan">삭제</span>
						</div>
						<div class="priceDiv overDiv">금&nbsp;&nbsp;액 : <fmt:formatNumber pattern="#,###" value="${wishlist.price }"></fmt:formatNumber>원</div>
						<div class="remainDateDiv overDiv">종료일 : <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd"/> 까지</div>
						<input type="hidden" name="wishNo" class="wishNo" value="${wishlist.wishNo }">
					</div>
				</li>
			</c:forEach>
			<c:if test="${fn:length(myWishList)<3}">
				<li class="wishLi">
					<span class="productSpan">&nbsp;</span>
					<div class="newDiv" onclick="newItem()"><a href='#writeDiv' class='openPopup newA newAPlus'>+</a></div>
				</li>
			</c:if>
		</ul>
		<div id="newADiv"><a href='#writeDiv' class='openPopup newA newAText' onclick="newItem()">물건 추가</a></div>
	</div>
	<div id="pageForm">
		<ul id="pageUl" class="pageUl">
		<c:if test="${pg.startPage > pg.pageBlock }">
			<li class="pageUl"><a class="pageA forwardArrow" href="myList.html?currentPage=${pg.startPage-pg.pageBlock }">◀</a></li>
		</c:if>
		<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
			<li class="pageUl">
				<c:if test="${pg.currentPage==i }">
						<a class="pageA bold" href="myList.html?currentPage=${i }">${i }</a>
				</c:if>
				<c:if test="${pg.currentPage!=i }">
						<a class="pageA" href="myList.html?currentPage=${i }">${i }</a>
				</c:if>
			</li>
		</c:forEach>
		<c:if test="${pg.endPage < pg.totalPage }">
			<li class="pageUl"><a class="pageA nextArrow" href="myList.html?currentPage=${pg.startPage+pg.pageBlock }">▶</a></li>
		</c:if>
		</ul>
	</div>
	<select id="bankSelect" style="display: none;">
		<option value="All">전체</option>
		<c:forEach var="opt" items="${bankList }">
			<option value="${opt.account}">${opt.bank}/${opt.account}</option>
		</c:forEach>
	</select>
	<div id="chart" style="display: none;">
		<div id="wishChart" style="min-width: 310px; height: 400px; width:500px; margin: 0 auto">
		</div>
	</div>
	<input type="button" id="buyBtn" style="display: none;" value="구입">
	<div id="buyDiv" style="display: none;">
		<input type="password" id="password" placeholder="암호">
		<input type="button" id="buy" value="구입하기">
	</div>
		<input type="hidden" id="imsiWishNo" value="">
		<input type="hidden" id="imsiWishNoMoney" value="">
		<input type="hidden" id="imsiBank" value="">
		<input type="hidden" id="imsiBankMoney" value="">

	<div id="writeDiv" class="mfp-hide">
		<form action="myWishWrite.html" method="POST" enctype="multipart/form-data" name="myWishDto">
			<table id="writeTable">
				<tr>
					<th>품명</th>
				<td>
					<input type="text" name="product" id="product" class="input" >
				</td>
				</tr>
				<tr>
					<th>금액</th>
					<td>
						<input type="text" name="price" id="price" class="input">
					</td>
				</tr>
				<tr>
				<th>기한</th>
					<td>
						<input type="date" name="remainDate" id="remainDate" class="input">
					</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<span id='imgSpan'>
							<input type="file" name="image" id="imageInput" class="input">
							<input type="hidden" name="img" id="imgInput">
						</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="입력">
						<input type="button" value="취소">
					</td>
				</tr>
			</table>
			<input type="hidden" name="wishNo" id="wishNo" class="input">
		</form> 
	</div>
</body>
</html>