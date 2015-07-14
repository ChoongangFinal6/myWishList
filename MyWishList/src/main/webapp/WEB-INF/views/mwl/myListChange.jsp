<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<c:forEach var="wishlist" items="${myWishList}">
		<div style="width: 250px; height: 200px; float: left;">
			${wishlist.product }<br>
			${wishlist.price }<br>
			${wishlist.remainDate }<br>
			<c:if test="${wishlist.img != null}">
				<img alt="" src="${wishlist.img }">
			</c:if>
			<input type="hidden" name="wishNo" >
		</div>
	</c:forEach>
</div>
