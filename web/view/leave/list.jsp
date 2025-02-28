<%-- 
    Document   : list
    Created on : Feb 28, 2025, 9:35:26 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Yêu cầu nghỉ phép</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2 class="text-center">Yêu cầu nghỉ phép</h2>

<table class="table table-bordered table-striped">
    <thead class="table-dark">
    <tr>
        <th>Đơn nghỉ</th>
        <th>Từ</th>
        <th>Đến</th>
        <th>Người tạo</th>
        <th>Trạng thái</th>
        <th>Người xác nhận</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${leaveRequests}">
        <tr>
            <td>
                <a href="request/${request.id}">${request.title}</a>
            </td>
            <td>${request.startDate}</td>
            <td>${request.endDate}</td>
            <td>${request.createdBy}</td>
            <td>${request.status}</td>
            <td>${request.processedBy}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

