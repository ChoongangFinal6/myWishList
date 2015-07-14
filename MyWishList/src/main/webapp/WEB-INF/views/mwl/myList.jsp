<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="template.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Wish List (${email })</title>
<style type="text/css">
	#mwl_account {
		border: 1px solid black;
		width:500px;
	}
</style>
<script type="text/javascript">
	function manageAccount(){
		var win = window.open("manageAccount.html", "계좌관리", "width=600, height=400,resizable=false");
	}
</script>
</head>
<body>
	<h3>My Wish List (${sessionScope.email })</h3>
	<div id="mwl_account">
		계좌정보<button onclick="manageAccount()">관리</button>
		<c:forEach var="account" items="${aList}">
			<div>${account.bank} ${account.account} ${account.money}</div>
		</c:forEach>
	</div>
</body>
</html>