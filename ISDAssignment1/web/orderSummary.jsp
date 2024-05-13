<%-- 
    Document   : orderSummary
    Created on : May 13, 2024, 5:56:04 PM
    Author     : pyaephyozaw
--%>

<%@page import="uts.isd.model.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/shoppingcart.css">
        <title>Order Summary Page</title>
    </head>
    <%
        ArrayList<Product> cartList = (ArrayList<Product>)session.getAttribute("cartList");
        HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>)session.getAttribute("shoppingCart");
        String shipmentAddress = (String)request.getAttribute("shipmentAddress");
        String shipmentMethod = (String)request.getAttribute("shipmentMethod");
        String paymentMethod = (String)session.getAttribute("paymentMethod");
        String expiryDate = (String)session.getAttribute("expiryDate");
        int cardNumber = (Integer)session.getAttribute("cardNumber");
        int cvc = (Integer)session.getAttribute("cvc");        
    %>
    <body>
        <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/OrderHistoryServlet" > Orders </a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/DisplayCartServlet"> Shopping Cart</a></li>
            </ul>                            
        </div>
        <br> 
        <br>
        <br>
        
        <div>
            <table width="100%">
                <caption><h1>Order Summary</h1></caption>
                <tr>
                    <th></th>
                    <th>Device</th>
                    <th>Type</th>
                    <th>Device Details</th>
                    <th>Unit Price</th> 
                    <th>Quantity</th>
                    <th>SubTotal</th>
                </tr>
                <% double totalAmount = 0.0; %>
                             
                    <%for (Product product : cartList){ %>
                    <tr>
                        <td><img height="70" width="70" src="css/<%=product.getProductImg()%>"></td>
                        <td><%= product.getProductName()%></td>
                        <td><%= product.getProductType()%></td> 
                        <td><%= product.getProductDetails()%></td>
                        <td>$<%= product.getProductUnitPrice()%></td> 
                        <% double subtotal = product.getProductUnitPrice() * shoppingCart.get(product.getProductID());%>
                        <td><%= shoppingCart.get(product.getProductID())%></td>
                        <td>$<%= subtotal%></td>
                        <% 
                            totalAmount += subtotal; 
    //                        session.setAttribute("totalAmount", totalAmount);
                        %>
                    </tr>
                    <% } %>
                    
                    <tr>
                        <td colspan="6"></td>
                        <td colspan="2" class="price-id">Total Price: $<%= totalAmount %></td>
                    </tr>
            </table>
        </div>
        <div class="bottom-container">
            <h2>Shipment Information</h2>

            <div class="flexbox">
                <div class="first-column">
                    <h3>Shipping Address:</h3> <p><%=shipmentAddress%></p> 
                </div>

                <div class="second-column">
                    <h3>Delivery Method:</h3> <p><%=shipmentMethod%></p>
                </div>
            </div>
        </div>
        <hr>        
        <div class="bottom-container">
            <h2>Payment Information</h2>

            <div class="flexbox">
                <div class="first-column">
                    <h3>Payment Method:</h3> <p><%=paymentMethod%></p> 
                </div>

                <div class="second-column">
                    <h3>Card Number: </h3> <p><%=cardNumber%></p>
                </div>
                
            </div>
        </div>
                
                <div id="updateForm">
                    <form method="GET" action="./SubmitOrderServlet">
                        <input type="hidden" name="totalAmount" value="<%= totalAmount %>"/>
                        <button class="availBtn" type="submit" >Submit the Order</button>
                    </form>
                </div>
                    
                    
    </body>
</html>
