$(document).ready(function () {

    function uploadImg(input){
        if (input.files && input.files[0]){
            let reader=new FileReader();

            let formData = new FormData($("#upload")[0]);
            formData.append('uploadedFile', input.files[0]);

            $("#save").prop("disabled", true);

            let message = $("#message");
            message.addClass("text-danger");

            reader.onload=function (e) {
                console.log("E TARGET RESULT: "+e.target.result);
               $("#image").attr('src',e.target.result);
            }

            $.ajax({
                url: '/admin/new/recipe/upload/image',
                enctype: 'multipart/form-data',
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data.success) {
                        console.log(data);
                        console.log("input.files"+input.files[0]);
                        console.log("formdata file: "+formData.get('uploadedFile'));

                        reader.readAsDataURL(input.files[0]);

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