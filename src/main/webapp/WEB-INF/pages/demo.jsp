<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo Application</title>
</head>
<body>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>


	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4 align="right">
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h4>
	</c:if>
	

    <form:form action="demo" commandName="item" method="POST">
    	<p>Id:
    	<form:input path="id" />
    	</p>
        <p>Content:
        <form:input path="content" />
        </p>
        <%-- <input type="hidden"                        
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/> --%>
        <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
    </form:form> 
    
	<br />

</body>
</html>