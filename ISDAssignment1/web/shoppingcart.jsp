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
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
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
                <li><a href="welcome.jsp" > You</a></li>
                <li><a href="login.jsp" > Login</a></li>
                <li><a href="register.jsp" > Register</a></li>               
                <li><a href="http://localhost:8080/ISDAssignment1/OrderHistoryServlet" > Orders </a></li>
            </ul>
        </div>
        <br>
        <br>
        <br> 
        <!--<br>-->
        <!--<br>-->
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
                <caption><h1>Shopping Cart</h1></caption>
                <tr>
                    <th></th>
                    <th>Device</th>
                    <th>Type</th>
                    <th>Device Details</th>
                    <th>Unit Price</th> 
                    <th>Quantity</th>
                    <th>SubTotal</th>
                    <th>Remove an Item</th>
                </tr>
                <% double totalAmount = 0.0; %>
                <% if(shoppingCart != null && !shoppingCart.isEmpty()) { %>                
                    <%for (Product product : cartList){ %>
                    <tr>
                        <td><img height="70" width="70" src="css/<%=product.getProductImg()%>"></td>
                        <td><%= product.getProductName()%></td>
                        <td><%= product.getProductType()%></td> 
                        <td><%= product.getProductDetails()%></td>
                        <td>$<%= product.getProductUnitPrice()%></td> 
                        <% double subtotal = product.getProductUnitPrice() * shoppingCart.get(product.getProductID());%>
                        <td>
                            <!-- When quantity is changed in the cart, update the "shoppingcart" session varaiable accordingly -->
                            <form method="POST" action="/ISDAssignment1/UpdateOrderServlet">
                                <input type="hidden" name="cartUpdate" value="true"/>
                                <input type="hidden" name="productID" value="<%= product.getProductID()%>" />
                                <input type="number" name="productQuantity" value="<%= shoppingCart.get(product.getProductID()) %>" onchange="this.form.submit()"/>
                            </form>

                        </td>
                        <td>$<%= subtotal%></td>
                        <% 
                            totalAmount += subtotal; 
    //                        session.setAttribute("totalAmount", totalAmount);
                        %>
                        <td>
                            <form method="GET" action="/ISDAssignment1/RemoveCartItemServlet">
                                <input type="hidden" name="function" value="RemoveAnItem"/>
                                <input type="hidden" name="productID" value="<%= product.getProductID() %>"/>
                                <input class="removeBtn" type="submit" value="Remove"/>
                            </form>
                        </td>
                    </tr>
                    <% } %>

                    <tr>
                        <td colspan="6"></td>
                        <td colspan="2" class="price-id">Total Price: $<%= totalAmount %></td>
                    </tr>
                <% }else { %>
                    <tr height="200px">
                        <td colspan="8">Shopping Cart is Empty</td>
                    </tr>
                <% } %>
                <tr>
                    <td colspan="3">
                        <form method="GET" action="/ISDAssignment1/RemoveCartItemServlet">
                            <input type="hidden" name="function" value="ClearCart"/>
                            <% if(shoppingCart != null && !shoppingCart.isEmpty()){ %>
                                <button class="availBtn" type="submit"> Clear the Cart </button>
                            <% }else{ %>
                                <button class="disBtn" disabled> Clear the Cart</button>
                            <% } %>
                        </form>
                        
                    </td>
                    <td colspan="2">
                        <form action="http://localhost:8080/ISDAssignment1/" method="POST">
                            <button class="availBtn" type="submit">Continue Shopping</button>
                        </form>
                    </td>
                    <td colspan="3">
                        <form method="GET" action="/ISDAssignment1/paymentForm.jsp">
                            <input type="hidden" name="totalAmount" value="<%= totalAmount %>"/>
                            <% if(shoppingCart != null && !shoppingCart.isEmpty()){ %>
                                <button class="availBtn" type="submit">Proceed the Order</button>
                            <% }else{ %>
                                <button class="disBtn" disabled> Proceed the Order</button>
                            <% } %>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        
    </body>
</html>