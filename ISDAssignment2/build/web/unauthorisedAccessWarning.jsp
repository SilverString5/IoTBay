<%-- 
    Document   : unauthorisedAccessWarning
    Created on : 13 May 2024, 7:34:55 am
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
        
        <% User user = (User) session.getAttribute("user"); %>
        
        
        <%if(user != null && user.getUserType().equals("S")){%>
            <div class="menu">
                <ul>
                    <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" >You</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/viewAccessLogs.jsp" >Your Access Logs</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/manageRegistration.jsp" >Manage Account Details</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp" >Logout</a></li>
                </ul>
                                
            </div>
        <%} else if(user != null && user.getUserType().equals("C")){%>
            <div class="menu">
                <ul>
                    <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" >You</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/viewAccessLogs.jsp" >Your Access Logs</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/manageRegistration.jsp" >Manage Account Details</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/DisplayCartServlet">Shopping Cart</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/OrderHistoryServlet" >Orders</a></li>
                    <li><a href="./shipmentHistory" >Shipping</a></li>                 
                    <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp" >Logout</a></li>
                </ul>                             
            </div>
            
        <%}else{ %>
            <div class="menu">
                <ul>
                    <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/login.jsp" >Login</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" >Register</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/DisplayCartServlet">Shopping Cart</a></li>
                    <li><a href="http://localhost:8080/ISDAssignment1/OrderHistoryServlet" >Orders </a></li>
                </ul>              
            </div>
        <% } %>
        
        <div class="body-background">
        
            <div class="window">
                <h1>Unauthorised Access</h1>
                <p>You are attempting to access a page that you should only access from shipment history. Please go back to the shipment history page to delete or update your shipment record</p>
            
                <form method="POST" action="./index.jsp">
                    <button type="submit">Back to Home</button>
                </form>
            
            </div>
            
        </div>
    </body>
</html>
