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
        String quantityError = (String) request.getAttribute("quantityError");
        String insufficientStockErr = (String) request.getAttribute("insufficientStockError");
//        int orderID = Integer.parseInt(request.getParameter("orderID"));
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
        <% if(quantityError != null) { %>
            <p style="color:red"><%= quantityError %></p>
        <% } %>
        <% if( insufficientStockErr != null){ %>
            <p style="color:red"><%= insufficientStockErr %></p>
        <% } %>
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
                    
                    <td>
                        <form method="POST" action="/ISDAssignment1/UpdateOrderServlet">
                            
                            <input type="hidden" name="unitPrice" value="<%= product.getProductUnitPrice()%>"/>
                            
                            <input type="hidden" name="productID" value="<%= product.getProductID() %>"/>
                            <input name="productQuantity" type="number" value="<%= quantity %>" onchange="this.form.submit()"/>
                        </form>
                        
                    </td>
                    <% subtotal *= quantity;%>
                    <td><%= subtotal%></td>
                    <% 
                        totalAmount += subtotal; 
                    %>
                </tr>
                <% } %>
                <% // request.setAttribute("totalAmount", totalAmount); %>
                <tr>
                    <td colspan="5"></td>
                    <td class="price-id">Total Price: $<%= totalAmount %></td>
                </tr>
            </table>
        </div>
        <div>
            <form method="POST" action="/ISDAssignment1/UpdateOrderServlet">
                <input type="hidden" name="saved" value="yes" />
                <!--<input type="hidden" name="orderID" value=" // orderID " />-->
                <input type="hidden" name="totalAmount" value="<%= totalAmount%>" />
                <input type="submit" value="Save"/>
            </form>
        </div>
    </body>
</html>
