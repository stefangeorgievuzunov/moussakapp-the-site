<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <h2><c:out value="${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.lastName} is here"/></h2>
<jsp:include page="footer.jsp"/>
</body>
</html>
