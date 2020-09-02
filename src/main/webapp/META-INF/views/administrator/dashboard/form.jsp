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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<acme:form-panel code="administrator.dashboard.show.generalData"/>
<acme:form readonly="true">

<acme:form-double code="administrator.dashboard.totalNotices" path="totalNotices"/>
<acme:form-double code="administrator.dashboard.totalTechnologyRecords" path="totalTechnologyRecords"/>
<acme:form-double code="administrator.dashboard.totalToolRecords" path="totalToolRecords"/>
<acme:form-double code="administrator.dashboard.minimumMoneyIntervalActiveInquires" path="minimumMoneyIntervalActiveInquires"/>
<acme:form-double code="administrator.dashboard.maximumMoneyIntervalActiveInquires" path="maximumMoneyIntervalActiveInquires"/>
<acme:form-double code="administrator.dashboard.averageMoneyIntervalActiveInquires" path="averageMoneyIntervalActiveInquires"/>
<acme:form-double code="administrator.dashboard.deviationMoneyIntervalActiveInquires" path="deviationMoneyIntervalActiveInquires"/>
<acme:form-double code="administrator.dashboard.minimumMoneyIntervalActiveOvertures" path="minimumMoneyIntervalActiveOvertures"/>
<acme:form-double code="administrator.dashboard.maximumMoneyIntervalActiveOvertures" path="maximumMoneyIntervalActiveOvertures"/>
<acme:form-double code="administrator.dashboard.averageMoneyIntervalActiveOvertures" path="averageMoneyIntervalActiveOvertures"/>
<acme:form-double code="administrator.dashboard.deviationMoneyIntervalActiveOvertures" path="deviationMoneyIntervalActiveOvertures"/>
</acme:form>


<%-- <acme:form-panel code="administrator.dashboard.show.TechnologyChart"/>
<div>
<canvas id="canvas"></canvas>
</div>

<acme:form-panel code="administrator.dashboard.show.ToolChart"/>
<div>
<canvas id="canvasTool"></canvas>
</div>

<script type="text/javascript">
 $(document).ready(function(){
	 
	var dataTech = {
			labels : [
				"Technology", "Science", "Arts", "Business", "Health" 
			],
			datasets : [
				{
					data : [
						<jstl:out value="&{totalTechnologiesByTechnologySector}"/>
						/* <jstl:out value="&{totalTechnologiesByScienceSector}"/>
						<jstl:out value="&{totalTechnologiesByArtsSector}"/>
						<jstl:out value="&{totalTechnologiesByBusinessSector}"/>
						<jstl:out value="&{totalTechnologiesByHealthSector}"/> */
					]
				}
			]
	};
	
	var optionsTech = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax: 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
	};
	
	var canvasTech, contextTech;
	
	canvasTech = document.getElementById("canvasTech");
	contextTech = canvas.getContext("2d");
	new Chart(contextTech, {
		type : "bar",
		data : dataTech,
		options: optionsTech
	});
	
	
	/* var dataTool = {			 
			labels : labelsTool,
			datasets : [
				{
					data : dataTool
				}
			]
	};
	
	var optionsTool = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax: 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
	};
	
	var canvasTool, contextTool;
	
	canvasTool = document.getElementById("canvasTool");
	contextTool = canvasTool.getContext("2d");
	new Chart(contextTool, {
		type : "bar",
		data : dataTool,
		options: optionsTool
	}); */
});
	 
</script> --%>
