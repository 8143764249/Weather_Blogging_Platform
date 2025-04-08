<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: #fff;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: rgba(0, 0, 0, 0.2);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        h2 {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: none;
            border-radius: 5px;
        }
        button {
            background-color: #ff9800;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            transition: background 0.3s ease;
        }
        button:hover {
            background-color: #e68a00;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Register</h2>

 <c:if test="${not empty error}">
    <script>
        alert("${error}");
    </script>
</c:if>

<form action="${pageContext.request.contextPath}/user/register" method="post">
            <label>Username:</label>
            <input type="text" name="username" required />

            <label>Email:</label>
            <input type="email" name="email" required />

            <label>Password:</label>
            <input type="password" name="password" required />

            <button type="submit">Register</button>
        </form>
    </div>
</body> 
</html>
