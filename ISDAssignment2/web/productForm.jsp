<%-- 
    Document   : createProduct
    Created on : 02/05/2024, 3:30:18 PM
    Author     : jijianlan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<body>
    <center>
        <h1>Device Collection Management</h1>
        <h2>
            <a href="/create">Add New Device</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Device</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${device != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${device == null}">
            <form action="create" method="post">
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
                    <input type="text" name="productDetails" size="5"
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
