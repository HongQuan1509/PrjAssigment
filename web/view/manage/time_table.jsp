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
                    background-color: #d4edda;
                } /* Green */
                .leave-day {
                    background-color: #f8d7da;
                }   /* Red */
                .empty-day {
                    background-color: #f1f1f1;
                }   /* Gray */
                .today {
                    border: 2px solid #007bff;
                }       /* Blue border */
                td {
                    height: 100px;
                    vertical-align: top;
                }
            </style>
        </head>

        <body>

            <h2 class="text-center mb-4">Employee Work Calendar</h2>

            <!-- Filters -->
            <div class="row justify-content-center mb-4">
                <div class="col-auto">
                    <label class="form-label"><strong>Employee</strong></label>
                    <select id="employeeSelect" class="form-select">

                    <c:forEach var="emp" items="${directstaffs}">
                        <option value="${emp.id}">${emp.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-auto">
                <label class="form-label"><strong>Month</strong></label>
                <select id="monthSelect" class="form-select"></select>
            </div>

            <div class="col-auto">
                <label class="form-label"><strong>Year</strong></label>
                <select id="yearSelect" class="form-select"></select>
            </div>
        </div>

        <!-- Calendar Table -->
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
            <tbody id="calendarBody"></tbody>
        </table>

        <!-- JS to load calendar -->
        <script src="../js/calendar.js"></script>
        <script>
            const currentYear = ${year}; // Inject từ server vào
            const currentMonth = ${month}; // Inject từ server vào
            initCalendar(currentYear, currentMonth);
        </script>

    </body>
</html>
