<%-- 
    Document   : logout
    Created on : 28/03/2024, 4:41:05 PM
    Author     : jijianlan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log out</title>
        <link rel="stylesheet" href="css/layout.css">
    </head>
    <body>
        <% session.invalidate();
        
        %>
        <div class="menu">
        <ul>
            <li><a href="http://localhost:8080/IoTBayWebApplication/">Home</a></li>
            <li><a href="http://localhost:8080/IoTBayWebApplication/login.jsp" > Login</a></li>
            <li><a href="http://localhost:8080/IoTBayWebApplication/register.jsp" > Register</a></li>
            <li><a href="http://localhost:8080/IoTBayWebApplication/welcome.jsp" > You</a></li>
        </ul>
        </div>
        <br>
        <br>
        <h1>Logged out!</h1>
    </body>
</html>
