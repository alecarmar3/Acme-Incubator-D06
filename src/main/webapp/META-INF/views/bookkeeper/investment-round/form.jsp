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
	<acme:form-textbox code="bookkeeper.investmentRound.label.ticker" path="ticker"/>
	<acme:form-textbox code="bookkeeper.investmentRound.label.finalMode" path="finalMode"/>
	<acme:form-moment code="bookkeeper.investmentRound.label.creationDate" path="creationDate"/>
	<acme:form-textbox code="bookkeeper.investmentRound.label.kindOfRound" path="kindOfRound"/>
	<acme:form-textbox code="bookkeeper.investmentRound.label.title" path="title"/>
	<acme:form-textarea code="bookkeeper.investmentRound.label.description" path="description"/>
	<acme:form-money code="bookkeeper.investmentRound.label.amountOfMoney" path="amountOfMoney"/>
	<acme:form-url code="bookkeeper.investmentRound.label.additionalInfo" path="additionalInfo"/>
	<acme:form-textbox code="bookkeeper.investmentRound.label.entrepreneur" path="entrepreneur.userAccount.username"/>
	
	<acme:form-submit code="authenticated.investmentRound.accountingRecords" method="get" action="/authenticated/accounting-record/list-its?id=${id}"/>
  	<acme:form-submit code="authenticated.investmentRound.workProgramme" method="get" action="/authenticated/activity/list?id=${id}"/>
  	<acme:form-return code="bookkeeper.investmentRound.button.return"/>
</acme:form>
