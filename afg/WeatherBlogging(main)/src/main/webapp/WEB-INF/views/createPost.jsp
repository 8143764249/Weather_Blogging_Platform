<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>

<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Create Blog Post</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: white;
            text-align: center;
            padding-top: 50px;
        }
        .container {
            background: rgba(0, 0, 0, 0.2);
            padding: 20px;
            border-radius: 10px;
            display: inline-block;
            text-align: left;
            width: 40%;
        }
        input, textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: none;
            border-radius: 5px;
        }
        button {
            background-color: #0072ff;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background-color: #005bb5;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Create a New Blog Post</h2>
<form action="${pageContext.request.contextPath}/blog/publish" method="post" enctype="multipart/form-data">
    <label>Title:</label>
    <input type="text" name="title" required>

    <label>Area:</label>
    <input type="text" name="area" required>

    <label>Image:</label>
    <input type="file" name="imageFile" accept="image/*" required>

    <label>Description:</label>
    <textarea name="description" rows="4" required></textarea>
    
    <label>Tags:</label>
<input type="text" name="tagNames" placeholder="Enter tags comma separated (e.g., storm,clouds)" />
    

    <button type="submit">Publish</button>
</form>


</div>

</body>
</html>
