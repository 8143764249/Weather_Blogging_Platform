<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String username = (String) session.getAttribute("loggedUser");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home - Weather Blog</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            margin: 0;
            padding: 0;
            color: #fff;
        }

        header {
            background-color: rgba(0, 0, 0, 0.3);
            padding: 20px;
            text-align: center;
        }

        header h1 {
            margin: 0;
            font-size: 2.5em;
            color: #ffffff;
        }

        .navigation {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            background-color: rgba(255, 255, 255, 0.15);
            padding: 10px 30px;
            gap: 20px;
        }

        .navigation a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
        }

        .navigation a:hover {
            text-decoration: underline;
        }

        .navigation form {
            display: flex;
            align-items: center;
        }

        .navigation input[type="text"] {
            padding: 5px 10px;
            border-radius: 5px;
            border: none;
        }

        .navigation button {
            margin-left: 8px;
            padding: 6px 10px;
            border-radius: 5px;
            background-color: #ffcc00;
            border: none;
            font-weight: bold;
            cursor: pointer;
        }

        .content {
            text-align: center;
            padding: 40px 20px;
        }

        .welcome {
            font-size: 1.8em;
            margin-bottom: 30px;
        }

        .latest-blogs {
            background: #fff;
            color: #333;
            margin: 0 auto;
            width: 80%;
            border-radius: 10px;
            padding: 20px;
        }

        .blog-post {
            border-bottom: 1px solid #ccc;
            margin-bottom: 20px;
            padding-bottom: 20px;
        }

        .blog-post h3 {
            color: #0072ff;
        }

        .blog-post img {
            max-width: 100%;
            border-radius: 5px;
            margin-top: 10px;
        }

        .about-us {
            margin-top: 50px;
            padding: 30px;
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 10px;
        }

        .about-us h2 {
            color: #ffcc00;
        }

        .about-us p {
            font-size: 1.1em;
        }
    </style>
</head>
<body>

<header>
    <h1>Welcome to Nikki's Blogging Platform</h1>
</header>

<div class="navigation">
    <a href="/blog/create">Create Post</a>
    <a href="/aboutus">About Us</a>
    <a href="/blog/all">Latest Posts</a>

    <form action="/blog/search" method="get">
        <input type="text" name="area" placeholder="Search area..." required />
        <button type="submit">Search</button>
    </form>
    
<a href="${pageContext.request.contextPath}/user/profile">Profile</a>
    <c:if test="${not empty profileUpdateSuccess}">
    <script>
        alert('${profileUpdateSuccess}');
    </script>
</c:if>
  
    

    <a href="/user/logout">Logout</a>
</div>

<div class="content">
    <div class="welcome">
        Hello, <strong><%= username %></strong> 👋
    </div>

    <div class="latest-blogs">
        <h2>Latest Weather Blogs</h2>
        <c:forEach var="blog" items="${latestBlogs}">
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
    </div>

    <div class="about-us">
        <h2>About Us</h2>
        <p>We are passionate weather enthusiasts who created this platform to share real-time weather experiences, tips, and forecasts for various areas. Join us to contribute your own observations and read about others’ adventures!</p>
    </div>
</div>

</body>
</html>






