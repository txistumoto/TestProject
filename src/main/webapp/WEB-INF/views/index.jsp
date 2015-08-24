<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
	<title><fmt:message key="title"/></title>
	<link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
</head>
<body>
	<c:set var="localeCode" value="${pageContext.response.locale}" />
	<ul id="localelist">
		<li><a href="?locale=en" <c:if test="${localeCode == 'en'}">id="current"</c:if>><fmt:message key="locale.en"/></a></li>
		<li><a href="?locale=es" <c:if test="${localeCode == 'es'}">id="current"</c:if>><fmt:message key="locale.es"/></a></li>
	</ul>
	<div class="box center">
		<h1><fmt:message key="tittle.list" /></h1>
		<section>
		<table border="0">
			<tr>
				<!-- 
				<th><fmt:message key="apk.id" /></th>
				-->
				<th><fmt:message key="apk.file" /></th>
				<th><fmt:message key="apk.name" /></th>
				<th><fmt:message key="apk.pack" /></th>
				<!-- 
				<th><fmt:message key="apk.creation_date" /></th>
				<th><fmt:message key="apk.update_date" /></th>
				-->
				<th colspan="2">&nbsp;</th>
			</tr>
			<c:forEach var="apk" items="${apkList}">
			<tr>
				<!-- 
				<td>${apk.id}</td>
				-->
				<td>${apk.file}</td>
				<td>${apk.name}</td>
				<td>${apk.pack}</td>
				<!-- 
				<td>${apk.creation_date}</td>
				<td>${apk.update_date}</td>
				-->
				<td><a href="edit.html?id=${apk.id}"><fmt:message key="cmd.edit" /></a></td>
				<td><a href="delete.html?id=${apk.id}"><fmt:message key="cmd.delete" /></a></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="3">
				<td><a href="upload.html"><fmt:message key="cmd.add" /></a></td>
				<td><a href="run.html"><fmt:message key="cmd.run" /></a></td>
			</tr>
		</table>
		</section>
	</div>	
	<c:if test="${not empty message}">
		<div id="message"><p>${message}</p></div>
	</c:if>		
</body>
</html>