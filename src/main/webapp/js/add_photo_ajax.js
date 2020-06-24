$(document).ready(function () {

    function uploadImg(input) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();

            let formData = new FormData($("#upload")[0]);
            formData.append('uploadedFile', input.files[0]);

            let saveButton = $("#save");
            saveButton.prop("disabled", true);

            let message = $("#message");
            message.addClass("text-danger");
            message.hide();

            reader.onload = function (e) {
                $("#image").attr('src', e.target.result);
            }

            $.ajax({
                url: '/admin/new/recipe/upload/image',
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data.success) {

                        reader.readAsDataURL(input.files[0]);
                        saveButton.prop("disabled", false);
                    } else {
                        message.html(data.error).show();
                    }
                },
                error: function (msg) {

                }
            });
        }
    }

    $("#picture").change(function () {
        uploadImg(this);
    });
});