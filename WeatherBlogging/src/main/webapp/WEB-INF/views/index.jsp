<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather Blogging Platform</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: #fff;
            text-align: center;
            padding-top: 50px;
        }
        .header {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .button-container {
            margin-top: 20px;
        }
        button {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            margin: 5px;
        }
        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="header">Weather Blogging Platform</div>

    <div class="button-container">
        <form action="user/register" method="get">
            <button type="submit">New User</button>
        </form>

        <form action="user/login" method="get">
            <button type="submit">Existing User</button>
        </form>
    </div>
</body>
</html>
