<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <h2><c:out value="${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.lastName} is here bitches"/></h2>
</body>
</html>
