<%--
  Created by IntelliJ IDEA.
  User: Stefan
  Date: 5/24/2020
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="user" scope="request" type="services.models.UserServiceModel"/>
    <h2>Hello,<c:out value="${user.firstName} ${user.lastName}"/></h2>
</body>
</html>
