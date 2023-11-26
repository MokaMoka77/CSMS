$(document).ready(function() {
    $("form").submit(function(event) {
        event.preventDefault();

        let email = $("#email").val();

        $.ajax({
            url: "/forgotpassword",
            method: "POST",
            data: { email: email },
            success: function (response) {
                if (response.startsWith("success:")) {
                    Swal.fire({
                        title: 'Gửi email thành công',
                        text: response.replace("success:", ""),
                        icon: 'success',
                        confirmButtonText: 'Về lại Trang đăng nhập',
                        showCloseButton: true
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.replace("login");
                        }
                    });
                } else if (response.startsWith("error:")) {
                    Swal.fire({
                        title: 'Lỗi!',
                        text: response.replace("error:", ""),
                        icon: 'error',
                        confirmButtonText: 'OK',
                        showCloseButton: true
                    })
                }
            }
        });
    });

    $(".txt_field input").on("input", function() {
        if ($(this).val().trim() !== "") {
            $(this).parent().addClass("has-content");
        } else {
            $(this).parent().removeClass("has-content");
        }
    });
});