<%-- 
    Document   : updateShipmentForm
    Created on : 6 May 2024, 6:09:23 pm
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
        
        <title>Order</title>
        
    </head>
    
    <body>
        
        <%Shipment shipment = (Shipment) session.getAttribute("shipment");%>
        
        <% if(shipment.getShipmentStatus().equals("Pending")) {%>
        <div class="shipping-container">
            <h2>Shipping Address</h2>
            
            
            <form method="POST" action="/updateShipmentDetail">
                
                <div class="shipping-details-container">
                <label for="streetAddress">Street Address: </label><br>
                <input type="text" id = "streetAddress" name = "streetAddress" value="<%=shipment.getShipmentAddress()%>">
                
                <label for="shipmentMethod">Delivery Method: </label><br>
                
                <select name="deliveryMethod" value="<%=shipment.getShipmentMethod()%>">
                    
                    <% if(shipment.getShipmentMethod().equals("Standard")){ %>
                    <option value="Standard" selected>Standard</option>
                    <option value="Express">Express</option>
                    
                    <%} else {%>
                    <option value="Standard">Standard</option>
                    <option value="Express" selected>Express</option>
                    
                    <% } %>
                </select>
            
                
               </div> 

                <button type="submit"> Submit </button>
                <!-- class="submit-button" -->
        </form>
        </div>
        <%} else {%>
        
        <p> You are trying to modify a shipment when its on the way. Click on the button below to go back to shipmentHistory</p>
        <button><a href="./shipmentHistory.jsp">Go Back To Shipment History</a></button>
        <%} %>
           
        <!--
       <div class="flexbox">
        <div class="left-container">
            
                
                
                <div class="shipping-details-container">
                
                    <label for="streetAddress">Street Address: </label><br>
                    <input type="text" id = "streetAddress" name = "streetAddress">

                    <div class="shipping-details-columns">
                        <table>
                            <tr>
                                <td>City: </td>
                                <td>State/Territory: </td>
                                <td>Postcode: </td>
                            </tr>
                            <tr>
                                <td><input type= "text" id = "city" name = "city"></td>
                                <td>
                                    <div class="country-dropdown">
                                    <select name="suburb">
                                        <option value="" selected> choose one</option>
                                        <option value="act">Australian Capital Territory</option>
                                        <option value="nsw">New South Wales</option>
                                        <option value="nt">Northern Territory</option>
                                        <option value="qld">Queensland</option>
                                        <option value="sa">South Australia</option>
                                        <option value="tas">Tasmania</option>
                                        <option value="vic">Victoria</option>
                                        <option value="wa">Western Australia</option>
                                    </select>
                                    </div>
                                </td>
                                <td><input type= "text" id = "postcode" name = "postcode"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                

                
                
                
                
            </form>
           
                
        </div>
        
        
        <div class="right-container"> 
                    <h1>wee</h1>
            </div>
        </div>-->
        
    </body>
</html>
