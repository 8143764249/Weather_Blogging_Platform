<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Your Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: #fff;
            padding: 50px;
            text-align: center;
        }
        .container {
            background: rgba(255, 255, 255, 0.1);
            padding: 20px;
            border-radius: 10px;
            display: inline-block;
            color: white;
        }
        .btn {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #ff9800;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #e68a00;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Profile</h2>
        <p><strong>Username:</strong> ${user.username}</p>
        <p><strong>Email:</strong> ${user.email}</p>

        <form action="${pageContext.request.contextPath}/user/updateForm" method="get">
            <button class="btn" type="submit">Update Profile</button>
        </form>
    </div>
</body>
</html>
