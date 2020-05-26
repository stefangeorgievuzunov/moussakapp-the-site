
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/auth.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="authForm">
    <form  method="post" action="${pageContext.request.contextPath}/register" class="needs-validation w-100 p-3" novalidate>
        <div class="form-group">
            <div class="form-row">
                <label for="validationCustomUsername">Потребителско име</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrepend">@</span>
                    </div>
                    <input type="email" class="form-control" id="validationCustomUsername" name="username" placeholder="e-mail" aria-describedby="inputGroupPrepend" required>
                    <div class="invalid-feedback">
                        Полето е задължително !
                    </div>
                </div>
            </div>
            <div class="form-row ">
                <div class="w-50 p-1">
                    <label for="validationCustom01">Първо име</label>
                    <input type="text" class="form-control" id="validationCustom01" name="firstName" placeholder="Първо име" required>
                    <div class="invalid-feedback">
                        Полето е задължително !
                    </div>
                </div>
                <div class="w-50 p-1">
                    <label for="validationCustom02">Фамилно име</label>
                    <input type="text" class="form-control" id="validationCustom02" name="lastName" placeholder="Фамилно име" required>
                    <div class="invalid-feedback">
                        Полето е задължително !
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="w-50 p-1">
                    <label for="validationCustom01">Парола</label>
                    <input type="password" class="form-control" id="validationCustom03" name="password" placeholder="Поне 8 символа" required>
                    <div class="invalid-feedback">
                        Полето е задължително !
                    </div>
                </div>
                <div class="w-50 p-1">
                    <label for="validationCustom02">Повторете паролата</label>
                    <input type="password" class="form-control" id="validationCustom04" name="rePassword" placeholder="Поне 8 символа" required>
                    <div class="invalid-feedback">
                        Полето е задължително !
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="w-50 p-1">
                    <label for="exampleFormControlSelect1">Години</label>
                    <select class="form-control" id="exampleFormControlSelect1">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <div class="w-50 p-1">
                    <label for="exampleFormControlSelect1">Пол</label>
                    <select class="form-control" id="exampleFormControlSelect2">
                        <option>Мъж</option>
                        <option>Жена</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <label for="exampleFormControlTextarea1">Разкажете за себе си.</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"  maxlength="100"></textarea>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Изпрати</button>
    </form>
</div>
<script>
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>

