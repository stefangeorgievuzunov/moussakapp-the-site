<%--
  Created by IntelliJ IDEA.
  User: Stefan
  Date: 5/24/2020
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>
    <title>Title</title>
</head>
<body class="sticky">
    <jsp:include page="header.jsp"/>
    <h2>Hello,<c:out value="${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.lastName}"/></h2>
    <h2>All users registered:<c:out value="${requestScope.data}"/></h2>
    <jsp:include page="footer.jsp"/>
</body>
</html>
