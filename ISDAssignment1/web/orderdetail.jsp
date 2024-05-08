<%-- 
    Document   : orderdetail
    Created on : May 7, 2024, 7:22:41 PM
    Author     : pyaephyozaw
--%>

<%@page import="java.util.HashMap"%>
<%@page import="uts.isd.model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/shoppingcart.css">
        <title>Order Detail Page</title>
    </head>
    <%
        ArrayList<Product> productList = (ArrayList<Product>)session.getAttribute("productList");
        HashMap<Integer, Integer> quantityMap = (HashMap<Integer, Integer>)session.getAttribute("quantityMap");
    %>
    <body>
        <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
                <!--<li><a href="http://localhost:8080/ISDAssignment1/" > Orders </a></li>-->
            </ul>                            
        </div>
        <div>
            <table width="100%" >
                <tr>
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
                    <td><%= product.getProductName()%></td>
                    <td><%= product.getProductType()%></td> 
                    <td><%= product.getProductDetails()%></td>
                    <td><%= product.getProductUnitPrice()%></td> 
                    <%  double subtotal = product.getProductUnitPrice();
                        int quantity = quantityMap.get(product.getProductID());
                    %>
                    
                    <td><%= quantity%></td>
                    <td><%= subtotal*quantity%></td>
                    <% 
                        totalAmount += subtotal; 
                    %>
                </tr>
                <% } %>
                <% // session.setAttribute("totalAmount", totalAmount); %>
                <tr>
                    <td colspan="5"></td>
                    <td class="price-id">Total Price: $<%= totalAmount %></td>
                </tr>
            </table>
        </div>
    </body>
</html>
