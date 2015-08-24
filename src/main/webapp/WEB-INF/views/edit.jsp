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
	<h1><fmt:message key="tittle.edit" /></h1>
	<section>
		<form:form method="post" action="edit.html" modelAttribute="apk">
	    <table border="0">
			<tr>
				<th colspan="2"><label for="file"><fmt:message key="apk.file" /></label></th>
				<td colspan="2"><form:input id="file" path="file" value="${apk.file}" size="25" maxlength="50" tabindex="1" readonly="true"/></td>
			</tr>
			<tr>
				<th colspan="2"><label for="name"><fmt:message key="apk.name" /></label></th>
				<td colspan="2"><form:input id="name" path="name" value="${apk.name}" size="25" maxlength="50" tabindex="2"/></td>
			</tr>
			<tr>
				<th colspan="2"><label for="pack"><fmt:message key="apk.pack" /></label></th>
				<td colspan="2"><form:input id="pack" path="pack" value="${apk.pack}" size="25" maxlength="50" tabindex="3"/></td>
			</tr>	
			<tr>
				<td>
					<fmt:message key="cmd.save" var="buttonSave" />
					<input type="submit" value="${buttonSave}" class="button"/>
				</td>
				<td>				
					<fmt:message key="cmd.clear" var="buttonCancel" />
					<input type="reset" value="${buttonCancel}" class="button"/>
				</td>
				<td>
					<a href="upload.html"><fmt:message  key="cmd.add" /></a>
				</td>	
				<td>
					<a href="index.html"><fmt:message  key="cmd.list" /></a>
				</td>
			</tr>				
		</table>
		<form:hidden path="id" value="${apk.id}" />
	</form:form>
	</section>
	</div>
	<c:if test="${not empty message}">
		<div id="message"><p>${message}</p></div>
	</c:if>		
</body>
</html>