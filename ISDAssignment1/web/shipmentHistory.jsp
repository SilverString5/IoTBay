<%-- 
    Document   : shipmentHistory
    Created on : 3 May 2024, 2:38:36 pm
    Author     : lorinchanel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/css/test.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Shipments</title>
    </head>
    <body>

        <% Shipments shipments = (Shipments) session.getAttribute("shipments"); 
           String shipmentFilterError = (String) session.getAttribute("shipmentFilterError");
        %>
        
        
        <h1>Track Your Orders</h1>
        
        <div class="search-bar">
            <form method="POST" action="shipmentHistory">
                <label for="shipmentID">Shipment ID: </label>
                <input type="text" id = "shipmentID" name = "shipmentID">

                <label for="date">Date: </label>
                <input type="date" id = "date" name = "date">
                
                <%=request.getParameter("date") %>
                
                <input type="hidden" name="searchButton" value="true">
                <button type="submit">Search</button>
            </form>
            
            <%if(shipmentFilterError != null) {%> 
                <p><%=shipmentFilterError%></p> 
            <%}%>
            
            
            <button>Reset</button>
        </div>
        
        
        <table class="table">
            <tr>
                <th>Shipment ID: </th>
                <th>Address: </th>
                <th>Method: </th>
                <th>Status: </th>
                <th>Date: </th>
                <th></th>
            </tr>
            
            <!-- For Loop That Finds All Shipment Records Based On Prompt -->
            <% for(Shipment shipment : shipments.getListOfCustomerShipments()) { %>
            <tr>
                
                
                <input type="hidden" name="shipmentIDRecord" value="<%=shipment.getShipmentID() %>">
                <td><%=shipment.getShipmentID()%></td>
                
               <input type="hidden" name="shipmentAddressRecord" value="<%=shipment.getShipmentAddress() %>">
                <td><%=shipment.getShipmentAddress()%></td>
                
                <input type="hidden" name="shipmentMethodRecord" value="<%=shipment.getShipmentMethod() %>">
                <td><%=shipment.getShipmentMethod()%></td>
                
                <input type="hidden" name="shipmentStatusRecord" value="<%=shipment.getShipmentStatus() %>">
                <td><%=shipment.getShipmentStatus()%></td>
                
                <input type="hidden" name="shipmentDateRecord" value="<%=shipment.getShipmentEstTime() %>">
                <td><%=shipment.getShipmentEstTime()%></td>
                
                
                <td> <a href="./shipmentDetail?shippingID=<%=shipment.getShipmentID() %>"> View </a>
                    <!--<form method="POST">
                        <input type="hidden" name="viewButton" value="true"> 
                        <button type="submit">View</button>
                    </form>-->
                </td>
            </tr>
            <% } %>
                            
        </table>
        
    </body>
</html>
