<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>
    <title>Влизане в системата</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="sticky">
<jsp:include page="header.jsp"/>
<section class="parallax-window" id="short" data-parallax="scroll" data-image-src="http://placehold.it/1400x300"
         data-natural-width="1400" data-natural-height="300">
    <div id="sub_header">
        <div class="container" id="sub_content">
            <div class="row">
                <div class="col-md-12">
                    <h1>Над 69 рецепти ;)</h1>
                    <div class="bread-crums">
                        <a href="${pageContext.request.contextPath}/home">Начало</a>
                        <span class="bread-crums-span">&raquo;</span>
                        <span class="current">регистрация</span>
                    </div><!-- End bread-crums -->
                </div>
            </div><!-- End row -->
        </div><!-- End container -->
    </div>
</section>

<div class="white_bg">
    <div class="container margin_60">
        <div class="main_title">
            <h2 class="nomargin_top">Потребителски вход</h2>
            <hr class="divider">
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="login-app">
                    <h3>Изтегли своята джобна готварска книга сега ! </h3>
                    <a href="#" target="_blank" class="btn btn-color1 solid small bdr-2 round mr-6">
                        <i class="fab fa-android"></i> Moussakapp
                    </a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-login">
                    <form method="post" action="" id="login">
                        <div class="form-group">
                            <div class="row">
                                <h3 id="message"></h3>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="validationCustomUsername">Потребителско име<span
                                    class="text-danger"> *</span></label>
                            <input type="email" class="form-control" id="validationCustomUsername" name="username"
                                   placeholder="e-mail" required>
                        </div>

                        <div class="form-group">
                            <label for="validationCustomPassword">Парола<span class="text-danger"> *</span></label>
                            <input type="password" class="form-control" id="validationCustomPassword" name="password"
                                   placeholder="Поне 8 символа" required>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-xs-6 text-left">
                                <button type="submit" class="btn  btn-primary">
                                    <i id="spinner"
                                       class="fa fa-refresh fa-spin"
                                       style="display:none"></i> Влез
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div><!-- End container -->
</div><!-- End white_bg -->
<jsp:include page="footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/login_ajax.js"></script>
</body>
</html>

