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
    <title>Leave Request</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
     <jsp:include page="../banner/banner.jsp"></jsp:include>

<h2 class="text-center">Leave Request List</h2>

<table class="table table-bordered table-striped">
    <thead class="table-dark">
    <tr>
        <th>Title</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Created By</th>
        <th>Status</th>
        <th>Processed By</th>
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

