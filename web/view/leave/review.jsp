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
    <title>Request Approval</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h2 class="text-center">Request Approval</h2>
    <div class="card p-4 shadow-sm" style="max-width: 500px; margin: auto;">
        <p><strong>Created By:</strong> <span>${lr.createdby.e.name}</span></p>
        <p><strong>Start Date:</strong> <span>${lr.from}</span></p>
        <p><strong>End Date:</strong> <span>${lr.to}</span></p>
        <p><strong>Reason:</strong> <span>${lr.reason}</span></p>
        <form action="review" method="post">
            <input type="hidden" name="requestid" value="${lr.id}">
            
            <div id="rejectReasonDiv" class="mb-3" style="display: none;">
                <label for="rejectReason" class="form-label">Rejection Reason:</label>
                <textarea class="form-control" name="rejectReason" id="rejectReason" rows="3"></textarea>
            </div>
            
            <div class="d-flex justify-content-center gap-3">
                <button type="submit" name="action" value="approve" class="btn btn-success">Approve</button>
                <button type="button" id="rejectBtn" class="btn btn-danger">Reject</button>
                <button type="submit" name="action" value="reject" id="submitReject" class="btn btn-danger" style="display: none;">Confirm</button>
            </div>
        </form>
    </div>
    
    <script>
        document.getElementById("rejectBtn").addEventListener("click", function() {
            document.getElementById("rejectReasonDiv").style.display = "block";
            document.getElementById("rejectBtn").style.display = "none";
            document.getElementById("submitReject").style.display = "inline-block";
        });
    </script>
</body>
</html>
