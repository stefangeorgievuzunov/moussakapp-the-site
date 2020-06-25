$(document).ready(function () {
    let saveButton = $("#save");

    $("#upload").submit(function (e) {
        e.preventDefault();

        let title=$("#title").val();
        let description=$("#description").val();
        let ingredients=$('input[name="recipe_ingredient[]"]').map(function(){return $(this).val();}).get();
        let instructions=$('input[name="recipe_instructions[]"]').map(function(){return $(this).val();}).get();
        let category=$("#category").val();
        let prepareTime=$("#prepareTime").val();
        let cookingTime=$("#cookingTime").val();
        let servings=$("#servings").val();


        console.log("INGREDIENTS: "+ingredients);

        const data={
            title:title,
            description:description,
            ingredients:ingredients,
            instructions:instructions,
            category:category,
            prepareTime:prepareTime,
            cookingTime:cookingTime,
            servings:servings
        }


        let formData = new FormData($("#upload")[0]);

        if (picture.files && picture.files[0]) {
            formData.append('uploadedFile', picture.files[0]);
        }
        formData.append('json',JSON.stringify(data));

        $.ajax({
            url: '/admin/new/recipe/add',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                console.log(data);
                if (data.success) {
                    console.log(data);
                    console.log(data.url);
                } else {
                    console.log(data.error);
                }
            },
            error: function (msg) {

            }
        });
    });

    function uploadImg(input) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();
            let formData = new FormData($("#upload")[0]);

            formData.append('uploadedFile', input.files[0]);

            saveButton.prop("disabled", true);

            let message = $("#message");
            message.addClass("text-danger");
            message.hide();

            reader.onload = function (e) {
                $("#image").attr('src', e.target.result).show();
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

    let picture = $("#picture");

    picture.on('click touchstart', function () {
        $("#image").hide();
        $("#picture").val(null);
    });

    picture.change(function () {
        uploadImg(this);
    });
});