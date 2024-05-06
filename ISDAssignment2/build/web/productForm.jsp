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
    <div>
        <center>
            <h1>Device Collection Management</h1>
            <h2>
                <form action="http://localhost:8080/ISDAssignment2/addDeviceFormServlet" method="POST">
                    <button type="submit">Add new device</button>
                </form>

                &nbsp;&nbsp;&nbsp;
                <form action="http://localhost:8080/ISDAssignment2/ConnServlet" method="POST">
                    <button type="submit">List all devices</button>
                </form>

            </h2>
        </center>
    </div>
    <div align="center">
        
        <c:if test="${device != null}">
            <form action="http://localhost:8080/ISDAssignment2/updateExistingDeviceServlet" method="post">
        </c:if>
        <c:if test="${device == null}">
            <form action="http://localhost:8080/ISDAssignment2/createNewProductServlet" method="post">
        </c:if>
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
                <c:if test="${device != null}">
                    <input type="hidden" name="productID" value="<c:out value='${device.productID}' />" />
                </c:if>           
            <tr>
                <th>Device Name: </th>
                <td>
                    <input type="text" name="productName" size="45"
                            value="<c:out value='${device.productName}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Device Type </th>
                <td>
                    <input type="text" name="productType" size="45"
                            value="<c:out value='${device.productType}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                    <input type="text" name="productUnitPrice" size="5"
                            value="<c:out value='${device.productUnitPrice}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Details: </th>
                <td>
                    <input type="text" name="productDetails" size="100"
                            value="<c:out value='${device.productDetails}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Stock: </th>
                <td>
                    <input type="text" name="productInStock" size="5"
                            value="<c:out value='${device.productInStock}' />"
                    />
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
