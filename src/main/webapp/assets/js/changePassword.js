$(document).ready(function() {
    let $form = $("form");
    let $newpass = $("#newpass");
    let $confirmpass = $("#confirmpass");

    function isPasswordStrong(password) {
        let hasUppercase = /[A-Z]/.test(password),
            hasLowercase = /[a-z]/.test(password),
            hasDigits = /\d/.test(password),
            hasSpecialChars = /[!@#$%^&*()_+{}[\]:;<>,.?~\\/-]/.test(password),
            isLongEnough = password.length >= 8;

        return hasUppercase && hasLowercase && hasDigits && hasSpecialChars && isLongEnough;
    }

    $form.submit(function(event) {
        event.preventDefault();

        let newpass = $newpass.val(),
            confirmpass = $confirmpass.val();

        if (isPasswordStrong(newpass) && newpass === confirmpass) {
            $.ajax({
                url: '/changepassword',
                method: "POST",
                data: {
                    newpass: newpass,
                    token: window.location.search.split('=')[1]
                },
                success: function (response) {
                    console.log(window.location.pathname + window.location.search)
                    if (response.startsWith("success:")) {
                        Swal.fire({
                            title: 'Thay đổi mật khẩu thành công!',
                            text: response.replace("success:", ""),
                            icon: 'success',
                            confirmButtonText: 'Đi đến Trang đăng nhập',
                            allowOutsideClick: false
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
        } else if (!isPasswordStrong(newpass)) {
            Swal.fire({
                title: 'Cảnh báo',
                text: 'Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt.',
                icon: 'warning',
                confirmButtonText: 'OK',
                showCloseButton: true
            })
        } else if (newpass !== confirmpass) {
            Swal.fire({
                title: 'Cảnh báo!',
                text: 'Mật khẩu mới và xác nhận mật khẩu mới không khớp.',
                icon: 'warning',
                confirmButtonText: 'OK',
                showCloseButton: true
            })
        }
    });
});