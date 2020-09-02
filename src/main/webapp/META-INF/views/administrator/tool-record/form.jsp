<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.tool-record.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.tool-record.form.label.activity-sector" path="activitySector"/>
	<acme:form-textbox code="administrator.tool-record.form.label.inventor-name" path="inventorName"/>
	<jstl:if test="${command !='update' }">
	<acme:form-moment code="administrator.tool-record.form.label.update-date" path="updateDate" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="administrator.tool-record.form.label.description" path="description"/>
	<acme:form-url code="administrator.tool-record.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.tool-record.form.label.email" path="email"/>
	
	<jstl:choose>
		<jstl:when test="${sourceType.equals('ClosedSource')}">
			<acme:form-select code="administrator.technology-record.form.label.source-type" path="sourceType">
				<acme:form-option code="administrator.technology-record.form.label.source-type.open-source" value="OpenSource"/>
				<acme:form-option code="administrator.technology-record.form.label.source-type.closed-source" value="ClosedSource" selected="true"/>
			</acme:form-select>
		</jstl:when>
		<jstl:otherwise>
			<acme:form-select code="administrator.technology-record.form.label.source-type" path="sourceType">
				<acme:form-option code="administrator.technology-record.form.label.source-type.open-source" value="OpenSource" selected="true"/>
				<acme:form-option code="administrator.technology-record.form.label.source-type.closed-source" value="ClosedSource"/>
			</acme:form-select>
		</jstl:otherwise>
	</jstl:choose>

	<acme:form-integer code="administrator.tool-record.form.label.stars" path="stars"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.tool-record.form.button.update" action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.tool-record.form.button.delete" action="/administrator/tool-record/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.tool-record.form.button.create" action="/administrator/tool-record/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.tool-record.form.button.update" action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.tool-record.form.button.delete" action="/administrator/tool-record/delete"/>
	
  	<acme:form-return code="administrator.tool-record.form.button.return"/>
	
</acme:form>
