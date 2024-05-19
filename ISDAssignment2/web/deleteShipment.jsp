<%-- 
    Document   : deleteShipment
    Created on : 10 May 2024, 4:17:44 pm
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
        
        <title>Delete Shipment</title>
    </head>
    <body>
        
        <%
          User user = (User) session.getAttribute("user");
        %>
        
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
        
        <%if(user != null && !user.getUserType().contentEquals("S")) {%>
         
            <% String shipmentID = request.getParameter("shippingID"); %>
                    
            <% if(shipmentID != null) { %>
         
            <div class="body-background">
        
                <div class="window">
                    <h1>Would you like to cancel your shipment?</h1>
                    <p>By clicking the button below, you are confirming that your shipment for this order will be cancelled</p>
                    
                    <form method="POST" action="./deleteShipmentDetail?shippingID=<%=shipmentID%>">
                        <button type="submit">Delete</button>
                    </form>
                    <a href="./shipmentHistory.jsp"><button>Back to Shipment History</button></a>
                </div>
            </div>
                        
            <% } else { 
                response.sendRedirect("./unauthorisedAccessWarning.jsp");
             } %>
                    
        <% } else { 
            response.sendRedirect("./unregisteredWarning.jsp");
        }%>
    </body>
</html>
