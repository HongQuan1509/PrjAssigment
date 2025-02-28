<%-- 
    Document   : review
    Created on : Feb 28, 2025, 9:50:21 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Phê duyệt đơn nghỉ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<h2 class="text-center">Phê duyệt đơn nghỉ</h2>
<div class="card p-4 shadow-sm">
    <p><strong>Từ ngày:</strong> <span>${request.startDate}</span></p>
    <p><strong>Đến ngày:</strong> <span>${request.endDate}</span></p>
    <p><strong>Lý do:</strong> <span>${request.reason}</span></p>
    <form action="leave/review" method="post">
        <input type="hidden" name="request

