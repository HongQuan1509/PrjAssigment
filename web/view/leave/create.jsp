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
    <title>Leave Request</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
 <jsp:include page="../banner/banner.jsp"></jsp:include>
<h2 class="text-center">Leave Request</h2>

<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<form action="create" method="post" class="card p-4 shadow-sm">
    <div class="mb-3">
        <label class="form-label">Title:</label>
        <input type="text" name="title" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Start Date:</label>
        <input type="date" name="from" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">End Date:</label>
        <input type="date" name="to" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Reason:</label>
        <textarea name="reason" class="form-control" required></textarea>
    </div>
    <button type="submit" class="btn btn-success w-100">Send</button>
</form>
</body>
</html>
