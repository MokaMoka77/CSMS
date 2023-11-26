<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Quên mật khẩu - CSMS</title>
        <link href="./assets/css/styles.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="./assets/image/favicon.png">
        <script src="./assets/js/feather.min.js"></script>
        <script src="./assets/js/sweetalert2.all.min.js"></script>
        <script src="./assets/js/jquery.min.js"></script>
        <script src="./assets/js/forgotPassword.js"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="center">
                        <h1>QUÊN MẬT KHẨU</h1>
                        <div class="px-1 pb-4">
                            <form action="forgotpassword" method="post">
                                <div class="txt_field">
                                    <input id="email" name="email" type="email" required>
                                    <span></span>
                                    <label for="email">Email</label>
                                </div>
                                <input type="submit" value="Đặt lại mật khẩu">
                                <div class="text-center pt-4">
                                    hoặc <a href="login.jsp">Đăng nhập</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <jsp:include page="footer.jsp"/>
            </div>
        </div>
    </body>
</html>
