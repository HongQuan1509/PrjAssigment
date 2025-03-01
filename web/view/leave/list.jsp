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
                <td>Id</td>
                <td>Title</td>
                <td>Reason</td>
                <td>From</td>
                <td>To</td>
                <td>Created By</td>
                <td>Created Date</td>
                <td>Status</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${list}">
        <tr>
            <td>
                <c:url value="/leaverequest/review" var="reviewUrl">
                    <c:param name="title" value="${request.title}" />
                </c:url>
                <a href="${reviewUrl}">${request.title}</a>
            </td>
            <td>${request.title}</td>
            <td>${request.reason}</td>
            <td>${request.from}</td>
            <td>${request.to}</td>
            <td>${request.createdby.displayname}</td>
            <td>${request.createddate}</td>
            <td>${request.status eq 0?"In Progress":(request.status eq 1)?"Rejected":"Accepted"}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

