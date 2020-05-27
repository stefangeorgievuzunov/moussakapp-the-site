<%--
  Created by IntelliJ IDEA.
  User: Stefan
  Date: 5/26/2020
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<h2>Welcome to <c:out value="${requestScope.viewedUser.firstName}'s"/> profile :) </h2>

</body>
</html>
