<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Blog Post</title>
</head>
<body>

<h2>Update Blog Post</h2>

<form action="${pageContext.request.contextPath}/blog/update/${blog.id}" method="post" enctype="multipart/form-data">
    <label>Title:</label><br>
    <input type="text" name="title" value="${blog.title}" required><br><br>

    <label>Area:</label><br>
    <input type="text" name="area" value="${blog.area}" required><br><br>

    <label>Description:</label><br>
    <textarea name="description" rows="5" cols="30" required>${blog.description}</textarea><br><br>

    <label>Upload New Image:</label><br>
    <input type="file" name="imageFile"><br><br>

    <input type="submit" value="Update">
</form>

<br>
<a href="${pageContext.request.contextPath}/blog/all">← Back to All Posts</a>

</body>
</html>
