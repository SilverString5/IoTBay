<%-- 
    Document   : createProduct
    Created on : 02/05/2024, 3:30:18 PM
    Author     : jialan Guo
--%>

<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Random"%> 
<%@page import="uts.isd.model.*"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page import="uts.isd.controller.*"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>IoTBay WebStore</title>
    </head>
    
    <% User user = (User)session.getAttribute("user"); 

        ArrayList<Product> deviceList = (ArrayList<Product>) session.getAttribute("listDevice");

        if(session.getAttribute("shoppingCart") != null){
            HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>)session.getAttribute("shoppingCart");
        }
    %>
    <body>
        <!-- Nav Bar Block -->
        <%if(user != null && user.getUserType().equals("S")){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" >You</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/viewAccessLogs.jsp" >Your Access Logs</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/manageRegistration.jsp" >Manage Account Details</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp" >Logout</a></li>
            </ul>
                                
            </div>
            <br>
            <br>
        <%}else if(user != null && user.getUserType().equals("C")){%>
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
        <% } %>
        
        
        <!-- Device Searching Block -->
        <div>    
            <form  method="POST" action="http://localhost:8080/ISDAssignment1/deviceSearchServlet">
                <input type="text" name="searchName" value="" placeholder="Search product by name"/>
                <input type="text" name="searchType" value="" placeholder="Search product by type"/>
                <button type="submit">Search</button>
            </form>
            <br/>
            <br/>
        </div>
        
        
        <!-- Device management block for staff -->
        <%if (user != null && user.getUserType().equals("S")){%> 
            <div>
                <center>
                    <h1>Device Collection Management</h1>
                    <h2>
                        <form method="POST" action="http://localhost:8080/ISDAssignment1/addDeviceFormServlet">
                            <button type="submit">Add new device</button>
                        </form>
                        &nbsp;&nbsp;&nbsp;
                        <form method="POST" action="http://localhost:8080/ISDAssignment1/ConnServlet">
                            <button type="submit">List all devices</button>
                        </form>

                    </h2>
                </center>
            </div>
            

            <!-- Device view and list for staff -->
            <div align="center">
                <table>
                    <caption><h2>Device Collection</h2></caption>
                    <tr>
                        
                        <th></th>
                        <th>ID</th>
                        <th>Device</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Details</th>
                        <th>Stocks</th>
                        <th></th>
                        
                    </tr>
                    <c:forEach var="device" items="${listDevice}">
                        <tr>
                            <td><img src="css/${device.productImg}"></td>
                            <td><c:out value="${device.productID}" /></td>
                            <td><c:out value="${device.productName}" /></td>
                            <td><c:out value="${device.productType}" /></td>
                            <fmt:formatNumber var="formattedUnitPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${device.productUnitPrice}" />
                            <td><c:out value="${formattedUnitPrice}" /></td>
                            <td><c:out value="${device.productDetails}"/></td>
                            <td><c:out value="${device.productInStock}"/></td>
                            <td>
                                <form method="POST" action="http://localhost:8080/ISDAssignment1/updateDeviceFormServlet" >
                                    <input type="hidden" name="productID" value="${device.productID}"/>
                                    <button type="submit">Update</button>
                                </form>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <form method="POST" action="http://localhost:8080/ISDAssignment1/deleteDeviceServlet" >
                                    <input type="hidden" name="productID" value="${device.productID}"/>
                                    <button type="submit">Delete</button>
                                </form>                    
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div> 
            
        <%} else {%> 
            
        
            <!-- Device view and list for Customer(non-registered and registered)-->
            <div align="center" >
                <table>
                    <caption><h2>IoTBay Online Shop</h2></caption>
                    <tr>
                        <th></th>
                        <th>Device</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Details</th>
                        <th></th>

                    </tr>
                   <%for (Product device: deviceList) {%>
                    <tr>
                        <td><img src="css/<%=device.getProductImg()%>"></td>
                        <td><%= device.getProductName() %></td>
                        <td><%= device.getProductType() %></td>
                        <td>$<%= device.getProductUnitPrice() %></td>
                        <td><%= device.getProductDetails() %></td>
                        <td>
                            <form method="GET" action="/ISDAssignment1/ShoppingCartServlet">
                                <input type="hidden" name="proID" value="<%= device.getProductID()%>"/>
                                <% if(device.getProductInStock() == 0){ %>
                                    <input id="disBtn" type="submit" value="Add to cart" disabled/>
                                <% }else{ %>
                                    <input id="addCartBtn" type="submit" value="Add to cart"/>
                                <% } %>
                            </form>
                        </td>
                    </tr>
                    <% } %> 
                </table>
            </div> 
        <%}%>
    </body>
</html>
