<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="bank_template.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계좌 관리</title>
<style type="text/css">
.fadeMsg {
	display: none;
	position: absolute;
	top: 100px;
	left: 100px;
	z-index: 10;
	width: 300px;
	background-color: #44c767;
	border-radius: 28px;
	border: 1px solid #18ab29;
	color: #ffffff;
	font-size: 15px;
	padding: 12px 20px;
	text-align: center;
}

#newAccountForm {
	float:right;
	display: none;
}

.accountForm {
	margin: 5px;
}

.inputStyle {
	margin-left: 10px;
	color: black;
}
</style>
<script type="text/javascript">
	$(function() {
		
		// 계좌 등록, 삭제, 잔고 변경 처리후 결과메세지 선택
		var result = "${result}";
		switch (result) {
		case '511':
			showMsg("<div class='fadeMsg'>새로운 계좌가 등록되었습니다</div>");
			refreshParent();
			break;
		case '512':
			showMsg("<div class='fadeMsg' style='background-color:#c74467'>이미 등록되어있는 계좌입니다</div>");
			break;
		case '513':
			showMsg("<div class='fadeMsg' style='background-color:#c74467'>계좌등록에 실패하였습니다</div>");
			break;
		case '521':
			showMsg("<div class='fadeMsg'>계좌 정보가 삭제되었습니다</div>");
			refreshParent();
			break;
		case '522':
			showMsg("<div class='fadeMsg' style='background-color:#c74467'>계좌 정보 삭제에 실패하였습니다</div>");
			break;
		case '531':
			showMsg("<div class='fadeMsg'>계좌의 잔고가 변경되었습니다.</div>");
			refreshParent();
			break;
		case '532':
			showMsg("<div class='fadeMsg' style='background-color:#c74467'>현재 잔고보다 많은 금액을 차감할 수 없습니다</div>");
			break;
		case '533':
			showMsg("<div class='fadeMsg' style='background-color:#c74467'>계좌의 잔고 변경에 실패하였습니다</div>");
			break;
		default:
			break;
		}
		/* 
		// 잔고변경버튼 클릭 이벤트
		$('.moneyEdit').click(function() {
			var formId = $(this).parent().attr('id');
			alert( $('#'+formId).child('#money').val());
			var flag = $(this).attr('id');
			var form = $(this).parent();
			var money = $(".accountForm > .money").val();
			$('.accountForm > #flag').val(flag);
			alert(money);
			alert(flag);
			if (flag == 'decr') {
				$("form > #money").val(money * -1);
			}
			//alert($(form).children('#money').val());
			$(form).attr('action', 'editBalance.html').submit();
		  	//editSubmit();
		});
 */
		// 새계좌등록버튼 클릭 이벤트
		$('#addNewAccountBtn').click(function(){
			$('#newAccountForm').toggle('500');	
		});
	});
	
	function plus(acc) {
		$('#editForm'+acc).submit();
	}	
	
	function minus(acc) {
		var money = $('#money_'+acc).val();
		$('#money_'+acc).val(money*-1);
		$('#editForm'+acc).submit();
	}

	//	새 계좌 등록, 삭제 요청, 삭제확인 등 메세지 출력
	function showMsg(msg) {
		$('body').append(msg);
		$('.fadeMsg').fadeIn();
		setTimeout(function() {
			$('.fadeMsg').fadeOut();
		}, 1500);
	}

	// 계좌 삭제 Confirm창
	function deleteConfirm(bank, account) {
		if (confirm(bank + "은행 (" + account + ")\n계좌 정보를 삭제 하시겠습니까?")) {
			location.href = "deleteAccount.html?account=" + account;
		}
	}

	// 계좌생성 및 변경시 부모창 갱신
	function refreshParent() {
		opener.loadAccountList();
	}

	
</script>

</head>

<body style="padding: 10 10 10 10;">

	<div id="accountList">
		<table>
			<tr style="margin: 5px;">
				<th class="bankTitle">은행</th>
				<th class="bankTitle" style="width: 130px;">계좌번호</th>
				<th class="bankTitle" style="width: 60px;">잔고</th>
				<th style="padding-left: 69px;">잔고 변경</th>
				<th class="bankTitle">삭제</th>
			</tr>
			<c:forEach var="acc" items="${aList}">
				<tr>
					<td class="bankContent">${acc.bank}</td>
					<td class="bankContent">${acc.account}</td>
					<td class="bankContent">${acc.money}</td>
					<td class="bankContent">
						<form action="editBalance.html" class="accountForm" id="editForm${acc.account}">
							<input type="hidden" name="email" value="${sessionScope.email}">
							<input type="hidden" name="account" value="${acc.account}">
							<input type="number" name="money" class="money" id="money_${acc.account}">
							<button class="moneyEdit" id="incr" onclick="plus('${acc.account}')">+</button>
							<button class="moneyEdit" id="decr" onclick="minus('${acc.account}')">-</button>
						</form>
					</td>
					<td style="padding-left: 7px;">
						<input style="margin-left: 6px; margin-top: 2px;" type="button" onclick="deleteConfirm('${acc.bank}','${acc.account}')" value="x" />
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<button type="button" class="btn btn-primary" id="addNewAccountBtn">새 계좌 등록</button>
	<div id="newAccountForm" class="btn btn-primary">
		<form action="addNewAccount.html">			
			<input type="hidden" name="email" value="${sessionScope.email}">
			<table  style="color:white;float:left;">
				<tr>
					<td>은행</td>
					<td style="width:70px;padding-right: 20px">
					<select name="bank" required="required" class="inputStyle">
						<option value="">선택</option>
						<option value="농협">농협</option>
						<option value="신협">신협</option>
						<option value="신한">신한</option>
						<option value="국민">국민</option>
					</select></td>
					<td>계좌번호</td>
					<td><input type="text" name="account"	required="required" maxlength="20" class="inputStyle"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" required="required"	maxlength="8" style="width:55px;" class="inputStyle"></td>
					<td>잔고</td>
					<td><input type="number" name="money" maxlength="20" required="required" style="width: 120px;" class="inputStyle"></td>
				</tr>
			</table>
			<button class="btn btn-warning" style="float:right;width:50px;height:50px;margin:5 5 5 15" >등록</button>
		</form>
	</div>
</body>
</html>