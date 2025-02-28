<%-- 
    Document   : create
    Created on : Feb 28, 2025, 9:35:20 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Đơn xin nghỉ phép</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2 class="text-center">Đơn xin nghỉ phép</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<form action="leave/request" method="post" class="card p-4 shadow-sm">
    <div class="mb-3">
        <label class="form-label">Tiêu đề:</label>
        <input type="text" name="title" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Từ ngày:</label>
        <input type="date" name="start_date" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Đến ngày:</label>
        <input type="date" name="end_date" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Lý do:</label>
        <textarea name="reason" class="form-control" required></textarea>
    </div>
    <button type="submit" class="btn btn-success w-100">Gửi</button>
</form>
</body>
</html>
