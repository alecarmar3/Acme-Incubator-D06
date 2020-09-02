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
	<jstl:if test="${command == 'create'}">
	<acme:form-textbox code="authenticated.forum.label.title" path="title"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="authenticated.forum.label.title" path="title" readonly="true"/>
	<acme:form-moment code="authenticated.forum.label.creationDate" path="creationDate" readonly="true"/>
	<acme:form-textbox code="authenticated.forum.label.investmentRound" path="investmentRound.title" readonly="true"/>
	</jstl:if>

	<acme:form-submit test="${command == 'show'}" code="authenticated.forum.button.getMessages" method="get" action="/authenticated/message/list?id=${id}"/>
	<acme:form-submit test="${command == 'show'}" code="authenticated.forum.button.postMessage" method="get" action="/authenticated/message/create?ForumId=${id}"/>
	<acme:form-submit test="${command != 'create' && isOwner}" code="authenticated.participates-in.button.add-participant" method="get" action="/authenticated/participates-in/add-participant?forumId=${id}"/>
	<acme:form-submit test="${command != 'create' && isOwner}" code="authenticated.forum.button.list-participants" method="get" action="/authenticated/participates-in/list-participants?forumId=${id}"/>
	<acme:form-submit test="${command == 'show' && isOwner}" code="authenticated.forum.button.delete" action="/authenticated/forum/delete"/>
	<acme:form-submit test="${command == 'create'}" code="authenticated.forum.button.create" action="/authenticated/forum/create?InvestmentRoundId=${investmentRound.id}"/>
	<acme:form-return code="authenticated.forum.button.return"/>
</acme:form>
