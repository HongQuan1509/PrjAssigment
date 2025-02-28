<%-- 
    Document   : home
    Created on : Feb 28, 2025, 9:34:52 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quản lý nghỉ phép</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Quản lý nghỉ phép</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <span class="nav-link">Xin chào, <strong>${username}</strong>!</span>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-danger text-white" href="/logout">Đăng xuất</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h2>Chào mừng, ${username}!</h2>
    <p>Vai trò của bạn:
        <c:forEach var="role" items="${roles}">
            <span>${role}</span>
        </c:forEach>
    </p>

    <h3>Chức năng của bạn</h3>
    <ul>
        <c:if test="${fn:contains(roles, 'request/create')}">
            <li><a href="/requests/create">Tạo đơn nghỉ phép</a></li>
        </c:if>
        <c:if test="${fn:contains(roles, 'request/review')}">
            <li><a href="/requests/review">Xét duyệt đơn nghỉ phép</a></li>
        </c:if>
        <c:if test="${fn:contains(roles, 'request/list')}">
            <li><a href="/requests/list">Danh sách đơn nghỉ phép</a></li>
        </c:if>
    </ul>
</div>
</body>
</html>

