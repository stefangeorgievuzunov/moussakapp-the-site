<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>
    <title>Профил</title>
</head>
<body class="sticky">
<jsp:include page="header.jsp"/>
    <div class="white_bg">
        <c:forEach items="${requestScope.recipes}" var="recipe">
        <div class="container margin_60">
            <div class="row">
                    <div class="col-md-8">
                        <div class="post-inner">
                            <div class="info-icons">
                                <div class="semipart">
                                    <i class="ic icon-x-forks"></i> Приготвяне ${recipe.prepareTime} минути
                                </div>
                                <div class="semipart">
                                    <i class="ic icon-timer"></i> Готвене ${recipe.cookingTime} минути
                                </div>
                                <div class="semipart">
                                    <i class="ic icon-community2"></i> ${recipe.servings} Порции
                                </div>
                            </div>

                            <div class="post-img">
                                <img src="data:image;base64,${recipe.avatar.base64Image}" style="width: 300px ; height: 300px; object-fit: contain;"/>
                            </div>

                            <h3 class="post-title">${recipe.title}</h3>
                            <div class="post-meta">
                                <span class="meta-author"><i class="far fa-user"></i><a
                                        href="#">${recipe.author.firstName} ${recipe.author.lastName} </a></span>
                            </div>

                            <div class="post-content">
                                <h4>Описание</h4>

                                <p>${recipe.description}</p>

                                <h4>Съставки</h4>
                                <div class="ul_list">
                                    <ul>
                                        <c:forEach items="${recipe.ingredients}" var="ingredient" begin="1"
                                                   end="${recipe.ingredients.size()+1}" varStatus="loop">
                                            <li>${loop.index}. ${ingredient.name}</li>
                                        </c:forEach>
                                    </ul>
                                </div><!-- End Ingredient -->

                                <h4>Инструкции</h4>
                                <div class="ul_list_default">
                                    <ol>
                                        <c:forEach items="${recipe.instructions}" var="instruction" begin="1"
                                                   end="${recipe.instructions.size()+1}" varStatus="loop">
                                            <li>${loop.index}. ${instruction.name}</li>
                                        </c:forEach>
                                    </ol>
                                </div><!-- End Instructions -->
                            </div>
                        </div>
                    </div>
                    <aside class="col-md-4 sidebar sticky-sidebar">
                        <div class="theiaStickySidebar">

                            <div class="widget widget-list">
                                <div class="widget-title">
                                    <i class="far fa-folder-open"></i> Категория
                                </div>
                                <ul>
                                    <li class="cat-item"><a href="#">${recipe.category.name}</a></li>
                                </ul>
                            </div><!-- End widget -->
                        </div>
                    </aside>
            </div><!-- End row -->
        </div><!-- End container -->
        </c:forEach>
    </div>
    <!-- End white_bg -->
<jsp:include page="footer.jsp"/>
</body>
</html>
