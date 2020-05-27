
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css"/>
</head>
<body>
<%--<jsp:useBean id="loggedUser" scope="request" class="services.models.UserServiceModel"/>--%>
<header>
    <section>
        <c:choose>
            <c:when test="${not empty sessionScope.loggedUser}">
                <a href="${pageContext.request.contextPath}/logout">Изход</a>
                <a href="${pageContext.request.contextPath}/admin/profile">
                    <c:out value="${sessionScope.loggedUser.username}"/>
                </a>
<%--                <a href="${pageContext.request.contextPath}/users/all">Потребители</a>--%>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/register">Регистрация</a>
                <a href="${pageContext.request.contextPath}/login">Вход</a>
<%--                <a href="${pageContext.request.contextPath}/users/all">Потребители</a>--%>
            </c:otherwise>
        </c:choose>
    </section>
</header>
</body>
</html>