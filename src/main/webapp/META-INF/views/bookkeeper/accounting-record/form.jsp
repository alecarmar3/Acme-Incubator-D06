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
	<acme:form-textbox code="bookkeeper.accountingRecord.label.title" path="title"/>
	<acme:form-textarea code="bookkeeper.accountingRecord.label.body" path="body"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="bookkeeper.accountingRecord.label.title" path="title" readonly="true"/>
	<acme:form-textarea code="bookkeeper.accountingRecord.label.body" path="body" readonly="true"/>
	<acme:form-textarea code="bookkeeper.accountingRecord.label.finalMode" path="finalMode" readonly="true"/>
	<acme:form-moment code="bookkeeper.accountingRecord.label.creationDate" path="creationDate" readonly="true"/>
	<acme:form-textbox code="bookkeeper.accountingRecord.label.bookkeeper" path="bookkeeper.userAccount.username" readonly="true"/>
	<acme:form-textbox code="bookkeeper.accountingRecord.label.investmentRound" path="investmentRound.title" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${!finalMode}">
  	<acme:form-submit test="${command == 'show'}" code="bookkeeper.accountingRecord.button.saveInFinalMode" action="/bookkeeper/accounting-record/update"/>
	</jstl:if>
	<acme:form-submit code="bookkeeper.accountingRecord.button.create" test="${command=='create'}" action="/bookkeeper/accounting-record/create?InvestmentRoundId=${investmentRound.id}"/>
  	<acme:form-return code="bookkeeper.accountingRecord.button.return"/>
</acme:form>
