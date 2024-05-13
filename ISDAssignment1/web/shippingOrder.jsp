<%-- 
    Document   : shippingOrder
    Created on : 23 Apr. 2024, 12:40:19 pm
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
        
        <% ArrayList<String> errorMsg = (ArrayList<String>) session.getAttribute("errorMessage"); 
           User user = (User) session.getAttribute("user");
        %>
        
        <%if(user == null || user.getUserType().contains("C")) {%>
        
            
            <div class="middle-container">   
                <div class="shipping-container">
                    <h2>Shipping Address</h2>
            
            
                    <% if(errorMsg != null) {
                        for(String errorLine : errorMsg){ %>

                        <p><%=errorLine %></p>

                    <%  } %>
                <% } %>


                    <form method="POST" action="./createShipment">

                        <div class="shipping-details-container">
                            <label for="streetAddress">Street Address: </label><br>
                            <input type="text" id = "streetAddress" name = "streetAddress">



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
                                            <select id="state" name="state">
                                                <option value="" selected> choose one</option>
                                                <option value="ACT">Australian Capital Territory</option>
                                                <option value="NSW">New South Wales</option>
                                                <option value="NT">Northern Territory</option>
                                                <option value="QLD">Queensland</option>
                                                <option value="SA">South Australia</option>
                                                <option value="TAS">Tasmania</option>
                                                <option value="VIC">Victoria</option>
                                                <option value="WA">Western Australia</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td><input type= "text" id = "postcode" name = "postcode"></td>
                                </tr>
                            </table>

                            <label for="shipmentMethod">Delivery Method: </label><br>
                            <select name="shipmentMethod">
                                <option value="" selected> Choose Your Delivery Type</option>
                                <option value="Standard">Standard</option>
                                <option value="Express">Express</option>
                            </select>


                        </div> 

                        <button class="submit-button" type="submit" name="orderSubmit" value="true"> Submit </button>

                    </form>
                </div>
            </div>
         
            <% } else { 
                response.sendRedirect("./unregisteredWarning.jsp");
            } %>
         
    </body>
</html>
