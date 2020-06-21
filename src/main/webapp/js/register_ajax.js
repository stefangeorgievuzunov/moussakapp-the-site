$(document).ready(function () {

    $("#register").submit(function (e) {
        e.preventDefault();

        let username = $("#validationCustomUsername").val();
        let password = $("#validationCustomPassword").val();
        let rePassword = $("#validationCustomRePassword").val();
        let firstName = $("#validationCustomFirstName").val();
        let lastName = $("#validationCustomLastName").val();

        if (password.length < 8) {
            $("#error").html("Паролата трябва да състои от поне 8 символа")
        } else {
            if (password !== rePassword) {
                $("#error").html("Паролите не съвпадат")
            } else {
                $("#error").hide();

                const data = {
                    username: username,
                    password: password,
                    firstName: firstName,
                    lastName: lastName
                }

                console.log(data);

                $.ajax({
                    url: '/register/authorization',
                    type: 'post',
                    data: JSON.stringify(data),
                    dataType: 'json',
                    contentType: 'application/json',

                    success: function (data) {
                        console.log(data);
                    },
                    error: function (msg) {
                        alert('error');
                    }
                });
            }
        }
    })
});