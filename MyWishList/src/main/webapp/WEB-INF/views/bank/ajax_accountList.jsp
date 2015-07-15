<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../mwl/header.jsp"%>
<c:forEach var="account" items="${aList}">
	<div>${account.bank} ${account.account} ${account.money}</div>
</c:forEach>