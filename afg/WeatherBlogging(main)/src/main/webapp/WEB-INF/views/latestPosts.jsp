<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.weatherblog.model.User" %>

<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String loggedInUser = (String) session.getAttribute("loggedUser");
    request.setAttribute("loggedInUsername", loggedInUser);
%>


<!DOCTYPE html>
<html>
<head>
    <title>Latest Blog Posts</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            color: white;
            text-align: center;
            padding-top: 50px;
        }
        .posts-container {
            width: 80%;
            margin: auto;
            background: rgba(255, 255, 255, 0.9);
            color: #333;
            padding: 20px;
            border-radius: 10px;
        }
        .blog-post {
            margin-bottom: 30px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 15px;
        }
        img {
            max-width: 100%;
            height: auto;
            border-radius: 6px;
        }
        a {
            color: #ff9800;
            text-decoration: none;
            font-weight: bold;
            margin-right: 10px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <h2>Latest Blog Posts</h2>

    <div class="posts-container">
        <c:forEach var="blog" items="${latestBlogs}">
            <div class="blog-post">
                <h3>${blog.title}</h3>
                <p><strong>Area:</strong> ${blog.area}</p>
                <p><strong>Author:</strong> ${blog.author.username}</p>
                <p><strong>Date:</strong> ${blog.timestamp}</p>
                <p>${blog.description}</p>
                <p><strong>Tags:</strong>
    <c:forEach var="tag" items="${blog.tags}">
            ${tag.name}
    </c:forEach>
</p>
                

                <c:if test="${not empty blog.imageName}">
                    <img src="/uploads/${blog.imageName}" alt="Image for ${blog.title}" />
                </c:if>
                <form action="${pageContext.request.contextPath}/blog/toggleLike/${blog.id}" method="post">
   
</form>
<h4>Comments</h4>
<c:forEach var="comment" items="${blog.comments}">
    <div>
        <strong>${comment.user.username}</strong>: ${comment.content}
        
        <c:if test="${comment.user.username == loggedInUsername}">
            <!-- Edit -->
            <form action="${pageContext.request.contextPath}/comment/update/${comment.id}" method="post" style="display:inline;">
                <input type="text" name="content" value="${comment.content}" required />
                <button type="submit">Update</button>
            </form>

            <!-- Delete -->
            <a href="${pageContext.request.contextPath}/comment/delete/${comment.id}" onclick="return confirm('Delete comment?')">Delete</a>
        </c:if>
    </div>
</c:forEach>

<!-- Add Comment -->
<form action="${pageContext.request.contextPath}/comment/add/${blog.id}" method="post">
    <textarea name="content" placeholder="Write a comment..." rows="2" cols="40" required></textarea><br>
    <button type="submit">Comment</button>
</form>          
 <br>
                

                <!-- Only show to blog owner -->
                <c:if test="${blog.author.username == loggedInUsername}">
                    <a href="${pageContext.request.contextPath}/blog/update/${blog.id}">Update</a>
                    <a href="${pageContext.request.contextPath}/blog/delete/${blog.id}" onclick="return confirm('Delete this post?');">Delete</a>
                </c:if>
            </div>
        </c:forEach>
    </div>

    <br><a href="/home">← Back to Home</a>

</body>
</html>
