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
        <link rel="stylesheet" href="./css/test.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Shipments</title>
    </head>
    
    <body>

        <% Shipments shipments = (Shipments) session.getAttribute("shipments"); 
           String shipmentFilterError = (String) session.getAttribute("shipmentFilterError");
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
        
        
        <div class="middle-container">
            
            <% if(user.getUserType().contains("C")) { %>
            
                <h1>Track Your Orders</h1>
        
        
                <form method="POST" action="shipmentHistory">
                
                    <div class="search-bar">
                        <label for="shipmentID">Shipment ID: </label>
                        <input type="number" id = "shipmentID" name = "shipmentID">
               

                        <label for="date">Date: </label>
                        <input type="date" id = "date" name = "date">

                        <input type="hidden" name="searchButton" value="true">
                        <button type="submit">Search</button>

                        <input type="hidden" name="resetButton" value="true">
                        <button><a href="./shipmentHistory.jsp">Reset</a></button>
                    </div>
                
                </form>
            
                <%if(shipmentFilterError != null) {%> 
                    <p class="errorMessage"><%=shipmentFilterError%></p> 
                <% } %>

        
                <% if(shipments != null) {%>
                    <table class="table">
                    <tr>
                        <th>Shipment ID: </th>
                        <th>Address: </th>
                        <th>Method: </th>
                        <th>Status: </th>
                        <th>Date: </th>
                        <th>View or Delete Shipment</th>
                    </tr>
            
            <!-- For Loop That Finds All Shipment Records Based On Prompt -->
            
                    <% for(Shipment shipment : shipments.getListOfCustomerShipments()) { %>
                        <tr>
                            <td><%=shipment.getShipmentID()%></td>
                            <td><%=shipment.getShipmentAddress()%></td>
                            <td><%=shipment.getShipmentMethod()%></td>
                            <td><%=shipment.getShipmentStatus()%></td>
                            <td><%=shipment.getShipmentEstTime()%></td>
                            <td>
                                <a href="./shipmentDetail?shippingID=<%=shipment.getShipmentID() %>"> View >>> </a> 
                                <%if (shipment.getShipmentStatus().equals("Pending")) {%>
                                    <a href="./deleteShipment.jsp?shippingID=<%=shipment.getShipmentID() %>"> Delete >>> </a>
                                <%}%>
                            </td>                
                        </tr>
                    <% } %>
            
                <% } else { %>
                    <h4>You don't have any shipments at the moment</h4>
                <% } %>
               
                </table>
                
            <% } else { 
                response.sendRedirect("./unregisteredWarning.jsp");
             } %>
            
        </div>
        
    </body>
</html>
