$(document).ready(function () {
    let saveButton = $("#save");
    let picture = $("#picture");


    $("#upload").submit(function (e) {
        e.preventDefault();

        let spinner = $("#spinner");
        spinner.show();

        let title=$("#title").val();
        let description=$("#description").val();
        let ingredients=$('input[name="recipe_ingredient[]"]').map(function(){return $(this).val();}).get();
        let instructions=$('input[name="recipe_instructions[]"]').map(function(){return $(this).val();}).get();
        let category=$("#category").val();
        let prepareTime=$("#prepareTime").val();
        let cookingTime=$("#cookingTime").val();
        let servings=$("#servings").val();

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

        let message = $("#generalMessage");
        message.addClass("text-danger");

        let formData = new FormData($("#upload")[0]);

        if (picture.get(0).files && picture.get(0).files[0]) {
            formData.append('uploadedFile', picture.get(0).files[0]);
        }
        formData.append('json',JSON.stringify(data));

        $.ajax({
            url: '/admin/new/recipe/add',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                spinner.hide();

                if (data.success) {
                    message.removeClass("text-danger");
                    message.addClass("text-success");
                    message.html("Рецептата бе създадена успешно !").show();

                    setInterval(function () {
                        window.location.href = data.url;
                    },2000);
                } else {
                    message.html(data.error).show();
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

    picture.on('click touchstart', function () {
        $("#image").hide();
        $("#picture").val(null);
    });

    picture.change(function () {
        uploadImg(this);
    });
});