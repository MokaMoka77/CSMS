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
        <!-- bootstrap css -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!-- style css -->
        <link rel="icon" type="image/x-icon" href="./assets/image/favicon.png">
        <link rel="stylesheet" type="text/css" href="./assets/css/styles.css">
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
                    <div class="container mt-5">
                        <!--Grid row-->
                        <div class="row">
                            <c:set var="dri" value="${requestScope.getedDri}"/>
                            <!--Grid column-->
                            <div class="col-md-6 mb-4 p-4">
                                <img src="${dri.pathImage}" class="img-fluid" style="height: 382px; width: 500px" alt="" />
                            </div>
                            <!--Grid column-->

                            <!--Grid column-->
                            <div class="col-md-6 mb-4">
                                <!--Content-->
                                <div class="p-4">
                                    <div class="mb-3">
                                        <a href="">
                                            <span class="badge bg-dark me-1">${dri.ca.name}</span>
                                        </a>
                                        <a href="">
                                            <span class="badge bg-info me-1">Mới</span>
                                        </a>
                                        <a href="">
                                            <span class="badge bg-danger me-1">Bán chạy</span>
                                        </a>
                                    </div>

                                    <p class="lead">
                                        <span>Tên thức uống: ${dri.name}</span><br>
                                        <span>Tình trạng: ${dri.status ? "Có sẵn" : "Không có sẵn"}</span>
                                    </p>

                                    <p class="lead">
                                        <span>Giá: </span>
                                        <!--<span class="me-1">
                                            <del> ${dri.price*120/100} vnd</del>
                                        </span>-->
                                        <span>${dri.price} vnd</span>
                                    </p>

                                    <br>
                                    <strong>
                                        <p style="font-size: 20px; margin: 0;">Mô tả</p>
                                    </strong>
                                    <p style="font-size: 18px; margin: 0;">${dri.name} là 1 loại đồ uống của CSMS chúng tôi với nhiều
                                        chất dinh dưỡng cần thiết . Loại đồ uống này được chế biến từ các nguyên liệu của hãng sản xuất
                                        Hun kid , nhân viên pha chế phải làm đầy đủ các bước để làm ra 1 loại đồ uống.</p>
                                    <br>
                                    <div class="d-flex justify-content-left">
                                        <!-- Default input -->
                                        <button 
                                            class="btn btn-primary ms-1" 
                                            type="button" data-bs-toggle="modal" 
                                            data-bs-target="#deleteDrink"
                                            onclick="window.location.href='viewDrinks?navbar=drink'"
                                        >
                                            Quay lại
                                        </button>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <button
                                            class="btn btn-primary ms-1" 
                                            type="button" data-bs-toggle="modal" 
                                            data-bs-target="#editDrink" 
                                            onclick="showOldDataWhenEdit(['${dri.id}','${dri.name}','${dri.pathImage}','${dri.price}','${dri.status}','${dri.ca.id}'])"
                                        >
                                            Sửa
                                        </button>                                        
                                    </div>
                                </div>
                                <!--Content-->
                            </div>
                            <!--Grid column-->
                        </div>
                        <!--Grid row-->

                        <hr />

                        <!--Grid row-->
                        <div class="row d-flex justify-content-center">
                            <!--Grid column-->
                            <div class="col-md-6 text-center">
                                <h4 class="my-4 h4">Thông tin bổ sung</h4>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Natus suscipit modi sapiente illo soluta
                                    odit voluptates, quibusdam officia. Neque quibusdam quas a quis porro? Molestias illo neque eum
                                    in laborum.</p>
                            </div>
                            <!--Grid column-->
                            
                        </div>
                        <!--Grid row-->
                    </div>
                </main>
                <jsp:include page="footer.jsp"/>
            </div>
        </div>
                                        
        <!-- Edit form -->
        <div id="editDrink" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="editDrink" method="POST">
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
                            <input type="submit" class="btn btn-info" value="Lưu">
                        </div>
                    </form>
                </div>
            </div>
        </div>
          
        <script src="./assets/js/sb-customizer.js"></script>
        <script src="./assets/js/scripts.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <script>
            function showOldDataWhenEdit(arr){
                let formEdit = document.querySelector('#editDrink form');
                let i = 0;
                let listInput = formEdit.querySelectorAll('.modal-body .form-group input');

                listInput.forEach((input)=>{
                    input.value = arr[i++];              
                })

                // take element select>option
                var selectEle = formEdit.querySelector('.modal-body .form-group select[name="category"]');
                Array.from(selectEle.options).forEach((option)=>{
                    if(option.value == arr[arr.length - 1]) option.selected = true;
                })

                var selectEle = formEdit.querySelector('.modal-body .form-group select[name="status"]');
                let statusValue = arr[arr.length - 2] === "true" ? 1 : 0;
                Array.from(selectEle.options).forEach((option)=>{
                    if(parseInt(option.value) === statusValue) option.selected = true;
                })
            }
        </script>
    </body>
</html>
