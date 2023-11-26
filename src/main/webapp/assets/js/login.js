$(document).ready(function() {
    $("form").submit(function(event) {
        event.preventDefault();

        let email = $("#email").val(),
            password = $("#password").val();

        $.ajax({
            url: "/login",
            method: "POST",
            data: { email: email, password: password},
            success: function (response) {
                if (response.startsWith("redirect:")) {
                    window.location.href = response.replace("redirect:", "");
                } else if (response.startsWith("error:")) {
                    Swal.fire({
                        title: 'Lỗi!',
                        text: response.replace("error:", ""),
                        icon: 'error',
                        confirmButtonText: 'OK',
                        showCloseButton: true
                    });
                }
            }
        })
    })

    $(".txt_field input").on("input", function() {
        if ($(this).val().trim() !== "") {
            $(this).parent().addClass("has-content");
        } else {
            $(this).parent().removeClass("has-content");
        }
    });
})