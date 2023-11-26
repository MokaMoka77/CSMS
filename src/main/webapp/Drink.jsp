<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
        <title>Thức uống - CSMS</title>
        <!-- style css -->
        <link rel="icon" type="image/x-icon" href="./assets/image/favicon.png">
        <link rel="stylesheet" type="text/css" href="./assets/css/styleKhoi.css">
        <link rel="stylesheet" type="text/css" href="./assets/css/styles.css">
        <!-- font css -->
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;800&display=swap" rel="stylesheet">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <script data-search-pseudo-elements defer src="./assets/js/all.min.js"></script>
        <script src="./assets/js/feather.min.js"></script>
        <style>
            .select2-search{
                margin: 0 5px;
                border-radius: 0.35rem
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
                    <div class="header_section">
                        <div class="coffee_section_1">
                            <div class="input-group">
<!--                                <img src="./images/banner-img.png" alt="" width="50px" height="50px">-->
                                <div style="margin: 0 150px 0 20px; font-size: 25px;">Cửa hàng cà phê</div>
                                <c:set var="price" value="${requestScope.price}"/>
                                <form action="searchDrink" method="POST" style="display: flex">
                                    <select class="select2-search" name="cate">
                                        <option value="C0">Tìm kiếm theo danh mục</option>
                                        <option value="C0">Tất cả</option>
                                        <c:set var="cid" value="${requestScope.cid}"/>
                                        <c:forEach items="${sessionScope.listCa}" var="ca">
                                            <option ${(cid==ca.id)?'selected':''} value="${ca.id}">${ca.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select class="select2-search" name="price">
                                        <option value="0">Tìm kiếm theo mức giá</option>
                                        <option ${(price=="10000-20000")?'selected':''} value="10000-20000">Từ 10k đến 20k</option>
                                        <option ${(price=="20000-30000")?'selected':''} value="20000-30000">Từ 20k đến 30k</option>
                                        <option ${(price=="30000")?'selected':''} value="30000">Trên 30k</option>
                                    </select>
                                    <input type="text" name="textName" class="form-control select2-search" placeholder="Nhập văn bản...">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="submit">Tìm</button>
                                    </div>
                                </form>
                            </div>             
                        </div>
                        <!-- banner section start --> 
                        <!-- banner section end -->
                    </div>
                    <!-- header section end -->
                    <!-- coffee section start -->
                    <div class="coffee_section layout_padding">
                        <div class="container">
                            <div class="row">
                                <h1 class="coffee_taital">ƯU ĐÃI CÀ PHÊ CỦA CHÚNG TÔI</h1>
                                <div class="d-flex justify-content-center">
                                    <div class="input-group-append">
                                        <button 
                                            class="btn btn-primary ms-1" 
                                            type="button" data-bs-toggle="modal" 
                                            data-bs-target="#addDrink" 
                                        >
                                            Thêm một thức uống mới
                                        </button> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="coffee_section_1" style="border-radius: 5px; padding: 10px;max-height: 300px; overflow-y:auto; background-color: rgb(130, 225, 238);">
                            <h3 style="text-align: left">Nguyên Lieu su dung moi ngay</h3>
                            <div class="d-flex flex-wrap">
                                <c:forEach items="${sessionScope.listNLPD}" var="nlpd">
                                    <div class="d-flex justify-content-around" style="width: 25%;font-size: 18px; border: 1px solid gray; padding: 5px ; margin-bottom: 10px">
                                        <label>${nlpd.nl.name}:</label>
                                        <div>${nlpd.weight} gam</div>
                                    </div>
                                </c:forEach>
                                <!-- comment -->
                            </div>
                        </div>
                        <div class="coffee_section_2">
                            <c:set var="list" value="${sessionScope.listDrink}" />
                            <c:if test="${list.size() == 0}">
                                <div style="color: red">Không tìm thấy đồ uống phù hợp ${list.size()}</div>
                            </c:if>
                            <c:forEach items="${sessionScope.listDrink}" var="dri" varStatus="status">
                                <c:if test="${status.index % 4 == 0}">
                                    <div class="container-fluid">
                                        <div class="row">
                                </c:if>
                                        <div class="col-lg-3 col-md-6 card ">
                                            <div class="coffee_img"><img src="${dri.pathImage}"></div>
                                            <h3 class="types_text">${dri.name}</h3>
                                            <p class="looking_text">${dri.price} vnd</p>
                                            <div class="read_bt"><a href="detailDrink?Drink_id=${dri.id}">Xem thêm</a></div>
                                        </div>   
                                <c:if test="${status.index % 4 == 3 || status.last}">
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>                       
                        </div>
                    </div>
                </main>
                <jsp:include page="footer.jsp"/>
            </div>
        </div>
                                    
        <!-- Add form -->
        <div id="addDrink" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="addDrink" method="POST">
                        <div class="modal-header">
                            <h4 class="modal-title">Thêm thông tin thức uống</h4>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Tên</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Link hình ảnh</label>
                                <input name="pathImage" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Giá</label>
                                <input name="price" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Tình trạng</label>
                                <select name="status" id="edit-se" class="form-select" aria-label="Default select example">
                                    <option value="1">Có sẵn</option>
                                    <option value="0">Không có sẵn</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Danh mục</label>
                                <select name="category" id="edit-se" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${sessionScope.listCa}" var="ca">
                                        <option value="${ca.id}">${ca.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <input type="submit" class="btn btn-info" value="Tạo">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="./assets/js/custom-bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="./assets/js/scripts.js"></script>
        <script src="./assets/js/sb-customizer.js"></script>
    </body>
</html>
