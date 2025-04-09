<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>About Us</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: white;
            text-align: center;
            margin-top: 50px;
        }
        .container {
            padding: 20px;
            background: rgba(0, 0, 0, 0.2);
            display: inline-block;
            border-radius: 10px;
            width: 60%;
        }
        a {
            color: #ff9800;
            text-decoration: none;
            font-size: 18px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>About Us</h2>
        <p>Welcome to the Weather Blogging Platform! Here, you can share your experiences, forecasts, and weather updates with the community.</p>
        <p>Our goal is to bring weather enthusiasts together to discuss and share insights.</p>
        <br>
        <a href="/home">Back to Home</a> <!-- ✅ Fixed Home Link -->
    </div>
</body> 
</html>
