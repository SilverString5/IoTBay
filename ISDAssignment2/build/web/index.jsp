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
    
    <% User user = (User)session.getAttribute("user"); 
    %>
    <body>
        
        <%if(user != null){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment2/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
        <%}else{%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment2/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/login.jsp" > Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment2/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
        <%} %>
        
        <%if (user != null && user.getType().equals("Staff")){%> 
            <center>
                <h1>Device Collection Management</h1>
                <h2>
                    <a href="/create">Add New Device</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/list">List All Devices</a>

                </h2>
            </center>
            <div align="center">
                <table border="1" cellpadding="5">
                    <caption><h2>List of Books</h2></caption>
                    <tr>
                        <th>ID</th>
                        <th>Device</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Details</th>
                        <th>Stocks</th>
                    </tr>
                    <c:forEach var="device" items="${listDevice}">
                        <tr>
                            <td><c:out value="${device.productID}" /></td>
                            <td><c:out value="${device.productName}" /></td>
                            <td><c:out value="${device.productType}" /></td>
                            <td><c:out value="${device.productUnitPrice}" /></td>
                            <td><c:out value="${device.productDetails}"/></td>
                            <td><c:out value="${device.productInStock}"/></td>
                            <td>
                                <a href="/edit?id=<c:out value='${device.productID}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="/delete?id=<c:out value='${device.productID}' />">Delete</a>                     
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div> 
            
        <%} else {%> 
            
            <center>
                <h1>Device Collection Management</h1>
                <h2>
                    <a href="/create">Add New Device</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/list">List All Devices</a>

                </h2>
            </center>
            <div align="center" >
                <table border="1" cellpadding="5">
                    <caption><h2>List of Books</h2></caption>
                    <tr>
                        <th>ID</th>
                        <th>Device</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Details</th>
                        <th>Stocks</th>
                    </tr>
                    <c:forEach var="device" items="${listDevice}">
                        <tr>
                            <td><c:out value="${device.productID}" /></td>
                            <td><c:out value="${device.productName}" /></td>
                            <td><c:out value="${device.productType}" /></td>
                            <td><c:out value="${device.productUnitPrice}" /></td>
                            <td><c:out value="${device.productDetails}"/></td>
                            <td>
                                <a href="/shoppingCartServlet?id=<c:out value='${device.productID}' />">Add to Cart</a>                   
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div> 
        <%}%>
        
        

    </body>
</html>
