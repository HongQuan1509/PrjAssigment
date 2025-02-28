<%-- 
    Document   : login
    Created on : Feb 28, 2025, 9:34:47 PM
    Author     : admin
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
<div class="card p-4 shadow-sm">
  <h2 class="text-center">Đăng nhập</h2>
  <form action="login" method="post">
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
      <div class="alert alert-danger"><%= error %></div>
    <% } %>
    <div class="mb-3">
      <label class="form-label">Tài khoản:</label>
      <input type="text" name="username" class="form-control" required>
    </div>
    <div class="mb-3">
      <label class="form-label">Mật khẩu:</label>
      <input type="password" name="password" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-primary w-100">Login</button>
  </form>
</div>
</body>
</html>
