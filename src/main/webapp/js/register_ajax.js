$(document).ready(function () {

    $("#register").submit(function (e) {
        e.preventDefault();

        let spinner = $("#spinner");
        spinner.show();

        let username = $("#validationCustomUsername").val();
        let password = $("#validationCustomPassword").val();
        let rePassword = $("#validationCustomRePassword").val();
        let firstName = $("#validationCustomFirstName").val();
        let lastName = $("#validationCustomLastName").val();

        let message = $("#message");
        message.addClass("text-danger");

        if (password.length < 8) {

            spinner.hide();
            message.html("Паролата трябва да състои от поне 8 символа");
        } else {
            if (password !== rePassword) {

                spinner.hide();
                message.html("Паролите не съвпадат");
            } else {
                message.hide();

                const data = {
                    username: username,
                    password: password,
                    firstName: firstName,
                    lastName: lastName
                }

                $.ajax({
                    url: '/register/authorization',
                    type: 'post',
                    data: JSON.stringify(data),
                    dataType: 'json',
                    contentType: 'application/json;charset=utf-8',
                    success: function (data) {
                        spinner.hide();

                        if (data.success) {
                            message.removeClass("text-danger");
                            message.addClass("text-success");
                            message.html("Вие се регистрирахте успешно !").show();

                            setInterval(function () {
                                window.location.href = data.url;
                            },3000);

                        } else {
                            message.html(data.error).show();
                        }
                    },
                    error: function (msg) {
                        alert('error');
                    }
                });
            }
        }
    })
});