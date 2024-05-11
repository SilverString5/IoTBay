<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Login</title>
    </head>
    <%
           String invalidLogin = (String) session.getAttribute("invalidLogin");
           User user = (User) session.getAttribute("user");
           if (user!=null){
    %>
                 <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/viewAccessLogs.jsp" >Your Access Logs</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/manageRegistration.jsp" >Manage Account Details</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp" >Logout</a></li>
            </ul>
            </div>
    <% } else { %>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/login.jsp" > Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" > Register</a></li>
            </ul>
            </div>
    <% } %>
            <br>
            <br>
            <br>
   
    <body>
        <% if (user==null) { %>
        <div class="myFormdiv">
        
        <form class="myForm" method="post" action="<%= request.getContextPath()%>/LoginServlet">
            <h1 class="myHeader">Login Here!</h1>
            <label for="email1">Email: </label><br>
            <input type="text" id = "email" placeholder = "email" name = "email" required><br><br>

            <label for="Password">Password: </label><br>
            <input type= "password" id = "password" placeholder="password" name="password" required><br><br>

            <input type="hidden" name="submitted" id="submitted" value="true" />
            <button type="submit">Login</button><br>
                <% if(invalidLogin != null) { %>
           <br>
                <p><%=invalidLogin%></p>
            <% } %>
                    <p>Don't have an account?</p>
        <a href="register.jsp">Register</a>
<% } else { %>
<p>You have already logged in</p>
<% } %>
        
        </form>
                    
            <br>

                    </div>
           

   
    </body>
    
</html>