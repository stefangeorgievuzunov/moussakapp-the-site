$(document).ready(function () {

    $("#picture").change(function () {

        let formData=new FormData($("#upload")[0]);
        formData.append('uploadedFile', $('input[type=file]')[0].files[0]);

        $("#save").prop("disabled",true);

        $.ajax({
            url: '/admin/new/recipe/upload/image',
            enctype: 'multipart/form-data',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {

            },
            error: function (msg) {

            }
        });
    });
});