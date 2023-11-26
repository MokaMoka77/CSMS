<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Vai trò - CSMS</title>
        <link href="./assets/css/tableStyle.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/styles.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="./assets/image/favicon.png">
        <script data-search-pseudo-elements defer src="./assets/js/all.min.js"></script>
        <script src="./assets/js/feather.min.js"></script>
        <style>
            .add-role {
                display: flex;
                align-content: center;
                width: 100%;
                height: 100%;
                border: 1px solid gray;
                border-radius: 15px;
                padding: 5px;
                text-align: center;
            }
            input {
                margin: 5px ;
            }
            .active {
                width: 20px !important;
            }
            button {
                margin: 5px;
                /*                background-color: rgba(146, 53, 233, 0.832);*/
                color: #0061f2;
                margin-bottom: 10px;
                width: 150px;
                height: 40px;
                border-radius: 5px;
            }
        </style>
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
                        <div class="card mb-4">
                            <div class="card-header">Thêm vai trò</div>
                            <div class="card-body">
                                <div class="add-role">
                                    <form action="addRole" style="display: flex">
                                         <h3 style="color: blue">${requestScope.error}</h3>
                                        <input style="width: 30%; height: 40px; border: 2px solid black; border-radius: 5px;" type="text" placeholder="ID" name="id"><br>

                                        <input style="width: 30%; height: 40px; border: 2px solid black; border-radius: 5px;" type="text" placeholder="Tên vai trò" name="name"><br>

                                        <input style="width: 30%; height: 40px; border: 2px solid black; border-radius: 5px;" type="text" placeholder="Quyền hạn" name="authorize"><br>

                                    <button>Thêm</button>
                                    </form>
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
