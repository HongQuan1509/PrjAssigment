<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Leave Request</title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/css/control/pagger.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/control/pagger.js" type="text/javascript"></script>

        <style>
            .pagger a {
                margin: 0 4px;
                padding: 6px 12px;
                text-decoration: none;
                border: 1px solid #ddd;
                border-radius: 4px;
                color: #007bff;
            }
            .pagger a:hover {
                background-color: #f1f1f1;
            }
            .pagger span.current {
                margin: 0 4px;
                padding: 6px 12px;
                font-weight: bold;
                background-color: #007bff;
                color: white;
                border-radius: 4px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../banner/banner.jsp"></jsp:include>

        <div class="container mt-4">
            <h2 class="text-center">Leave Request List</h2>

            <p class="text-end">Page ${pageindex} of ${totalpage}, showing ${requestScope.size} records.</p>

            <!-- Top Pagination -->
            <div id="toppagger" class="pagger my-3"></div>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>Title</th>
                        <th>Reason</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Created By</th>
                        <th>Created Date</th>
                        <th>Status</th>
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
                            <td>${request.reason}</td>
                            <td>${request.from}</td>
                            <td>${request.to}</td>
                            <td>${request.createdby.displayname}</td>
                            <td>${request.createddate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${request.status == 0}">In Progress</c:when>
                                    <c:when test="${request.status == 1}">Rejected</c:when>
                                    <c:otherwise>Accepted</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            
            <!-- Bottom Pagination -->
            <div id="botpagger" class="pagger my-3"></div>

            <!-- Only show pagger if more than 1 page -->
            <c:if test="${totalpage > 1}">
                <script>
                    const baseUrl = '${pageContext.request.contextPath}/leaverequest/list';
                    renderPagger('toppagger', ${pageindex}, 2, ${totalpage}, baseUrl);
                    renderPagger('botpagger', ${pageindex}, 2, ${totalpage}, baseUrl);
                </script>
            </c:if>
        </div>
    </body>
</html>
