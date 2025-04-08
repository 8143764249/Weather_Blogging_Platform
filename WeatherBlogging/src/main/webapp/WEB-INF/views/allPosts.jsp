<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Blog Posts</title>
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
        }
        a:hover {
            text-decoration: underline;
        }
        .like-section {
            margin-top: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
        }

        .like-button {
            background-color: #ffcc00;
            border: none;
            padding: 6px 12px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            color: #000;
            font-size: 1em;
        }

        .like-button:hover {
            background-color: #ffaa00;
        }

        .like-count {
            font-weight: bold;
            color: #0072ff;
        }
    </style>
</head>
<body>

    <h2>All Blog Posts</h2>

    <div class="posts-container">
        <c:forEach var="blog" items="${allBlogs}">
            <div class="blog-post">
                <h3>${blog.title}</h3>
                <p><strong>Area:</strong> ${blog.area}</p>
                <p><strong>Author:</strong> ${blog.author.username}</p>
                <p><strong>Date:</strong> ${blog.timestamp}</p>
                <p>${blog.description}</p>
                <c:if test="${not empty blog.imageName}">
                    <img src="/uploads/${blog.imageName}" alt="Image for ${blog.title}" />
                </c:if>

                <div class="like-section">
                    <form action="/blog/like/${blog.id}" method="post">
                        <button class="like-button" type="submit">
                            👍 Like
                        </button>
                    </form>
                    <span class="like-count">
                        ${fn:length(blog.likes)} likes
                    </span>
                </div>

                <br>
                <a href="${pageContext.request.contextPath}/blog/update/${blog.id}">Update</a>
                <a href="${pageContext.request.contextPath}/blog/delete/${blog.id}" onclick="return confirm('Delete this post?');">Delete</a>
            </div>
        </c:forEach>
    </div>

    <br><a href="/home">← Back to Home</a>

</body>
</html>
