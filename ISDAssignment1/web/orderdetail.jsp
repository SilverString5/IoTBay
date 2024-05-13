<%-- 
    Document   : orderdetail
    Created on : May 7, 2024, 7:22:41 PM
    Author     : pyaephyozaw
--%>

<%@page import="java.util.HashMap"%>
<%@page import="uts.isd.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/shoppingcart.css">
        <title>Order Detail Page</title>
    </head>
    <%
        User user = (User)session.getAttribute("user");
        ArrayList<Product> productList = (ArrayList<Product>)session.getAttribute("productList");
        HashMap<Integer, Integer> quantityMap = (HashMap<Integer, Integer>)session.getAttribute("quantityMap");
        int orderID = (Integer)session.getAttribute("orderID");
    %>
    <body>
        <% if(user != null){%>
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
            <br>
            <br>
            <br>
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
            <br>
            <br>
            <br>
        <% } %>
        <div>
            <table width="100%" >
                <caption><h1>Order Details for Order Number: <%= orderID %></h1></caption>
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
                <%for (Product product : productList){ %>
                <tr>
                    <td><img height="70" width="70" src="css/<%=product.getProductImg()%>"></td>
                    <td><%= product.getProductName()%></td>
                    <td><%= product.getProductType()%></td> 
                    <td><%= product.getProductDetails()%></td>
                    <td>$<%= product.getProductUnitPrice()%></td> 
                    <%  double subtotal = product.getProductUnitPrice();
                        int quantity = quantityMap.get(product.getProductID());
                    %>
                    
                    <td><%= quantity%></td>
                    <% subtotal *= quantity; %>
                    <td>$<%= subtotal%></td>
                    <% 
                        totalAmount += subtotal; 
                    %>
                </tr>
                <% } %>
                
                <tr>
                    <td colspan="5"></td>
                    <td colspan="2" class="price-id">Total Price: $<%= totalAmount %></td>
                </tr>
            </table>
        </div>
    </body>
</html>