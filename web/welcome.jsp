<%-- 
    Document   : welcome
    Created on : 28/03/2024, 4:40:32 PM
    Author     : jijianlan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>You</title>
        <link rel="stylesheet" href="css/layout.css">
    </head>
    <body>
        <%  User user = (User) session.getAttribute("user");
            
        %>
        <%if(user == null){%> <!-- a new session will be automatically created once
                                                        page is refreshed after log out, so session exist, 
                                                      request.getSession(false) == null won't work for this case-->
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

            <h2>Hi anonymous user</h2>
            <h2>Creating an Account?</h2>
            <button><a href="http://localhost:8080/IoTBayWebApplication/register.jsp">Register</a></button>

        <%} else{%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/IoTBayWebApplication/">Home</a></li>
                <li><a href="http://localhost:8080/IoTBayWebApplication/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/IoTBayWebApplication/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>

            <%
                String email = user.getEmail();
                String name = user.getName();

            %>
            <h1>Welcome</h1>
            <h2>Email: <%= email%></h2>
            <h2>Name: <%= name%></h2>
            
        <%}%>
    </body>
</html>
