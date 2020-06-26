<%--
  Created by IntelliJ IDEA.
  User: Stefan
  Date: 6/1/2020
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Нова рецепта</title>
</head>
<body class="sticky">
<jsp:include page="header.jsp"/>
<section class="parallax-window" id="short" data-parallax="scroll" data-image-src="http://placehold.it/1400x300"
         data-natural-width="1400" data-natural-height="300">
    <div id="sub_header">
        <div class="container" id="sub_content">
            <div class="row">
                <div class="col-md-12">
                    <h1>Добави нова рецепта</h1>
                    <div class="bread-crums">
                        <a href="${pageContext.request.contextPath}/home">Начало</a>
                        <span class="bread-crums-span">&raquo;</span>
                        <span class="current">Страници</span>
                        <span class="bread-crums-span">&raquo;</span>
                        <span class="current">Добави Рецепта</span>
                    </div><!-- End bread-crums -->
                </div>
            </div><!-- End row -->
        </div><!-- End container -->
    </div>
</section>
<!-- End SubHeader -->
<div class="white_bg create-recipe">
    <div class="container margin_60">
        <div class="main_title">
            <h2 class="nomargin_top">Нова рецепта</h2>
            <hr class="divider">
        </div>
        <div class="row">
            <form method="post" action="" id="upload" enctype="multipart/form-data">
                <div class="col-md-8">
                    <div class="form-group">
                        <div class="row">
                            <h3 id="generalMessage"></h3>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <label for="title">Заглавие<span
                                    class="text-danger"> *</span></label>
                            <input type="text" id="title" name="recipeTitle" class="form-control"
                                   placeholder="Заглавие на рецепта" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description">Описание</label>
                        <textarea id="description" name="recipeDescription" class="form-control" rows="8"></textarea>
                    </div>
                    <div class="form-group">
                        <div class="recipe-ingredient">
                            <div class="row-recipe-ingredient">
                                <div class="col-recipe-ingredient">
                                    <i class="ic icon-weight"></i>
                                    <input type="text" class="form-control" placeholder="Съставки"
                                           name="recipe_ingredient[]">
                                </div>
                                <div class="col-recipe-ingredient">
                                    <i class="ic icon-weight"></i>
                                    <input type="text" class="form-control" placeholder="Съставки"
                                           name="recipe_ingredient[]">
                                    <span class="remove-recipe-col"><i class="far fa-times-circle"></i></span>
                                </div>
                                <div class="col-recipe-ingredient">
                                    <i class="ic icon-weight"></i>
                                    <input type="text" class="form-control" placeholder="Съставки"
                                           name="recipe_ingredient[]">
                                    <span class="remove-recipe-col"><i class="far fa-times-circle"></i></span>
                                </div>
                                <div class="col-recipe-ingredient">
                                    <i class="ic icon-weight"></i>
                                    <input type="text" class="form-control" placeholder="Съставки"
                                           name="recipe_ingredient[]">
                                    <span class="remove-recipe-col"><i class="far fa-times-circle"></i></span>
                                </div>
                            </div>
                            <button type="button" class="btn btn-addnew-ingredient"><i class="far fa-plus-square"></i>
                                Добави нова съставка
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="recipe-instructions">
                            <div class="row-recipe-instructions">
                                <div class="col-recipe-instructions">
                                    <i class="ic icon-manual-madrab"></i>
                                    <input type="text" class="form-control" placeholder="Инструкции"
                                           name="recipe_instructions[]">
                                </div>
                                <div class="col-recipe-instructions">
                                    <i class="ic icon-manual-madrab"></i>
                                    <input type="text" class="form-control" placeholder="Инструкции"
                                           name="recipe_instructions[]">
                                    <span class="remove-recipe-col"><i class="far fa-times-circle"></i></span>
                                </div>
                                <div class="col-recipe-instructions">
                                    <i class="ic icon-manual-madrab"></i>
                                    <input type="text" class="form-control" placeholder="Инструкции"
                                           name="recipe_instructions[]">
                                    <span class="remove-recipe-col"><i class="far fa-times-circle"></i></span>
                                </div>
                                <div class="col-recipe-instructions">
                                    <i class="ic icon-manual-madrab"></i>
                                    <input type="text" class="form-control" placeholder="Инструкции"
                                           name="recipe_instructions[]">
                                    <span class="remove-recipe-col"><i class="far fa-times-circle"></i></span>
                                </div>
                            </div>
                            <button type="button" class="btn btn-addnew-instruction"><i class="far fa-plus-square"></i>
                                Добави нова инструкция
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="category">Категория<span
                                class="text-danger"> *</span></label>
                        <select class="form-control" id="category" required>
                            <option>Десерт</option>
                        </select>
                    </div>
                </div>
                <aside class="col-md-4 sidebar sticky-sidebar">
                    <div class="theiaStickySidebar">
                        <div class="form-group">
                            <h3 id="message"></h3>
                            <img src="" alt="" id="image" style="width: 200px ; height: 200px; object-fit: cover;">
                        </div>
                        <div class="form-group">
                            <div class="add-photo">
                                <label class="icon-picture gray">
                                    <input type="file" id="picture"/>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="prepareTime">Време за приготвяне /минути/<span
                                    class="text-danger"> *</span></label>
                            <input type="text" id="prepareTime" name="prepTime" class="form-control"
                                   placeholder="Време за приготвяне" required>
                        </div>
                        <div class="form-group">
                            <label for="cookingTime">Време за готвене /минути/<span
                                    class="text-danger"> *</span></label>
                            <input type="text" id="cookingTime" name="cookTime" class="form-control"
                                   placeholder="Време за готвене" required>
                        </div>
                        <div class="form-group">
                            <label for="servings">Порции<span
                                    class="text-danger"> *</span></label>
                            <input type="text" id="servings" name="servings" class="form-control"
                                   placeholder="Брой порции" required>
                        </div>
                        <div class="form-group">
                            <div class="recipe-button">
                                <button type="submit" class="btn btn-primary">
                                    <i id="spinner"
                                       class="fa fa-refresh fa-spin"
                                       style="display:none"></i> Запази
                                </button>
                            </div>
                        </div>
                    </div>
                </aside>
            </form>
        </div>
    </div><!-- End container -->
</div><!-- End white_bg -->
<jsp:include page="footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/add_new_recipe_ajax.js"></script>
</body>
</html>
