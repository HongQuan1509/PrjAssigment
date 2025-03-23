<%-- 
    Document   : myrequest
    Created on : Mar 15, 2025, 1:56:05 AM
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
        <script src="${pageContext.request.contextPath}/js/control/pagger.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/control/pagger.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../banner/banner.jsp"></jsp:include>

            <h2 class="text-center">My Leave Request</h2>
            <div id="toppagger" class="pagger mb-3"></div>

            <p class="text-end">Page ${pageindex} of ${totalpage}, showing ${requestScope.size} records.</p>
            
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
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
                        <td>${request.title}</td>
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
        
        <div id="botpagger" class="pagger mt-3"></div>

        <script>
    var baseUrl = '${pageContext.request.contextPath}/leaverequest/myrequest'; // hoặc servlet URL của bạn
    renderPagger('toppagger', ${pageindex}, 2, ${totalpage}, baseUrl);
    renderPagger('botpagger', ${pageindex}, 2, ${totalpage}, baseUrl);
        </script>

    </body>
</html>
