<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Đăng nhập - CSMS</title>
    <link rel="stylesheet" href="./assets/css/styles.css">
    <link rel="icon" type="image/x-icon" href="./assets/image/favicon.png">
    <script src="./assets/js/feather.min.js"></script>
    <script src="./assets/js/sweetalert2.all.min.js"></script>
    <script src="./assets/js/jquery.min.js"></script>
    <script src="./assets/js/login.js"></script>
</head>
<body class="bg-primary">
    <div id="layoutAuthentication">
        <div id="layoutAuthentication_content">
            <main>
                <!-- Basic login form-->
                <div class="center">
                    <h1>ĐĂNG NHẬP</h1>
                    <div class="px-1 pb-5">
                        <form>
                            <div class="txt_field">
                                <input id="email" name="email" type="email" value="" required>
                                <span></span>
                                <label for="email">Email</label>
                            </div>
                            <div class="txt_field">
                                <input id="password" name="password" type="password" required>
                                <span></span>
                                <label for="password">Mật khẩu</label>
                            </div>
                            <div class="forgot-pass-link small"><a href="forgotPassword.jsp">Quên mật khẩu?</a></div>
                            <input type="submit" value="Đăng nhập">
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
