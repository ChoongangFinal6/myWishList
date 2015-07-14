<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<form action="bankCreate.html">
	<select name="bank" id="bank">
		<option value="">은행 선택</option>
		<option value="하나">하나은행</option>
		<option value="신한">신한은행</option>
		<option value="농협">농협</option>
		<option value="신협">신협</option>
	</select><br>
	계좌번호 <input type="text" name="account" id="account"><br><div id="warning"></div>
	비밀번호 <input type="password" name="password" id="password"><br>
	현재금액 <input type="text" name="money" id="money"><br>
	<input type="button" id="addBank" value="등록">
	<input type="button" value="취소">
	<input type="hidden" name="email" value="${email }">
</form>
</body>
</html>