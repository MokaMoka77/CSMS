<%--
  Created by IntelliJ IDEA.
  User: KhoaBug
  Date: 8/16/2023
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script src="assets/js/user/user-name-concat.js"></script>
</head>

<body class="nav-fixed">
<jsp:include page="/assets/common/user/user-edit-confirm-modal.jsp"></jsp:include>
<jsp:include page="/header.jsp"></jsp:include>
<div id="layoutSidenav">
    <jsp:include page="/navBar.jsp"></jsp:include>
    <div id="layoutSidenav_content">
        <main>
            <header class="page-header page-header-compact page-header-light border-bottom bg-white mb-4">
                <div class="container-xl px-4">
                    <div class="page-header-content">
                        <div class="row align-items-center justify-content-between pt-3">
                            <div class="col-auto mb-3">
                                <h1 class="page-header-title">
                                    <div class="page-header-icon">
                                        <i data-feather="user"></i>
                                    </div>
                                    Sua nhan vien
                                </h1>
                            </div>
                            <div class="col-12 col-xl-auto mb-3">
                                <a class="btn btn-sm btn-light text-primary"
                                   href="${pageContext.request.contextPath}/users?page=1&size=10">
                                    <i class="me-1" data-feather="arrow-left"></i>
                                    Tro lai danh sach
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- Main page content-->
            <div class="container-xl px-4 mt-4">
                <div class="row">
                    <div class="col-xl-4">
                        <!-- Profile picture card-->
                        <div class="card mb-4 mb-xl-0">
                            <div class="card-header">Profile Picture</div>
                            <div class="card-body text-center">
                                <!-- Profile picture image-->
                                <img class="img-account-profile rounded-circle mb-2"
                                     src="assets/image/user-placeholder.svg" alt="">
                                <!-- Profile picture help block-->
                                <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                                <!-- Profile picture upload button-->
                                <input class="d-none" id="profile-image" type="file">
                                <label class="btn btn-primary" for="profile-image">Upload new
                                    image</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <!-- Account details card-->
                        <div class="card mb-4">
                            <div class="card-header">Account Details</div>
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/edit-user" method="post" id="editForm">
                                    <!-- Form Row-->
                                    <div class="row gx-3 mb-3">
                                        <input name="id" type="hidden" value="${requestScope.user.id}">
                                        <c:set var="fullName" value="${requestScope.user.name}"/>
                                        <c:set var="firstName" value="${fn:substringAfter(fullName, ' ')}"/>
                                        <c:set var="lastName" value="${fn:substringBefore(fullName, ' ')}"/>
                                        <!-- Form Group (last name)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputLastName">Last name</label>
                                            <input class="form-control" id="inputLastName" type="text"
                                                   placeholder="Enter your last name" value="${lastName}">
                                        </div>
                                        <!-- Form Group (first name)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputFirstName">First name</label>
                                            <input class="form-control" id="inputFirstName" type="text"
                                                   placeholder="Enter your first name" value="${firstName}">
                                        </div>
                                        <input name="name" id="inputFullName" type="hidden">
                                    </div>
                                    <!-- Form Group (email address)-->
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                        <input class="form-control" id="inputEmailAddress" type="email"
                                               placeholder="Enter your email address"
                                               value="${requestScope.user.email}"
                                               name="email">
                                    </div>
                                    <!-- Form Group (Roles)-->
                                    <div class="mb-3">
                                        <label class="small mb-1">Role</label>
                                        <select class="form-select" name="roleId" aria-label="Default select example">
                                            <option selected="" disabled="">Select a role:</option>
                                            <c:forEach var="role" items="${requestScope.roles}">
                                                <c:choose>
                                                    <c:when test="${role.id==requestScope.user.role.id}">
                                                        <option value="${role.id}" selected>${role.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${role.id}">${role.name}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <!-- Submit button-->
                                    <button class="btn btn-primary summit" data-bs-toggle="modal"
                                            data-bs-target="#editConfirmModal" type="button">Save changes
                                    </button>
                                </form>
                            </div>
                        </div>
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
