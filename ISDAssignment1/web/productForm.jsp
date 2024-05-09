<%-- 
    Document   : createProduct
    Created on : 02/05/2024, 3:30:18 PM
    Author     : jijianlan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>IoTBay WebStore</title>
</head>

    <!-- Servlet side input check results -->
    <% 
        String productNameErr = (String) request.getAttribute("productNameErr");
        String productTypeErr = (String) request.getAttribute("productTypeErr");
        String productUnitPriceErr = (String) request.getAttribute("productUnitPriceErr");
        String productDetailsErr = (String) request.getAttribute("productDetailsErr");
        String productInStockErr = (String) request.getAttribute("productInStockErr");
    %>

<body>
        
    <!-- Nav Bar Block -->    
    <div class="menu">
    <ul>
        <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
        <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" > Register</a></li>
        <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
    </ul>
    </div>
    <br>
    <br>

    <!-- Device management block for staff-->
    <div>
        <center>
            <h1>Device Collection Management</h1>
            <h2>
                <form action="http://localhost:8080/ISDAssignment1/addDeviceFormServlet" method="POST">
                    <button type="submit">Add new device</button>
                </form>

                &nbsp;&nbsp;&nbsp;
                <form action="http://localhost:8080/ISDAssignment1/ConnServlet" method="POST">
                    <button type="submit">List all devices</button>
                </form>

            </h2>
        </center>
    </div>
    
    <!-- Device update/create form for staff -->
    <div align="center">
        <c:if test="${device != null}">
            <!-- update -->
            <form action="http://localhost:8080/ISDAssignment1/updateExistingDeviceServlet" method="post">
        </c:if>
        <c:if test="${device == null}">
            <!-- create -->
            <form action="http://localhost:8080/ISDAssignment1/createNewProductServlet" method="post">
        </c:if>
            
            <!-- Form -->
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${device != null}">
                        Edit Device
                    </c:if>
                    <c:if test="${device == null}">
                        Add New Device
                    </c:if>
                </h2>
            </caption>
            
            <!-- Error Displaying -->
            <caption>
                <% if(productNameErr != null) { %>
                <%=productNameErr%></h1
                <% } %>
                <% if(productTypeErr != null) { %>
                <%=productTypeErr%>
                <% } %>
                <% if(productUnitPriceErr != null) { %>
                <%=productUnitPriceErr%>
                <% } %>
                <% if(productDetailsErr != null) { %>
                <%=productDetailsErr%>
                <% } %>
                <% if(productInStockErr != null) { %>
                <%=productInStockErr%>
                <% } %>
            <caption/>
            
            <!-- Form inputs -->
            <c:if test="${device != null}">
                <input type="hidden" name="productID" value="<c:out value='${device.productID}' />" />
            </c:if>  
                
            <tr>
                <th>Device Name: </th>
                <td>
                    <input type="text" name="productName" size="45"
                           value="<c:out value='${device.productName}' />"/>
                </td>
            </tr>
            
            <tr>
                <th>Device Type </th>
                <td>
                    <input type="text" name="productType" size="45"
                            value="<c:out value='${device.productType}' />"/>
                </td>
            </tr>

            <tr>
                <th>Price: </th>
                <td>
                    <input type="text" name="productUnitPrice" size="5"
                            value="<c:out value='${device.productUnitPrice}' />"/>
                </td>
            </tr>
   
            <tr>
                <th>Details: </th>
                <td>
                    <input type="text" name="productDetails" size="100"
                            value="<c:out value='${device.productDetails}' />"/>
                </td>
            </tr>

            <tr>
                <th>Stock: </th>
                <td>
                    <input type="text" name="productInStock" size="5"
                            value="<c:out value='${device.productInStock}' />"/>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>
