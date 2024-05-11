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
                <li><a href="http://localhost:8080/ISDAssignment1/viewAccessLogs.jsp" >Your Access Logs</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/manageRegistration.jsp" >Manage Account Details</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp" >Logout</a></li>
            </ul>
            </div>
         
            <br>
            <br>
            <p>Welcome, <%=user.getName()%></p>
   
            <form action="<%= request.getContextPath()%>/ViewAccessLogsServlet" method="post">
                <input type="hidden" id="origin" name="viewAll" value="viewAll">
                <button type="submit">View Your Access Logs</button><br>   
            </form>
            <form action="<%= request.getContextPath()%>/ViewRegistrationServlet" method="post">
                <button type="submit">Manage Your Account Details</button><br>
            </form>

            <%}  else {%>
                        <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/login.jsp" > Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" > Register</a></li>

            </ul>
            </div>
                        <br><br><br>
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