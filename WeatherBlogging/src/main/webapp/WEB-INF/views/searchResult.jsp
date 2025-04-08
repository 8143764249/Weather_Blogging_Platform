<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Search Results - Weather Blog</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            margin: 0;
            padding: 0;
            color: #fff;
        }
        .content {
            text-align: center;
            padding: 40px 20px;
        }
        .blog-post {
            background-color: #fff;
            color: #333;
            margin: 0 auto 20px;
            width: 80%;
            border-radius: 10px;
            padding: 20px;
        }
        .blog-post img {
            max-width: 100%;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="content">
    <h2>Search Results for Area: <c:out value="${searchQuery}" /></h2>

    <c:choose>
        <c:when test="${not empty blogs}">
            <c:forEach var="blog" items="${blogs}">
                <div class="blog-post">
                    <h3>${blog.title}</h3>
                    <p><strong>Area:</strong> ${blog.area}</p>
                    <p><strong>Author:</strong> ${blog.author.username}</p>
                    <p><strong>Posted On:</strong> ${blog.timestamp}</p>
                    <p>${blog.description}</p>
                    <c:if test="${not empty blog.imageName}">
                        <img src="/uploads/${blog.imageName}" alt="Image for ${blog.title}" />
                    </c:if>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>No blogs found for the specified area.</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
