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
        <link rel="stylesheet" href="./css/test.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Order Detail</title>
    </head>
    <body>
        
        
        <%Shipment shipment = (Shipment) session.getAttribute("shipment"); 
          User user = (User) session.getAttribute("user");
        %>
        
        
         <% if(user != null && user.getUserType().equals("C")){%>
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
        
        <%if(user == null || user.getUserType().contains("C")) {%>
            <div class="middle-container">        
        
        
                <div class="shipment-status-container">
                    <h1>Track Your Package</h1>
                    <h3>Shipment Date: <%=shipment.getShipmentEstTime()%> </h3>
                </div>

                <!-- Depending on the shipment status, it portrays an image that reflect this information -->
                <% if(shipment.getShipmentStatus().equals("Pending")){ %>
                        <img class="tracking-image" src="./css/Pending.png">
                <% } else if(shipment.getShipmentStatus().equals("Dispatched")){ %>
                        <img class="tracking-image" src="./css/Dispatched.png">
                <% } else if(shipment.getShipmentStatus().equals("On Route")){ %>
                        <img class="tracking-image" src="./css/OnRoute.png">
                <% } else if(shipment.getShipmentStatus().equals("Delivered")){ %>
                        <img class="tracking-image" src="./css/Delivered.png">
                <% } else { %>
                        <p> Shipment was Cancelled</p>
                <% } %>


                <div class="bottom-container">
                    <h2>Shipment Information</h2>

                    <div class="flexbox">
                        <div class="first-column">
                            <h3>Shipping Address:</h3> <p><%=shipment.getShipmentAddress()%></p> 
                        </div>

                        <div class="second-column">
                            <h3>Delivery Method:</h3> <p><%=shipment.getShipmentMethod()%></p>
                        </div>
                    </div>
                </div>

                <div class="flexbox">
                    <%if(user == null) { %>
                        <div class="first-column">
                            <button><a href="./index.jsp">Back to Home Page</a></button>
                        </div>
                        <div class="second-column align-right"/>         
                        
                    <% } else { %>
                        <div class="first-column">
                            <button><a href="./shipmentHistory.jsp">Back To Shipments</a></button>
                        </div>
                    <% } %>
                    
                    <% if(user != null) {%>
                        <div class="second-column align-right">
                            <%if (shipment.getShipmentStatus().equals("Pending")) {%>
                                <button type="submit"><a href="./updateShipmentForm.jsp">Update</a></button>
                            <% } %>
                        </div>
                    <% } %>
                </div>

            </div>
        <% } else { 
                System.out.println("pass");
                response.sendRedirect("./unregisteredWarning.jsp");
        }%>
            
        
    </body>
</html>
