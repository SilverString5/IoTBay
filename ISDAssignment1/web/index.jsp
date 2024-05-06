<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%> 
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/layout.css">
        <title>IoTBay WebStore</title>
    </head>
    
    <body>
        <%if (session.getAttribute("user") != null){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
            
        <%}else {%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/login.jsp" > Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
        
<<<<<<< Updated upstream
=======
        <div align="left">
                        <form method="POST" action="http://localhost:8080/ISDAssignment1/deviceSearchServlet">
                            <input type="text" name="searchName" value="" placeholder="Search product by name"/>
                            <input type="text" name="searchType" value="" placeholder="Search product by type"/>
                            <button type="submit">Search</button>
                        </form>
        </div>
        
        <%if (user != null && user.getUserType().equals("S")){%> 
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
            

            
            <div align="center">
                <table border="1" cellpadding="5">
                    <caption><h2>Device Collection</h2></caption>
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
                            <fmt:formatNumber var="formattedUnitPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${device.productUnitPrice}" />
                            <td><c:out value="${formattedUnitPrice}" /></td>
                            <td><c:out value="${device.productDetails}"/></td>
                            <td><c:out value="${device.productInStock}"/></td>
                            <td>
                                <form method="POST" action="<%= request.getContextPath()%>/updateDeviceFormServlet" >
                                    <input type="hidden" name="productID" value="${device.productID}"/>
                                    <button type="submit">Update</button>
                                </form>
                                     
                                
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <form method="POST" action="<%= request.getContextPath()%>/deleteDeviceServlet" >
                                    <input type="hidden" name="productID" value="${device.productID}"/>
                                    <button type="submit">Delete</button>
                                </form>                    
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div> 
            
        <%} else {%> 
            
            <div align="center" >
                <table border="1" cellpadding="5">
                    <caption><h2>IoTBay Online Shop</h2></caption>
                    <tr>
                        
                        <th>Device</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Details</th>

                    </tr>
                    <c:forEach var="device" items="${listDevice}">
                        <tr>
                            
                            <td><c:out value="${device.productName}" /></td>
                            <td><c:out value="${device.productType}" /></td>
                            <fmt:formatNumber var="formattedUnitPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${device.productUnitPrice}" />
                            <td><c:out value="${formattedUnitPrice}" /></td>
                            <td><c:out value="${device.productDetails}"/></td>
                            <td>
                                <a href="/shoppingCartServlet?id=<c:out value='${device.productID}' />">Add to Cart</a>                   
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div> 
>>>>>>> Stashed changes
        <%}%>

    </body>
</html>
