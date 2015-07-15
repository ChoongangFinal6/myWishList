<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Wish List (${email })</title>
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
<!-- 계좌목록 -->
	<div id="mwl_account">
		계좌정보<button onclick="accountDetail()">상세</button><button onclick="manageAccount()">관리</button>
		<div id="mwl_acc_list"></div>
	</div>
<!-- WishList -->	
	<div id="wishList">
		<c:forEach var="wishlist" items="${myWishList}">
			<div class="wishDiv" style="width: 250px; height: 200px; float: left;">
				${wishlist.product }<br>${wishlist.price }<br>
				${wishlist.remainDate }<br>
				<c:if test="${wishlist.img != null}">
					<img alt="" src="${wishlist.img }">
				</c:if>
				<input type="hidden" name="wishNo" class="wishNo" value="${wishlist.wishNo }">
			<span onclick="modify(${wishlist.wishNo })">수정</span>
			<span onclick="del(${wishlist.wishNo })">삭제</span>
			</div>
		</c:forEach>
	</div>
	<div style="width: 800px;">
<!-- Chart -->
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
	<div id="writeDiv">
		<form action="myWishWrite.html" method="POST" enctype="multipart/form-data" name="myWishDto">
			<span>품명</span><input type="text" name="product" id="product" class="input"><br>
			<span>금액</span><input type="text" name="price" id="price" class="input"><br>
			<span>기한</span><input type="date" name="remainDate" id="remainDate" class="input"><br>
			<span>이미지</span><span id='imgSpan'><input type="hidden" id="img" name="img"></span><br>
			<span>성공여부</span><input type="text" name="success" id="success" class="input"><br>
			<input type="text" name="wishNo" id="wishNo" class="input">
			<input type="submit" value="입력">
			<input type="button" value="취소">
		</form> 
	</div>

</body>
</html>