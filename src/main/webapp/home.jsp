<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Trang chủ - CSMS</title>
    <link href="assets/css/tableStyle.css" rel="stylesheet" type="text/css">
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
                <!-- Main page content-->
                <div class="container-xl px-4 mt-5">
                    <!-- Custom page header alternative example-->
                    <div class="d-flex justify-content-between align-items-sm-center flex-column flex-sm-row mb-4">
                        <div class="me-4 mb-3 mb-sm-0">
                            <h1 class="mb-0">Bảng điều khiển</h1>
                            <div class="small">
                                <span class="fw-500 text-primary">Thứ hai</span>
                                · 21 Tháng 8, 2023 · 14:00 CH
                            </div>
                        </div>
                    </div>
                    <!-- Illustration dashboard card example-->
                    <div class="card card-waves mb-4 mt-5">
                        <div class="card-body p-5">
                            <div class="row align-items-center justify-content-between">
                                <div class="col">
                                    <h2 class="text-primary">Chào mừng trở lại, bảng điều khiển của bạn đã sẵn sàng!</h2>
                                    <a class="btn btn-primary p-3" href="home.jsp">
                                        Bắt đầu
                                        <i class="ms-1" data-feather="arrow-right"></i>
                                    </a>
                                </div>
                                <div class="col d-none d-lg-block mt-xxl-n4">
                                    <img class="img-fluid px-xl-4 mt-xxl-n5" src="./assets/image/statistics.svg" alt="statistic-image"/>
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
    <script src="./assets/js/simple-datatables.js"></script>
    <script src="./assets/js/datatables-simple-demo.js"></script>
    <script src="./assets/js/sb-customizer.js"></script>
</body>

</html>
