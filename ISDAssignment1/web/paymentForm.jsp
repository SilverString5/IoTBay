<%-- 
    Document   : paymentForm
    Created on : 6 May 2024, 10:15:31 pm
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
        <title>Payment Menu</title>
    </head>
    
    
    <% User user = (User)session.getAttribute("user"); 
        String paymentID = (String) request.getAttribute("paymentID");
        String paymentMethod = (String) request.getAttribute("paymentMethod");
        String expiryDate = (String) request.getAttribute("expiryDate");
        String paymentCVC = (String) request.getAttribute("paymentCVC");
        String paymentCardNumber = (String) request.getAttribute("paymentCardNumber");
        String userID = (String) request.getAttribute("userID");
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
            <form class="myForm" action="<%= request.getContextPath()%>/RegisterServlet" method="post">
                <h1 class="myHeader">Add your Payment Details Here.</h1>
                <label for="PaymentMethod">Payment Method:</label><br>
                <select id="PaymentMethod">
                    <option value="Visa">Visa</option>
                    <option value="Mastercard">Mastercard</option>
                    <option value="Paypal">Paypal</option>
                    <option value="GooglePay">Google Pay</option>
                </select>
            
                <label for="ExpiryDate">Expiry Date</label><br>
                <input type="date" name="ExpiryDate" id="ExpiryDate" required><br>

            
                <label for="CVC">CVC</label><br>
                <input type="text" name="CVC" id="CVC" placeholder="000" required/><br>

           
                <label for="paymentcardnumber">Card Number:</label><br>
                <input type="number" name="paymentcardnumber" id="paymentcardnumber" placeholder="000000000" required><br>           
            
                <input type="hidden" name="submitted" id="submitted" value="true" /><br>
                <button type="submit">Confirm</button>

            </form>
        </div>
        
                <div>
                    
        </div>
    </body>
</html>
