<%-- 
    Document   : home
    Created on : Feb 28, 2025, 9:34:52 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Leave Request Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
     <jsp:include page="../banner/banner.jsp"></jsp:include>


    
<ul>
        <c:forEach var="feature" items="${features}">
            <li><a href="<c:url value='${feature.url}' />">${feature.name}</a></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>

