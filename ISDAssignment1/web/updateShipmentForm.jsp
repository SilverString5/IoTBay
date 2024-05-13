<%-- 
    Document   : updateShipmentForm
    Created on : 6 May 2024, 6:09:23 pm
    Author     : lorinchanel
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/test.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        
        <title>Order</title>
        
    </head>
    
    <body>
        
        <%Shipment shipment = (Shipment) session.getAttribute("shipment");
          String invalidAddress = (String) session.getAttribute("invalidUpdateAddress");
          ArrayList<String> errorMsg = (ArrayList<String>) session.getAttribute("invalidUpdateAddressArray");
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
        
        <% if(user != null && !user.getUserType().contentEquals("S")) {%>
            <% if(shipment.getShipmentStatus().equals("Pending")) {%>
        
                <div class="middle-container">    
                    <div class="shipping-container">
                        <h2>Shipping Address</h2>


                        <form method="POST" action="./updateShipmentDetail">

                            <div class="shipping-details-container">
                                <label for="streetAddress">Street Address: </label><br>
                                <input type="text" id = "streetAddress" name = "streetAddress" value="<%=shipment.getShipmentAddress()%>">

                                <label for="shipmentMethod">Delivery Method: </label><br>
                                <select name="deliveryMethod" value="<%=shipment.getShipmentMethod()%>">

                                <!-- Checks the shipmentMethod and depending on the information, it selects and shows this option first -->
                                <% if(shipment.getShipmentMethod().equals("Standard")){ %>
                                    <option value="Standard" selected>Standard</option>
                                    <option value="Express">Express</option>

                                <%} else {%>
                                    <option value="Standard">Standard</option>
                                    <option value="Express" selected>Express</option>

                                <% } %>
                                </select>


                            </div> 

                            <% if(invalidAddress != null){ %>
                                <p><%=invalidAddress%></p>
                            <% } %>

                            <% if(errorMsg != null){ 
                                for(String error : errorMsg){%>
                                    <p><%=error%></p>
                                <%}%>

                            <% } %>

                            
                            <div class="flexbox">
                                <div class="first-column">
                                    <button><a href="./shipmentDetail.jsp">Back to Shipment Detail</a></button>
                                </div>

                                <div class="second-column align-right">
                                    <button type="submit"> Submit </button>
                                </div>
                            </div>
                            
                            
                        </form>

                    </div>
                </div>
            <%} else {%>
                <p> You are trying to modify a shipment when its on the way. Click on the button below to go back to shipment history page</p>
                <button><a href="./shipmentHistory.jsp">Go Back To Shipment History</a></button>
            <%} %>
        <% } else { 
                response.sendRedirect("./unregisteredWarning.jsp");
        } %>

    </body>
</html>
