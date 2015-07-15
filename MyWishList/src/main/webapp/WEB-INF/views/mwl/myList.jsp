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
<input type="button" onclick="location.href='mySucFailList.html?view=success'" value="성공">
<input type="button" onclick="location.href='mySucFailList.html?view=fail'" value="실패">
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
						<div class="priceDiv overDiv">${wishlist.price }</div>
						<div class="remainDateDiv overDiv"><fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd"/></div>
						<input type="hidden" name="wishNo" class="wishNo" value="${wishlist.wishNo }">
					</div>
				</li>
			</c:forEach>
			<c:if test="${fn:length(myWishList)<3}">
				<li class="wishLi">
					<span class="productSpan">aa</span>
					<div class="newDiv" onclick="newItem()"><a href='#writeDiv' class='openPopup newA newAPlus'>+</a></div>
				</li>
			</c:if>
		</ul>
		<div><a href='#writeDiv' class='openPopup newA newAText' onclick="newItem()">물건 추가</a></div>
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
			<span>품명</span><input type="text" name="product" id="product" class="input"><br>
			<span>금액</span><input type="text" name="price" id="price" class="input"><br>
			<span>기한</span><input type="date" name="remainDate" id="remainDate" class="input"><br>
			<span>이미지</span>
			<span id='imgSpan'><input type="file" name="image" id="imageInput"><input type="hidden" name="img" id="imgInput"></span><br>
			<span>성공여부</span><input type="text" name="success" id="success" class="input"><br>
			<input type="hidden" name="wishNo" id="wishNo" class="input">
			<input type="submit" value="입력">
			<input type="button" value="취소">
		</form> 
	</div>
</body>
</html>