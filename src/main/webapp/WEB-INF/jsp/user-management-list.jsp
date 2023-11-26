<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Nhan vien</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" type="text/css">
    <link href="assets/css/styles.css" rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="assets/image/favicon.png">
    <script data-search-pseudo-elements defer
            src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/js/all.min.js"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.29.0/feather.min.js"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="assets/js/user/user-activate-change-id.js"></script>
</head>

<body class="nav-fixed">
<jsp:include page="/assets/common/user/user-deactivate-confirm-modal.jsp"></jsp:include>
<jsp:include page="/assets/common/user/user-reactivate-confirm-modal.jsp"></jsp:include>
<jsp:include page="/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="/navBar.jsp"></jsp:include>
    <div id="layoutSidenav_content">
        <main>
            <header class="page-header page-header-compact page-header-light border-bottom bg-white mb-4">
                <div class="container-fluid px-4">
                    <div class="page-header-content">
                        <div class="row align-items-center justify-content-between pt-3">
                            <div class="col-auto mb-3">
                                <h1 class="page-header-title">
                                    <div class="page-header-icon">
                                        <i data-feather="user"></i>
                                    </div>
                                    Danh sach nhan vien
                                </h1>
                            </div>
                            <div class="col-12 col-xl-auto mb-3">
                                <a class="btn btn-sm btn-light text-primary"
                                   href="#">
                                    <i class="me-1" data-feather="users"></i>
                                    Quan ly quyen
                                </a>
                                <a class="btn btn-sm btn-light text-primary"
                                   href="${pageContext.request.contextPath}/add-user">
                                    <i class="me-1" data-feather="user-plus"></i>
                                    Them nhan vien
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- Main page content-->
            <div class="container-fluid px-4">
                <div class="card mb-4">
                    <div class="card-header">Nhan vien</div>
                    <div class="card-body">
                        <table id="datatablesSimple">
                            <thead>
                            <tr>
                                <th>Họ và tên</th>
                                <th>Email</th>
                                <th>Vai trò</th>
                                <th>Ngày sinh</th>
                                <th>Trạng thái</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${requestScope.users}">
                                <tr>
                                    <td>${user.name}</td>
                                    <td>${user.email}</td>
                                    <td>${user.role.name}</td>
                                    <td>${user.dob}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.active==true}">
                                                <span class="badge bg-green rounded-pill">Active</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-red rounded-pill">Non-active</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a class="btn btn-datatable btn-icon btn-transparent-dark me-2"
                                           href="${pageContext.request.contextPath}/edit-user?id=${user.id}">
                                            <i data-feather="edit"></i>
                                        </a>
                                        <c:choose>
                                            <c:when test="${user.active==true}">
                                                <button class="btn btn-datatable btn-icon btn-transparent-dark deactivate"
                                                        data-bs-toggle="modal" data-bs-target="#deactivateModal">
                                                    <i data-feather="trash-2"></i>
                                                    <input type="hidden" name="current-id" id="id" value="${user.id}">
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-datatable btn-icon btn-transparent-dark me-2 reactivate"
                                                        data-bs-toggle="modal" data-bs-target="#reactivateModal">
                                                    <input type="hidden" name="current-id" id="id" value="${user.id}">
                                                    <i data-feather="rotate-ccw"></i>
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/footer.jsp"></jsp:include>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="assets/js/scripts.js"></script>
<script src="assets/js/simple-datatables.js"></script>
<script src="assets/js/datatables-simple-demo.js"></script>
<script src="https://assets.startbootstrap.com/js/sb-customizer.js"></script>
</body>

</html>