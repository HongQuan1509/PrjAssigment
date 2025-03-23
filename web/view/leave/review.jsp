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
    <!-- SweetAlert2 CDN -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
    <jsp:include page="../banner/banner.jsp"></jsp:include>

    <h2 class="text-center">Request Approval</h2>

    <div class="card p-4 shadow-sm" style="max-width: 500px; margin: auto;">
        <p><strong>Created By:</strong> <span>${lr.createdby.e.name}</span></p>
        <p><strong>Start Date:</strong> <span>${lr.from}</span></p>
        <p><strong>End Date:</strong> <span>${lr.to}</span></p>
        <p><strong>Reason:</strong> <span>${lr.reason}</span></p>

        <form action="review" method="post" id="myForm">
            <input type="hidden" name="requestid" value="${lr.id}">

            <div class="d-flex justify-content-center gap-3">
                <button type="button" name="action" value="approve" id="approveBtn" class="btn btn-success">Approve</button>
                <button type="button" name="action" value="reject" id="rejectBtn" class="btn btn-danger">Reject</button>
            </div>
        </form>
    </div>

    <script>
        const approveBtn = document.getElementById('approveBtn');
        const rejectBtn = document.getElementById('rejectBtn');
        const form = document.getElementById('myForm');

        approveBtn.addEventListener('click', () => {
            Swal.fire({
                title: 'Are you sure?',
                text: "You are about to approve this request.",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Yes, approve it!',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    addHiddenActionAndSubmit('approve');
                }
            });
        });

        rejectBtn.addEventListener('click', () => {
            Swal.fire({
                title: 'Are you sure?',
                text: "You are about to reject this request.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, reject it!',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    addHiddenActionAndSubmit('reject');
                }
            });
        });

        function addHiddenActionAndSubmit(actionValue) {
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'action';
            input.value = actionValue;
            form.appendChild(input);

            form.submit();
        }
    </script>
</body>
</html>
