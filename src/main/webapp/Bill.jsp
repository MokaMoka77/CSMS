<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Hóa đơn - CSMS</title>
        <link rel="icon" type="image/x-icon" href="./assets/image/favicon.png">
        <link rel="stylesheet" type="text/css" href="./assets/css/styleKhoi.css">
        <link rel="stylesheet" type="text/css" href="./assets/css/styles.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script data-search-pseudo-elements defer src="./assets/js/all.min.js"></script>
        <script src="./assets/js/feather.min.js"></script>
        <style>
            .close{
                font-size: 25px;
                cursor: pointer;
                border-radius: 50%;
            }

            .close:hover{
                color: red;
                background-color: #fff;
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
                    <div class="system-bill-coffee">
                        <div class="row">
                            <div class="col-md-6 ui-1">
                                <nav class="navbar navbar-expand-lg navbar-dark mt-3 mb-5 shadow p-2" style="background-color: #607D8B">
                                    <!-- Container wrapper -->
                                    <div class="container-fluid">

                                        <!-- Navbar brand -->
                                        <a class="navbar-brand" href="#">Danh mục:</a>

                                        <!-- Toggle button -->
                                        <button 
                                            class="navbar-toggler" 
                                            type="button" 
                                            data-mdb-toggle="collapse" 
                                            data-mdb-target="#navbarSupportedContent2" 
                                            aria-controls="navbarSupportedContent2" 
                                            aria-expanded="false" 
                                            aria-label="Toggle navigation">
                                            <i class="fas fa-bars"></i>
                                        </button>

                                        <!-- Collapsible wrapper -->
                                        <div class="collapse navbar-collapse" id="navbarSupportedContent2">
                                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                                                <!-- Link -->
                                                <li class="nav-item acitve">
                                                    <a class="nav-link text-white" href="convertDrink?cid=C0">Tất cả</a>
                                                </li><!-- comment -->
                                                
                                                <c:forEach items="${sessionScope.listCa}" var="ca">
                                                    <li class="nav-item">
                                                        <a class="nav-link text-white" href="convertDrink?cid=${ca.id}">${ca.name}</a>
                                                    </li>
                                                </c:forEach>
                                           </ul>
                                        </div>
                                    </div>
                                    <!-- Container wrapper -->
                                </nav>
                                <div class="row">
                                    <c:forEach items="${sessionScope.listDrink}" var="dri" >
                                        <div onclick="getInfoDrink(${dri})" class="col-md-4 item-coffee">
                                            <img src="${dri.pathImage}" alt="">
                                            <div class="name-coffee">${dri.name}</div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-md-6 ui-2">
                                <table id='bill-process' class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Tên</th>
                                            <th scope="col">Giá</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Tùy chọn</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                    </tbody>
                                </table>
                                <div class="payment">
                                    <div class="total">
                                        <div class="total-title">Tổng số lượng</div>
                                        <div class="total-number quantity">0</div>
                                    </div>
                                    <div class="total">
                                        <div class="total-title">Tổng tiền</div>
                                        <div class="total-number money">0</div>
                                    </div>
                                    <div class="action">
                                        <button onclick="showContentBill()" class="btn btn-primary ms-1" type="button" data-bs-toggle="modal" data-bs-target="#show-bill">
                                            Tạo hóa đơn
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Bill Modal HTML -->
                    <div id="show-bill" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="add" method="post">
                                    <div>						
                                        <div class="modal-title">Hóa đơn thanh toán</div>
                                        <hr>
                                        <div class="info">
                                            <div class="info-title">
                                                Ngày:
                                            </div>&nbsp;&nbsp;
                                            <div class="info-content date">
                                                
                                            </div>
                                        </div>
                                        <div class="info">
                                            <div class="info-title">
                                                Thời gian:
                                            </div>&nbsp;&nbsp;
                                            <div class="info-content time">
                                                
                                            </div>
                                        </div>
                                        <hr>
                                    </div>
                                    <div class="modal-body">	
                                        <table id='bill-complete' class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">Tên</th>
                                                    <th scope="col">Giá/1 thức uống</th>
                                                    <th scope="col">Số lượng</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="payment">
                                        <div class="total" style="color: black">
                                            <div class="total-title">Tổng số lượng</div>
                                            <div class="total-number quantity-bill"></div>
                                        </div>
                                        <div class="total" style="color: black">
                                            <div class="total-title">Tổng tiền</div>
                                            <div class="total-number money-bill"></div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button onclick="cancelBill()" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <input onClick="sendBillToServlet()" type="button" class="btn btn-success" value="Thêm">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>
                <jsp:include page="footer.jsp"/>
            </div>
        </div>

        <script src="./assets/js/custom-bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="./assets/js/scripts.js"></script>
        <script src="./assets/js/sb-customizer.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="./assets/js/handleBill.js"></script>
        
    </body>
</html>
