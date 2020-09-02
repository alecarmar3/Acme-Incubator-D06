
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.bookkeeper-request.label.firmName" path="firmName" width="20%"/>
	<acme:list-column code="administrator.bookkeeper-request.label.responsibilityStatement" path="responsibilityStatement" width="20%"/>
	<acme:list-column code="administrator.bookkeeper-request.label.status" path="status" width="60%"/>
</acme:list>