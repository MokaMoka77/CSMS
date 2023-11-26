<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Hồ sơ - CSMS</title>
    <link href="./assets/css/styles.css" rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="./assets/image/favicon.png">
    <script data-search-pseudo-elements defer src="./assets/js/all.min.js"></script>
    <script src="./assets/js/feather.min.js"></script>
</head>
<body class="nav-fixed">
    <%
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.jsp");
        }
    %>
    <jsp:include page="header.jsp"/>
    <div id="layoutSidenav">
        <jsp:include page="navBar.jsp"/>
        <div id="layoutSidenav_content">
            <main>
                <header class="page-header page-header-compact page-header-light border-bottom bg-white mb-4">
                    <div class="container-xl px-4">
                        <div class="page-header-content">
                            <div class="row align-items-center justify-content-between pt-3">
                                <div class="col-auto mb-3">
                                    <h1 class="page-header-title">
                                        <div class="page-header-icon"><svg xmlns="http://www.w3.org/2000/svg" width="24"
                                                                           height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                                                           stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                                                           class="feather feather-user">
                                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                            <circle cx="12" cy="7" r="4"></circle>
                                        </svg></div>
                                        Tài khoản
                                    </h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                <!-- Main page content-->
                <div class="container-xl px-4 mt-4">
                    <!-- Account page navigation-->
                    <nav class="nav nav-borders" aria-label="subnav">
                        <a class="nav-link active ms-0" href="profile.jsp">Hồ sơ</a>
                        <a class="nav-link" href="security.jsp">Bảo mật</a>
                    </nav>
                    <hr class="mt-0 mb-4">
                    <div class="row d-flex justify-content-center">
                        <div class="col-xl-8">
                            <!-- Account details card-->
                            <div class="card mb-4">
                                <div class="card-header">Chi tiết tài khoản</div>
                                <div class="card-body">
                                    <form>
                                        <!-- Profile picture card-->
                                        <div class="card-body text-center">
                                            <!-- Profile picture image-->
                                            <img class="img-account-profile rounded-circle mb-2" src="assets/image/profile.png"
                                                 alt="">
                                            <!-- Profile picture help block-->
                                            <div class="small font-italic text-muted mb-4">Định dạng JPG hoặc PNG dưới 5 MB
                                            </div>
                                            <!-- Profile picture upload button-->
                                            <button class="btn btn-primary" type="button">Tải hình ảnh lên</button>
                                        </div>
                                        <!-- Form Row-->
                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (full name)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputFullName">Họ và tên</label>
                                                <input class="form-control" id="inputFullName" type="text"
                                                       placeholder="Nhập họ và tên của bạn" value="Quản trị viên">
                                            </div>
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputGender">Giới tính</label>
                                                <input class="form-control" id="inputGender" type="text"
                                                       placeholder="Nhập giới tính của bạn" value="Nam">
                                            </div>
                                        </div>
                                        <!-- Form Row-->
                                        <div class="row gx-3 mb-3">
                                            <!-- Form Group (phone number)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputPhone">Số điện thoại</label>
                                                <input class="form-control" id="inputPhone" type="tel"
                                                       placeholder="Nhập số điện thoại của bạn" value="0987******">
                                            </div>
                                            <!-- Form Group (birthday)-->
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputBirthday">Ngày sinh</label>
                                                <input class="form-control" id="inputBirthday" type="text"
                                                       name="birthday" placeholder="Nhập ngày sinh của bạn"
                                                       value="04/08/2023">
                                            </div>
                                        </div>
                                        <!-- Form Group (email address)-->
                                        <div class="mb-3">
                                            <label class="small mb-1" for="inputEmailAddress">Email</label>
                                            <input class="form-control" id="inputEmailAddress" type="email"
                                                   placeholder="Nhập địa chỉ email của bạn" value="admin@gmail.com">
                                        </div>
                                        <!-- Form Group (location)-->
                                        <div class="mb-3">
                                            <label class="small mb-1" for="inputLocation">Địa chỉ</label>
                                            <input class="form-control" id="inputLocation" type="text"
                                                   placeholder="Nhập địa chỉ của bạn" value="Quy Nhơn, Bình Định">
                                        </div>
                                        <!-- Save changes button-->
                                        <div class="d-flex justify-content-center">
                                            <button class="btn btn-primary" type="button">Lưu thay đổi</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <jsp:include page="footer.jsp"/>
        </div>
    </div>
    <script src="./assets/js/custom-bundle.min.js"></script>
    <script src="./assets/js/scripts.js"></script>
    <script src="./assets/js/sb-customizer.js"></script>
</body>
</html>