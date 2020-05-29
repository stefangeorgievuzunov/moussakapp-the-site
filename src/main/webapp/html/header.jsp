
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <title>Recipes Food - Food & Recipes Food Template</title>
    <meta name="description" content="Recipes Food - Food & Recipes Food Template">
    <meta name="keywords" content="recipes food, recipes , sushi, chinese, italian food">
    <meta name="author" content="themearabia.net">

    <!-- Mobile Specific Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- Main Style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

    <!-- customize CSS Style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customize.css">

    <!-- Responsive Style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">

    <!-- Favicons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png">

    <!-- Skins -->
    <!-- just remove the below comments witch color skin you want to use -->
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/skins/blue.css">--%>
    <!--<link rel="stylesheet" href="css/skins/yellow.css">-->
    <!--<link rel="stylesheet" href="css/skins/green.css">-->
    <!--<link rel="stylesheet" href="css/skins/cyan.css">-->
    <!--<link rel="stylesheet" href="css/skins/orange.css">-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/skins/pink.css">
    <!--<link rel="stylesheet" href="css/skins/purple.css">-->
    <!--<link rel="stylesheet" href="css/skins/red.css">-->
    <!--<link rel="stylesheet" href="css/skins/lactic.css">-->
    <!--<link rel="stylesheet" href="css/skins/darkred.css">-->
</head>
<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-5 col-xs-6">
                    <a href="${pageContext.request.contextPath}/" id="logo">
                        <img src="${pageContext.request.contextPath}/images/logo.png" alt="">
                    </a>
                </div>
                <nav class="col-md-9 col-sm-7 col-xs-6">
                    <a class="np-toggle-switch np-toggle-switch-nxp open_close" href="javascript:void(0);"><span>Menu mobile</span></a>
                    <div class="main-menu" id="main-menu">
                        <div id="header_menu">
                            <img src="${pageContext.request.contextPath}/images/logo-menu.png" alt="">
                        </div>
                        <a href="#" class="open_close" id="close_in"><i class="icon_close"></i></a>
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/home" ><i class="icon_house_alt"></i> Начало </a>
                            </li>
                            <li><a href="recipes.html"><i class="ic icon-recipes"></i> Рецепти</a></li>
                            <li class="submenu">
                                <a href="javascript:void(0);" class="show-submenu"><i class="ic icon-plus-circle"></i> Страници <i class="fas fa-angle-down"></i></a>
                                    <ul>
                                        <c:if test="${empty sessionScope.loggedUser}">
                                            <li><a href="${pageContext.request.contextPath}/login"><i class="fas fa-sign-in-alt"></i> Потребителски вход</a></li>
                                            <li><a href="${pageContext.request.contextPath}/register"><i class="fas fa-user-plus"></i> Регистрация</a></li>
                                        </c:if>
                                        <li><a href="${pageContext.request.contextPath}/admin/new/recipe"><i class="ic icon-recipes"></i> Добави Рецепта</a></li>
                                        <li><a href="page-right-sidebar.html"><i class="far fa-file-alt"></i> Добави статия</a></li>
                                    </ul>
                            </li>
                            <c:if test="${not empty sessionScope.loggedUser}">
                                <li class="submenu">
                                    <a href="javascript:void(0);" class="show-submenu"><i class="ic icon-chefs"></i><c:out value="${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.lastName}"/><i class="fas fa-angle-down"></i></a>
                                    <ul>
                                        <li><a href="${pageContext.request.contextPath}/admin/profile">Моят профил</a></li>
                                        <li><a href="${pageContext.request.contextPath}/admin/new/recipe">Моите рецепти</a></li>
                                        <li><a href="page-right-sidebar.html">Моите статии</a></li>
                                        <li><a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Излез</a></li>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </header>
</body>
</html>