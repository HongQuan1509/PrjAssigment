<%-- 
    Document   : time_table
    Created on : Feb 28, 2025, 9:52:02 PM
    Author     : admin
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="../banner/banner.jsp"></jsp:include>
            <title>Employee Work Calendar</title>

            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

            <style>
                .working-day {
                    background-color: #d4edda !important;
                }
                .leave-day {
                    background-color: #f8d7da !important;
                }
                .empty-day {
                    background-color: #f1f1f1 !important;
                }
                .today {
                    border: 2px solid #007bff !important;
                }

                td {
                    height: 100px;
                    vertical-align: top;
                }
            </style>
        </head>

        <body>

            <h2 class="text-center mb-4">Employee Work Calendar</h2>
            <form id="calendarForm" action="time-table" method="GET" class="row justify-content-center mb-4">

                <!-- Filters -->
                <div class="col-auto">
                    <label class="form-label"><strong>Employee</strong></label>
                    <select id="employeeSelect" name="employeeId" class="form-select" onchange="submitForm()">

                    <c:forEach var="emp" items="${directstaffs}">
                        <option value="${emp.id}" ${employeeId == emp.id ?'selected':' '}>${emp.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="col-auto">
            <label for="monthSelect" class="form-label"><strong>Month</strong></label>
            <select id="monthSelect" name="month" class="form-select" onchange="submitForm()">
                <c:forEach var="m" begin="1" end="12">
                    <option value="${m}" ${m == month ? 'selected' : ''}>${m}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-auto">
            <label for="yearSelect" class="form-label"><strong>Year</strong></label>
            <select id="yearSelect" name="year" class="form-select" onchange="submitForm()">
                <c:forEach var="y" begin="${year - 5}" end="${year + 5}">
                    <option value="${y}" ${y == year ? 'selected' : ''}>${y}</option>
                </c:forEach>
            </select>
        </div>


    </form>
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
        <tbody>
            <c:forEach var="week" items="${calendarData}">
                <tr>
                    <c:forEach var="day" items="${week}">
                        <td class="${day.type} ${day.isToday ? 'today' : ''}">
                            <c:if test="${day.day > 0}">${day.day}</c:if>
                            </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <script>
        function submitForm() {
            document.getElementById('calendarForm').submit();
        }
    </script>

</body>
</html>