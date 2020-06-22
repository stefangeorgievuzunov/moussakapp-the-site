$(document).ready(function () {

    $("#login").submit(function (e) {
        e.preventDefault();

        let spinner = $("#spinner");
        spinner.show();

        let username = $("#validationCustomUsername").val();
        let password = $("#validationCustomPassword").val();

        let message = $("#message");
        message.addClass("text-danger");

        const data = {
            username: username,
            password: password
        }

        $.ajax({
            url: '/login/authorization',
            type: 'post',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json;charset=utf-8',
            success: function (data) {
                spinner.hide();

                if (data.success) {
                    window.location.href = data.redirect;
                } else {
                    message.html(data.error).show();
                }
            },
            error: function (msg) {
                alert('error');
            }
        });
    })
});