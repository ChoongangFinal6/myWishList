<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="bank_template.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계좌 관리</title>
<style type="text/css">

</style>
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