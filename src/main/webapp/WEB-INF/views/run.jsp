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
		<h1><fmt:message key="tittle.run" /></h1>
		<section>
		<form id="fileUploadForm" action="run.html" method="POST">
	    <table border="0">
			<tr>		
				<th><label for="file"><fmt:message key="apk.file" /></label></th>
				<td>
					<select name="id"  tabindex="1">
					<c:forEach var="apk" items="${apkList}">
						<option value="${apk.id}">${apk.name} (${apk.file})</option>
					</c:forEach>
					</select>
					</td>
			</tr>				
			<tr>			
				<th><label for=name><fmt:message key="apk.command" /></label></th>
				<td>
					<select name="cmd"  tabindex="2">
					<c:forEach var="cmd" items="${cmdList}">
						<option value="${cmd}"><fmt:message key="cmd.apk.${cmd}"/></option>
					</c:forEach>
					</select>
				</td>
			</tr>		
			<tr>
				<td>
					<fmt:message key="cmd.run" var="buttonSave" />
					<input type="submit"  value="${buttonSave}" class="button"/>
				</td>	
				<td>
					<a href="index.html"><fmt:message  key="cmd.list" /></a>
				</td>
			</tr>
		</table>						
		</form>
		</section>
	</div>
	<c:if test="${not empty message}">
		<div id="message"><p>${message}</p></div>
	</c:if>		
</body>
</html>