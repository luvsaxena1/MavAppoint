<jsp:include page='<%=(String) request.getAttribute("includeHeader")%>' />
<%@ page import="uta.mav.appoint.login.LoginUser"%>
<div class="container">
	<div class="jumbotron masthead">
		<img src="img/mavlogo.gif" style=";padding:30px;float:left;">
		<h1><font style="color: #1e59ce;font-size:72px;"> Mav Appointment </font></h1>
		<p>University Advising System For Mavericks</p>
		
		<% LoginUser user = (LoginUser)session.getAttribute("user");
		
		if(user==null){%>
		<a href="advising" class="btn btn-primary btn-lg">Make an appointment Now!</a>
		<%	}else if(user.getEmail().equals("")){%>
			<a href="advising" class="btn btn-primary btn-lg">Schedule your appointment Here!</a>
		<% }
		else if(!user.getRole().equalsIgnoreCase("advisor")&&!user.getRole().equalsIgnoreCase("admin")){
	    			%>		
		<a href="advising" class="btn btn-primary btn-lg">Schedule your appointment Here!</a>
		<%	}
	    			%>
	</div>
</div>

<%@include file="templates/footer.jsp"%>