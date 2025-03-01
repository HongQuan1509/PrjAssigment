<%-- 
    Document   : banner
    Created on : Mar 1, 2025, 9:58:52 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value='/home'/>">Leave Request Management</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <span class="nav-link">Hi, <strong>${user.displayname}</strong>!</span>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-danger text-white" href="<c:url value='/logout'/>">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
    </body>
</html>
