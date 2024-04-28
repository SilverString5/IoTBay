<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <title>Login</title>
    </head>
    <%
            User user = (User) session.getAttribute("user");
    %>
        
    <%
            String email1 = request.getParameter("email1");
            String password1 = request.getParameter("password1");
            String submitted1 = request.getParameter("submitted1");
    %>
        <%if (submitted1 != null){
            if (!(email1.equals(user.getEmail())) || !(password1.equals(user.getPassword()))) {%>
             <h1>Your username or password is wrong. Please try again.</h1>
             <a href="login.jsp">Try again.</a>
            <% } else { %>
                <!--<h1>Welcome, <//%=user.getName()%> .</h1>-->
                <!--<p>Here is a link to your homepage.</p><br><br>-->
                <!--<a href="welcome.jsp">Welcome Page</a>-->
                <%response.sendRedirect("welcome.jsp");%>

            <%}%>
        <%}%>
        
    <body>
        <% if (submitted1 == null){%>
        <h1>Login Here!</h1>
        <form method="post">
            <label for="email1">Email: </label><br>
            <input type="text" id = "email1" name = "email1"><br><br>

            <label for="Password">Password: </label><br>
            <input type= "password" id = "password1" name = "password1"><br><br>

            <input type="hidden" name="submitted1" id="submitted1" value ="true" />
            <button type="submit">Login</button>
        </form>

        <h3>Don't have an account?</h1>
            <div>
                <a href="register.jsp">Register</a>
            </div>  
        
        <% } %>
    </body>
    
</html>