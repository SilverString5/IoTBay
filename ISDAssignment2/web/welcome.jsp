<%-- 
    Document   : main
    Created on : Mar 28, 2024, 3:29:37 PM
    Author     : pyaephyozaw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%> 
<%@page import="uts.isd.model.*"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page import="uts.isd.controller.*"%>
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
        
        <%if(request.getParameter("submitted") != null){%>
            <%  
            
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phoneNumber = request.getParameter("phonenumber");
                String address = request.getParameter("address");
                String password = request.getParameter("password");
                User user = new User("Staff", email, name, phoneNumber, password, address);
                session.setAttribute("user", user);
               
            %>
        <%}%>
        <% User user = (User)session.getAttribute("user");%>
        
        <%if (user != null){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment2/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
            
        <%}else {%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment2/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/login.jsp" > Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
        
        <%}%>
        
        
        <%if(user != null){%>
            <h1>Hello <%=user.getName()%>,
                <br> 
                Welcome to IoTBay Web Application
            </h1>
            <button><a href="logout.jsp">Log out</a></button>
         </div>
            
        <%}  else {%>
            <h1> Hello Anonymous User, </h1>
            <h2> Do you want to register? </h2>
            <button><a href="register.jsp">Register</a></button>
        
        <% } %>
    </body>
</html>