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
        <title>Nguyên liệu - CSMS</title>
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
                            <div class="input-group" style="justify-content: center">
<%--                                <img src="./images/banner-img.png" alt="" width="50px" height="50px">--%>
                                <div style="margin: 0 150px 0 20px; font-size: 25px;">Cửa hàng cà phê</div>
                                <form action="searchIngredient" method="POST" style="display: flex">
                                    <input type="text" name="textName" class="form-control" placeholder="Nhập văn bản...">
                                    <div class="input-group-append" style="margin-left: 10px">
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
                                <h1 class="coffee_taital">Danh sách nguyên liệu</h1>
                                <div class="d-flex justify-content-center">
                                    <div class="input-group-append">
                                        <button 
                                            class="btn btn-primary ms-1" 
                                            type="button" data-bs-toggle="modal" 
                                            data-bs-target="#addIngredient" 
                                        >
                                            Thêm một nguyên liệu mới
                                        </button> 
                                        <button 
                                            class="btn btn-primary ms-1" 
                                            type="button" data-bs-toggle="modal" 
                                            data-bs-target="#ingredientPerDay" 
                                        >
                                            Nguyên liệu trong một ngày
                                        </button> 
                                    </div>
                                </div>
                                <div class="bulit_icon"><img src="images/bulit-icon.png"></div>
                            </div>
                        </div>
                        <div class="coffee_section_2"> 
                            <table id='bill-process' class="table" aria-label="List ingredient">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Tên</th>
                                        <th scope="col">Khối lượng</th>
                                        <th scope="col">Ngày</th>
                                        <th scope="col">Tình trạng</th>
                                        <th scope="col">Tùy chọn</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${sessionScope.listNL}" var="nl">
                                        <tr>
                                            <th scope="row">${nl.id}</th>
                                            <td>${nl.name}</td>
                                            <td>${nl.weight}</td><!-- comment -->
                                            <td>${nl.date}</td><!-- comment -->
                                            <td>${nl.status ? 'available' : 'unavailable'}</td>
                                            <td>
                                                <button 
                                                    class="btn btn-primary ms-1" 
                                                    type="button" data-bs-toggle="modal" 
                                                    data-bs-target="#editIngredient" 
                                                    onclick="showOldDataWhenEdit(['${nl.id}','${nl.name}','${nl.weight}','${nl.date}','${nl.status}'])"
                                                >
                                                Sửa
                                                </button>
                                            </td>
                                        </tr> 
                                    </c:forEach>
                                </tbody>
                            </table>                      
                        </div>
                    </div>
                </main>
                <jsp:include page="footer.jsp"/>
            </div>
        </div>
                                    
        <!-- Add form -->
        <div id="addIngredient" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="addIngredient" method="POST">
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
                                <label>Khối lượng</label>
                                <input name="weight" type="number" class="form-control" required>
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
        
        <!-- Edit form -->
        <div id="editIngredient" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="editIngredient" method="POST">
                        <div class="modal-header">
                            <h4 class="modal-title">Sửa thông tin thức uống</h4>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>ID</label>
                                <input name="id" type="text" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Tên</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Khối lượng</label>
                                <input name="weight" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Ngày</label>
                                <input name="date" type="text" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Tình trạng</label>
                                <select name="status" id="edit-se" class="form-select" aria-label="Default select example">
                                    <option value="1">Có sẵn</option>
                                    <option value="0">Không có sẵn</option>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <input type="submit" class="btn btn-info" value="Lưu">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- Per day form -->
        <div id="ingredientPerDay" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="max-width: 900px;">
                <div class="modal-content">
                    <form action="updateIngredientPerDay" method="POST">
                        <c:set var="listNLPD" value="${sessionScope.listNLPD}"/>
                        <div class="modal-header">
                            <h4 class="modal-title">Nguyên liệu trong một ngày</h4>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">&times;</button>
                        </div>
                        <div class="row" style="margin:auto 0">
                            <c:forEach items="${sessionScope.listNLPD}" var="nlpd">
                                <div class="col-md-4 d-flex p-1">
                                    <label style="width: 40%; line-height: 3">${nlpd.nl.name}</label>
                                    <input name="weight" type="number" class="form-control" value='${nlpd.weight}' style="width: 50%">
                                </div>
                            </c:forEach>                            
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <input type="submit" class="btn btn-info" value="Lưu">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
                                    
        <script src="./assets/js/custom-bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="./assets/js/scripts.js"></script>
        <script src="./assets/js/sb-customizer.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script>            
            function showOldDataWhenEdit(arr){
                let formEdit = document.querySelector('#editIngredient form');
                let i = 0;
                let listInput = formEdit.querySelectorAll('.modal-body .form-group input');
                listInput.forEach((input)=>{
                    input.value = arr[i++];              
                })

                // take element select>option
                
                let selectEle = formEdit.querySelector('.modal-body .form-group select[name="status"]');
                let statusValue = arr[arr.length - 1] === "true" ? 1 : 0;
                Array.from(selectEle.options).forEach((option)=>{
                    if(parseInt(option.value) === statusValue) option.selected = true;
                })
            }
        </script>
    </body>
</html>
