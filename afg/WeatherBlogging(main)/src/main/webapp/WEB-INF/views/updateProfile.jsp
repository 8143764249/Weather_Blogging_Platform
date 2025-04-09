<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: white;
            text-align: center;
            padding: 50px;
        }
        .form-container {
            background: rgba(0, 0, 0, 0.3);
            padding: 20px;
            border-radius: 10px;
            display: inline-block;
        }
        input {
            margin: 10px;
            padding: 8px;
            border-radius: 5px;
            border: none;
            width: 80%;
        }
        .btn {
            background-color: #ff9800;
            color: white;
            padding: 10px 20px;
            border: none;
            margin-top: 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #e68a00;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Update Your Profile</h2>
        <form action="${pageContext.request.contextPath}/user/update" method="post">
            <input type="hidden" name="id" value="${user.id}" />
            <input type="text" name="username" value="${user.username}" required /><br/>
            <input type="email" name="email" value="${user.email}" required /><br/>
            <input type="password" name="password" placeholder="Enter New Password" required /><br/>

            <button type="submit" class="btn">Update</button>
        </form>
    </div>

</body>
</html>
