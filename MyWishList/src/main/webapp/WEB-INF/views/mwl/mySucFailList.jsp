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
<script src="resources/magnific-popup/jquery.magnific-popup.js"></script>
<script type="text/javascript">
	$(function(){
		loadAccountList();		
	});

	// 계좌 관리 팝업창
	function manageAccount(){
		var win = window.open("manageAccount.html", "계좌관리", "width=500, height=400,resizable=false");
	}
	
	// 계좌 목록 Load
	function loadAccountList(){
		$.ajax({
			url: "loadAccountList.html",
			success : function(result) {
				$('#mwl_acc_list').html(result);
			}
		});
	}
	
	function accountDetail(){
		$('#accounDetail').toggle('1000');
	}
	
</script>
</head>
<body>
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
						<div class="priceDiv overDiv">${wishlist.price }</div>
						<div class="remainDateDiv overDiv"><fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd"/></div>
						<input type="hidden" name="wishNo" class="wishNo" value="${wishlist.wishNo }">
					</div>
				</li>
			</c:forEach>
		</ul>
		<div><a href='#writeDiv' class='openPopup newA newAText' onclick="newItem()">물건 추가</a></div>
	</div>
	<div id="pageForm">
		<c:if test="${pg.startPage > pg.pageBlock }">
			<a href="mySucFailList.html?view=${view }&currentPage=${pg.startPage-pg.pageBlock }">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
			<a href="mySucFailList.html?view=${view }&currentPage=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${pg.endPage < pg.totalPage }">
			<a href="mySucFailList.html?view=${view }&currentPage=${pg.startPage+pg.pageBlock }">[다음]</a>
		</c:if>
	</div>
	
	<div id="chart" style="display: none;">
		<div id="wishChart" style="min-width: 310px; height: 400px; width:500px; margin: 0 auto">
		</div>
	</div>
	
</body>
</html>