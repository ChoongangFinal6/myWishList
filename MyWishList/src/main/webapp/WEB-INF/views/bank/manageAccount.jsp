<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../mwl/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계좌 관리</title>
<style type="text/css">
.fadeMsg {
	display:none;
	position:absolute;
	top:100px;
	left:100px;
	z-index:10;
	width:250px;
	background-color:#44c767;
	border-radius:28px;
	border:1px solid #18ab29;
	color:#ffffff;
	font-size:15px;
	padding:12px 20px;
	text-align: center;
}

#newAccount {
	
}
</style>
<script type="text/javascript">
	$(function(){
		var result = "${result}";
		if( result == 1 ){
			showMsg("<div class='fadeMsg'>새로운 계좌가 등록되었습니다</div>");		
		}else if( result == -1 ){
			showMsg("<div class='fadeMsg'>이미 등록되어있는  계좌입니다</div>");
		}else if( result == 2){
			showMsg("<div class='fadeMsg'>계좌 정보가 삭제되었습니다</div>");
		}else if( result == 3){ 
			showMsg("<div class='fadeMsg'>계좌 정보가 삭제되지 않았습니다</div>");
		}
	});

	//	새 계좌 등록, 삭제 요청, 삭제확인 등 메세지 출력 메소드
	function showMsg(msg){
		$('body').append(msg);
		$('.fadeMsg').fadeIn();
		setTimeout(function(){ $('.fadeMsg').fadeOut();	}, 2000);
	}
	
	function deleteConfirm(bank, account){
		if(confirm(bank + "은행 ("+ account + ")\n계좌 정보를 삭제 하시겠습니까?")){
			location.href="deleteAccount.html?account="+account;
		}
	}
	
 	$(function(){
 		$('.moneyEdit').click(function(){
 			console.log("event");
			var flag = $(this).attr('id');
 			var form = $(this).parent();
			var money = $("form > #money").val();
			if(flag=='decr'){
				$("form > #money").val(money*-1);
			}
			console.log($(form).children('#money').val());
			$(form).attr('action', 'editBalance.html').submit();
 		});
	}); 
</script>

</head>

<body>
<div id="accountList">
	<table>
		<tr>
			<th>은행</th><th>계좌번호</th><th>잔고</th><th>잔고 변경</th><th>삭제</th>
		</tr>
	<c:forEach var="acc" items="${aList}">
		<tr>
			<td>${acc.bank}</td>
			<td>${acc.account}</td>
			<td>${acc.money}</td>
			<td>
				<form action="#">
					<input type="hidden" name="email" value="${sessionScope.email}">
					<input type="hidden" name="account" value="${acc.account}">
					<input type="number" name="money" id="money">
					<button class="moneyEdit" id="incr">+</button>
					<button class="moneyEdit" id="decr">-</button>
					<!-- <button onclick="editBalance()">입출금</button> -->
				</form>
			</td>
			<td>
			 	<button onclick="deleteConfirm('${acc.bank}',${acc.account})">X</button>
			</td>
		</tr>
	</c:forEach>
	</table>
</div>

<div id="newAccount">
	<form action="addNewAccount.html">
	<input type="hidden" name="email" value="${sessionScope.email}">
	은행<select name="bank" required="required">
		<option value="">선택</option>
		<option value="농협">농협</option>
		<option value="국민">국민</option>
	</select>
	계좌번호<input type="text" name="account" required="required" maxlength="20">
	결제비밀번호<input type="text" name="password" required="required" maxlength="8" >
	잔고<input type="number" name="money" maxlength="20">
	<button>등록</button>
	</form>
</div>
</body>
</html>