<%-- 
    Document   : main
    Created on : Mar 28, 2024, 3:29:37 PM
    Author     : pyaephyozaw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Welcome Page</title>
    </head>
    <body>


        <% User user = (User)session.getAttribute("user");%>
        
        <%if (session.getAttribute("user") != null){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/login.jsp" > Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
            </ul>
            </div>
         
            <br>
            <br>
            <p>Welcome, <%=user.getName()%></p>
            <a href="manageRegistration.jsp">Manage Your Account Details</a>
            <a href="viewAccessLogs.jsp">View Your Access Logs</a>
            
           

            <%}  else {%>
                        <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
            </ul>
                            <br><br><br>
            </div>
            <div class="myFormdiv">
            <form class="myForm">
                
        <h1> Hello Anonymous User, </h1>
        <br>
        <h2> Do you want to register? </h2>
        <br>
        <a href="register.jsp">Register</a>
            </form>
            </div>
        <% } %>
    </body>
</html>
