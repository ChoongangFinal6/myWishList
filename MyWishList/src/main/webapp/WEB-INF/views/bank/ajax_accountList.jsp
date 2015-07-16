<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	#accounDetail {display: none; float:left; position:absolute; background-color: gray;}
</style>
<c:set var='sum' value='0'></c:set>
<c:set var='count' value='0'></c:set>
<div id="accounDetail">
	<c:forEach var="account" items="${aList}">
		<div>${account.bank} ${account.account} ${account.money}</div>
		<c:set var='sum' value="${sum+account.money}"></c:set>
		<c:set var='count' value="${count+1}"></c:set>
	</c:forEach>
</div>
<div>등록계좌 : ${count}개,	총 잔고 ${sum}원</div>