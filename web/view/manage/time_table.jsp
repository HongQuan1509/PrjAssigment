<%-- 
    Document   : time_table
    Created on : Feb 28, 2025, 9:52:02 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Work Time</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .working-day { background-color: #d4edda !important; } /* Xanh - ngày làm việc */
        .leave-day { background-color: #f8d7da !important; }  /* Đỏ - ngày nghỉ */
        .empty-day { background-color: #f1f1f1 !important; }  /* Xám - ô trống */
    </style>
</head>
<body class="container mt-5">
    <jsp:include page="../banner/banner.jsp"></jsp:include>
<h2 class="text-center"> Work Time </h2>

<!-- Dropdown chọn năm và tháng -->
<div class="d-flex align-items-center mb-3">
    <label class="me-2"><b>Month</b></label>
    <select id="monthSelect" class="form-select w-auto" onchange="updateCalendar()">
        <c:forEach var="i" begin="0" end="11">
            <option value="${i}" ${i == currentMonth ? 'selected' : ''}>thang ${i + 1}</option>
        </c:forEach>
    </select>

    <label class="me-2"><b>Year</b></label>
    <select id="yearSelect" class="form-select w-auto me-3" onchange="updateCalendar()">
        <c:forEach var="i" begin="${currentYear - 2}" end="${currentYear + 2}">
            <option value="${i}" ${i == currentYear ? 'selected' : ''}>${i}</option>
        </c:forEach>
    </select>
</div>

<!-- Bảng hiển thị lịch làm việc -->
<table class="table table-bordered text-center">
    <thead class="table-dark">
    <tr>
        <th>Monday</th>
        <th>Tuesday</th>
        <th>Wednesday</th>
        <th>Thursday</th>
        <th>Friday</th>
        <th>Saturday</th>
        <th>Sunday</th>
    </tr>
    </thead>
    <tbody id="calendarBody">
        <c:forEach var="week" items="${calendar}">
            <tr>
                <c:forEach var="day" items="${week}">
                    <td class="${day.type}" >${day.value}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script>
    function updateCalendar() {
        let year = document.getElementById("yearSelect").value;
        let month = document.getElementById("monthSelect").value;
        window.location.href = `time_table.jsp?year=${year}&month=${month}`;
    }
</script>
</body>
</html>

