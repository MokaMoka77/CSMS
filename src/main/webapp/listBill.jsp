<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Hóa đơn - CSMS</title>
    <link href="assets/css/tableStyle.css" rel="stylesheet" type="text/css">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./assets/css/styleKhoi.css">
    <link rel="stylesheet" type="text/css" href="./assets/css/styles.css">
    <link rel="icon" type="image/x-icon" href="./assets/image/favicon.png">
    <script data-search-pseudo-elements defer src="./assets/js/all.min.js"></script>
    <script src="./assets/js/feather.min.js"></script>
    <script src="./assets/js/jquery.min.js"></script>
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
                        <div class="card-header">Danh sách hóa đơn</div>
                        <%--                            <div class="action">--%>
                        <%--                                <button onclick="showContentBill()" class="btn btn-primary ms-1" type="button" data-bs-toggle="modal" data-bs-target="#show-bill">--%>
                        <%--                                    Show Bill--%>
                        <%--                                </button>--%>
                        <%--                            </div>--%>
                        <table id="datatablesSimple" aria-label="List bill">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Tổng giá tiền</th>
                                    <th scope="col">Ngày tạo</th>
                                    <th scope="col">Người tạo</th>
                                    <th scope="col">Tùy chọn</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.dt}" var="b">
                                    <c:set var="id" value="${b.id}"/>
                                    <tr>
                                        <td>${id}</td>
                                        <td>${b.totalPrice}</td>
                                        <td>${b.createdAt}</td>
                                        <td>${b.takeCreateBy}</td>
                                        <td>
                                            <button class="btn btn-primary ms-1"
                                                    type="button" data-bs-toggle="modal"
                                                    data-bs-target="#show-bill"
                                                    onclick="showBillDetail(${id}, '${b.createdAt}', ${b.totalPrice})">
                                                Hiển thị hóa đơn
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Tổng giá tiền</th>
                                    <th scope="col">Ngày tạo</th>
                                    <th scope="col">Người tạo</th>
                                    <th scope="col">Tùy chọn</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>

                <div id="show-bill" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="showBill" method="post">
                                <div>
                                    <div class="modal-title">Chi tiết hóa đơn</div>
                                    <hr>
                                    <div class="info">
                                        <div class="info-title">ID:</div>&nbsp;&nbsp;
                                        <div class="info-content id"></div>
                                    </div>
                                    <div class="info">
                                        <div class="info-title">Thời gian:</div>&nbsp;&nbsp;
                                        <div class="info-content time"></div>
                                    </div>
                                    <hr>
                                </div>
                                <div class="modal-body">
                                    <table id='bill-complete' class="table" aria-label="Bill details">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Tên</th>
                                                <th scope="col">Giá</th>
                                                <th scope="col">Số lượng</th>
                                            </tr>
                                        </thead>
                                        <tbody></tbody>
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
                                    <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">
                                        Đóng
                                    </button>
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
    <script src="./assets/js/scripts.js"></script>
    <script src="./assets/js/simple-datatables.js"></script>
    <script src="./assets/js/datatables-simple-demo.js"></script>
    <script src="./assets/js/sb-customizer.js"></script>
    <script>
        function showBillDetail(id, createdAt, totalPrice) {
            $.ajax({
                url: "/showBill",
                method: "POST",
                data: { id : id},
                success: function (response) {
                    let idElement = document.querySelector("#show-bill .info-content.id"),
                        timeElement = document.querySelector("#show-bill .info-content.time"),
                        tableElement = document.querySelector("#show-bill table tbody"),
                        totalQuantityElement = document.querySelector("#show-bill .total-number.quantity-bill"),
                        totalPriceElement = document.querySelector("#show-bill .total-number.money-bill");

                    tableElement.innerHTML = response;
                    idElement.textContent = id;
                    timeElement.textContent = createdAt;
                    totalQuantityElement.textContent = getTotalQuanity();
                    totalPriceElement.textContent = totalPrice;
                }
            })
        }

        function getTotalQuanity() {
            let rows = Array.from(document.querySelectorAll("#show-bill table tbody tr")),
                totalQuantity = 0;
            rows.forEach(function(row) {
                let quantityCell = row.querySelector("td:nth-child(4)");
                let quantity = parseInt(quantityCell.textContent);
                if (!isNaN(quantity)) {
                    totalQuantity += quantity;
                }
            });
            return totalQuantity;
        }
    </script>
</body>
</html>
