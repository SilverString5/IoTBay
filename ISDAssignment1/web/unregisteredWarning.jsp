<%-- 
    Document   : unregisteredWarning
    Created on : 11 May 2024, 11:26:09 pm
    Author     : lorinchanel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/style.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        
        <title>Unable To Access</title>
    </head>
    <body>
        
        <%
           User user = (User) session.getAttribute("user");
        %>
        
        <div class="body-background">
        
            <div class="window">
                <h1>Unable To Access Page</h1>
                <p>You are attempting to access a page that is only visible for registered users.</p>
            
                <% if(user == null) {%>
                    <p>Please register an account first to access these web pages.</p>
                <%} else { %>
                    <p>As a staff member, you are unable to access this page. Please go back to the home page.</p>
                <% } %>
                
                <form method="POST" action="./index.jsp">
                    <button type="submit">Back to Home</button>
                </form>
            
            </div>
            
        </div>
    </body>
</html>
