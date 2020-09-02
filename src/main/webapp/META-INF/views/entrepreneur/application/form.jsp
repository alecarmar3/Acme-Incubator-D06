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
	<acme:form-textbox code="entrepreneur.application.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="entrepreneur.application.label.creationDate" path="creationDate" readonly="true"/>
	<acme:form-textarea code="entrepreneur.application.label.statement" path="statement" readonly="true"/>
	<acme:form-money code="entrepreneur.application.label.investmentMoneyOffer" path="investmentMoneyOffer" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.label.investor" path="investor.userAccount.username" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.label.investmentRound" path="investmentRound.title" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.label.status" path="status" readonly="true"/>
	<acme:form-textarea code="entrepreneur.application.label.justification"  path="justification"/>
	
	
	<acme:form-submit code="entrepreneur.application.button.accept" test="${status=='PENDING'}" action="/entrepreneur/application/update-accept?id=${id}"/>
  	<acme:form-submit code="entrepreneur.application.button.reject" test="${status=='PENDING'}" action="/entrepreneur/application/update-reject?id=${id}"/>
  	
  	<acme:form-return code="entrepreneur.application.button.return"/>
</acme:form>
