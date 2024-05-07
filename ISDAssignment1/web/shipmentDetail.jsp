<%-- 
    Document   : shipmentDetail
    Created on : 3 May 2024, 3:12:42 pm
    Author     : lorinchanel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/css/test.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Order Detail</title>
    </head>
    <body>
        
        <%Shipment shipment = (Shipment) session.getAttribute("shipment"); %>
        
        <h2>Tracking Detail</h2> <!-- Add Order Number -->
        
        
        
        <div class="shipment-status-container">
            <h2>Track Your Package</h3>
            <h3>Estimated Delivery Date: </h2> <!-- calculate estimated time - 5 days after order date -->
        </div>
        
        <div class="flexbox">
            <div class="first-column">
                <h2>Shipment Information</h2>
                
                <h3>Shipping Address: <%=shipment.getShipmentAddress()%></h3> 
                <h3>Delivery Method: <%=shipment.getShipmentMethod()%></h3>
                
                <button><a href="./shipmentHistory.jsp">Back To Shipments</a></button>
                
                <form method="POST" action="/deleteShipmentDetail">
                    <button type="submit">Delete</button>
                </form>
                
                <%if (shipment.getShipmentStatus().equals("Pending")) {%>
                <button type="submit"><a href="./updateShipmentForm.jsp">Update</a></button>
                <% } %>

            </div>
            
            <div class="second-column">
                <h2>Tracking History</h2> <!-- Add all tracking logs -->
            </div>
        </div>
        
        
    </body>
</html>
