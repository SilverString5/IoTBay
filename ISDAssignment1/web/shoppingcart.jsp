<%-- 
    Document   : shoppingcart
    Created on : May 6, 2024, 4:46:46 PM
    Author     : pyaephyozaw
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.Product"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/shoppingcart.css">
        <title>Shopping Cart Page</title>
    </head>
    <%
        HashMap<Integer, Integer> shoppingCart = new HashMap();
        if(session.getAttribute("shoppingCart") != null)
            shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart");
        ArrayList<Product> cartList = (ArrayList<Product>)session.getAttribute("cartList");
        String quantityError = (String) request.getAttribute("quantityError");
        String insufficientStockErr = (String) request.getAttribute("insufficientStockError");
    %>
    
    <body>
        <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="login.jsp" > Login</a></li>
                <li><a href="register.jsp" > Register</a></li>
                <li><a href="welcome.jsp" > You</a></li>
            </ul>
        </div>
        <div>
            <% if(quantityError != null) { %>
            <p style="color:red"><%= quantityError %></p>
            <% } %>
            <% if( insufficientStockErr != null){ %>
            <p style="color:red"><%= insufficientStockErr %></p>
            <% } %>
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
                <%for (Product product : cartList){ %>
                <tr>
                    <td><%= product.getProductName()%></td>
                    <td><%= product.getProductType()%></td> 
                    <td><%= product.getProductDetails()%></td>
                    <td><%= product.getProductUnitPrice()%></td> 
                    <% double subtotal = product.getProductUnitPrice() * shoppingCart.get(product.getProductID());%>
                    <td>
                        <form method="POST" action="/ISDAssignment1/UpdateOrderServlet">
                            <input type="hidden" name="cartUpdate" value="true"/>
                            <input type="hidden" name="productID" value="<%= product.getProductID()%>" />
                            <input type="number" name="productQuantity" value="<%= shoppingCart.get(product.getProductID()) %>" onchange="this.form.submit()"/>
                        </form>
                        
                    </td>
                    <td><%= subtotal%></td>
                    <% 
                        totalAmount += subtotal; 
//                        session.setAttribute("totalAmount", totalAmount);
                    %>
                </tr>
                <% } %>
                
                <tr>
                    <td colspan="5"></td>
                    <td class="price-id">Total Price: $<%= totalAmount %></td>
                </tr>
            </table>
        </div>
        <div>
<!--            <form method="GET" action="/ISDAssignment1/SaveOrderServlet">
                <button type="submit">Save the Order</button>
            </form>-->
            <form method="GET" action="/ISDAssignment1/SubmitOrderServlet">
                <input type="hidden" name="totalAmount" value="<%= totalAmount %>"/>
                <button type="submit">Submit the Order</button>
            </form>
            <form action="http://localhost:8080/ISDAssignment1/ConnServlet" method="POST">
                <button type="submit">Continue Shopping</button>
            </form>
                
        </div>
    </body>
</html>
