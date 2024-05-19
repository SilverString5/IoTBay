<%-- 
    Document   : paymentsList
    Created on : 13 May 2024, 6:30:57 pm
    Author     : sienn
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
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Payment Record List</title>
    </head>

    <% User user = (User)session.getAttribute("user"); %>
    
    
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
        
        
        <center>
            <h1>Payment Management</h1>
            <h2>
                <a href="/paymentForm">Go Back to Payment</a>
            </h2>
        </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Your Payments</h2></caption>
            <tr>
                <th>ID</th>
                <th>Payment Method</th>
                <th>Expiry Date</th>
                <th>Card Number</th>
            </tr>
            <c:forEach var="payment" items="${payments}">
                <tr>
                    <td><c:out value="${payment.paymentID}"/></td>
                    <td><c:out value="${payment.paymentMethod}"/></td>
                    <td><c:out value="${payment.expiryDate}"/></td>
                    <td><c:out value="${payment.paymentCardNumber}"/></td>
                    
                    <td>
                        <a href="/paymentedit?id=<c:out value='${payment.paymentID}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/paymentdelete?id=<c:out value='${payment.paymentID}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
