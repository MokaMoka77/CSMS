<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="layoutSidenav_nav">
    <nav class="sidenav shadow-right sidenav-light" aria-label="sidenav">
        <div class="sidenav-menu">
            <div class="nav accordion" id="accordionSidenav">
                <!-- Sidenav Heading (Addons)-->
                <div class="sidenav-menu-heading">Cửa hàng cà phê</div>
                <!-- Sidenav Link (Tables)-->
                <a class="nav-link pe-auto" id="dashboard" href="home.jsp">
                    Bảng điều khiển
                </a>
                <c:forEach var="permission" items="${sessionScope.authorize}">
                    <c:choose>
                        <c:when test="${permission eq 'tài khoản'}">
                            <a class="nav-link collapsed pe-auto" href="javascript:void(0);" data-bs-toggle="collapse"
                               data-bs-target="#appsCollapseUserManagement" aria-expanded="false"
                               aria-controls="appsCollapseUserManagement">
                                Tài khoản
                                <div class="sidenav-collapse-arrow">
                                    <i class="fas fa-angle-down"></i>
                                </div>
                            </a>
                            <div class="collapse" id="appsCollapseUserManagement"
                                 data-bs-parent="#accordionSidenavAppsMenu">
                                <nav class="sidenav-menu-nested nav" aria-label="accountnav">
                                    <a class="nav-link pe-auto" id="user-management-list"
                                       href="${pageContext.request.contextPath}/users?page=1&size=10">Danh sách tài
                                        khoản</a>
                                    <a class="nav-link pe-auto" id="user-management-add-user"
                                       href="${pageContext.request.contextPath}/add-user">Thêm tài khoản</a>
                                </nav>
                            </div>
                        </c:when>
                        <c:when test="${permission eq 'vai trò'}">
                            <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse"
                               data-bs-target="#collapseRole" aria-expanded="false" aria-controls="collapseRole">
                                Vai trò
                                <div class="sidenav-collapse-arrow">
                                    <i class="fas fa-angle-down"></i>
                                </div>
                            </a>
                            <div class="collapse" id="collapseRole" data-bs-parent="#accordionSidenav" style="">
                                <nav class="sidenav-menu-nested nav" aria-label="rolenav">
                                    <a class="nav-link" href="listRole">Danh sách vai trò</a>
                                    <a class="nav-link" href="addRole.jsp">Thêm vai trò</a>
                                </nav>
                            </div>
                        </c:when>
                        <c:when test="${permission eq 'hóa đơn'}">
                            <a class="nav-link collapsed" href="javascript:void(0);" data-bs-toggle="collapse"
                               data-bs-target="#collapseBill" aria-expanded="false" aria-controls="collapseBill">
                                Hóa đơn
                                <div class="sidenav-collapse-arrow">
                                    <i class="fas fa-angle-down"></i>
                                </div>
                            </a>
                            <div class="collapse" id="collapseBill" data-bs-parent="#accordionSidenav" style="">
                                <nav class="sidenav-menu-nested nav" aria-label="rolenav">
                                    <a class="nav-link" href="showBill">Xem hóa đơn</a>
                                    <a class="nav-link" href="viewDrinks?navbar=bill">Tạo hóa đơn</a>
                                </nav>
                            </div>
                        </c:when>
                        <c:when test="${permission eq 'thức uống'}">
                            <a class="nav-link" href="viewDrinks?navbar=drink">Thức uống</a>
                        </c:when>
                        <c:when test="${permission eq 'nguyên liệu'}">
                            <a class="nav-link" href="viewIngredient">Nguyên liệu</a>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
        <!-- Sidenav Footer-->
        <div class="sidenav-footer">
            <div class="sidenav-footer-content">
                <div class="sidenav-footer-subtitle">Nội bộ</div>
                <div class="sidenav-footer-title">Chỉ dành cho nhân viên</div>
            </div>
        </div>
    </nav>
</div>