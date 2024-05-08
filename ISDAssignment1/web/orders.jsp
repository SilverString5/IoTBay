<%-- 
    Document   : orders
    Created on : May 7, 2024, 3:32:32 PM
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
        <title>Orders</title>
    </head>
    <%
        User user = (User)session.getAttribute("user");
        String submitted = request.getParameter("submitted");
        ArrayList<Order> orderList = (ArrayList<Order>)session.getAttribute("orderList");
        Order specificOrder = null;
        if(request.getAttribute("searchedOrder") != null){
             specificOrder = (Order)request.getAttribute("searchedOrder");
        }
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
        <%  String errorMessage = "";
            if(request.getAttribute("errorMessage") != null){
                errorMessage = (String) request.getAttribute("errorMessage");
        %>
                <p><%= errorMessage%></p>
        <%}%>
        
        <div>
            <form method="GET" action="/ISDAssignment1/SearchOrderServlet">
                <label for="orderID">Order Number:</label>
                <input name="orderID" type="text" placeholder="Fill in order number">
                
                <label for="orderDate">Order Date:</label>
                <input name="orderDate" type="text" placeholder="yyyy-mm-dd">
                <input type="hidden" id="submitted" name="submitted" value="yes">
                <input type="submit" value="Search">
            </form>
        </div>
        
        <div>
            <table width="100%">
                <tr>
                    <th>Order Number</th>
                    <th>Order Date</th>
                    <th>Order Status</th>
                    <th>Total Amount</th>
                    <th>Shipment Number</th>
                    <th>View Details</th>
                    <th>Update Order</th>
                    <th>Cancel Order</th>
                </tr>
        <% if(submitted == null && user != null){ %>        
                <%
                    for(Order order : orderList) {
                %>
                <tr>
                    <td><%= order.getOrderID() %></td>
                    <td><%= order.getOrderDate() %></td>
                    <td><%= order.getOrderStatus() %></td> 
                    <td><%= order.getTotalAmount() %></td> 
                    <td><%= order.getShipmentID() %></td>
                    <td>
                        <form method="GET" action="/ISDAssignment1/ViewOrderServlet">
                            <input type="hidden" name="function" value="View"/>
                            <input type="hidden" name="orderID" value="<%= order.getOrderID() %>"/>
                            <input type="submit" value="View Details"/>
                        </form>
                    </td>
                    
                    <td>
                        <form method="GET" action="/ISDAssignment1/ViewOrderServlet">
                            <input type="hidden" name="function" value="Update"/>
                            <input type="hidden" name="orderID" value="<%= order.getOrderID() %>"/>
                            <input type="submit" value="Update"/>
                        </form>
                    </td>
                    
                    <td>
                        <form method="GET" action="/ISDAssignment1/ViewOrderServlet">
                            <input type="hidden" name="function" value="Cancel"/>
                            <input type="hidden" name="orderID" value="<%= order.getOrderID() %>"/>
                            <input type="submit" value="Cancel"/>
                        </form>
                    </td>
                </tr>
                <% } %>
        <% }else if(submitted != null && submitted.equals("yes")){ %>
                
                <% if(specificOrder != null){ %>
                <tr>
                    <td><%= specificOrder.getOrderID() %></td>
                    <td><%= specificOrder.getOrderDate() %></td>
                    <td><%= specificOrder.getOrderStatus() %></td> 
                    <td><%= specificOrder.getTotalAmount() %></td> 
                    <td><%= specificOrder.getShipmentID() %></td>
                    <td>
                        <form method="GET" action="/ISDAssignment1/ViewOrderServlet">
                            <input type="hidden" name="function" value="View"/>
                            <input type="hidden" name="orderID" value="<%= specificOrder.getOrderID() %>"/>
                            <input type="submit" value="View Details"/>
                        </form>
                    </td>
                    
                    <td>
                        <form method="GET" action="/ISDAssignment1/ViewOrderServlet">
                            <input type="hidden" name="function" value="Update"/>
                            <input type="hidden" name="orderID" value="<%= specificOrder.getOrderID() %>"/>
                            <input type="submit" value="Update"/>
                        </form>
                    </td>
                    
                    <td>
                        <form method="GET" action="/ISDAssignment1/ViewOrderServlet">
                            <input type="hidden" name="function" value="Cancel"/>
                            <input type="hidden" name="orderID" value="<%= specificOrder.getOrderID() %>"/>
                            <input type="submit" value="Cancel"/>
                        </form>
                    </td>
                    
                </tr>   
                <% } %>
            <% } %>
                
            </table>
        </div>
   
    </body>
</html>
