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
<%@ page import="uts.isd.model.dao.PaymentDAO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Payment Menu</title>
    </head>
    
    <!-- Getting the attribute for the user.-->
    <% User user = (User)session.getAttribute("user"); 
    %>
    
    
    <body>
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
      
        
        <div>
            <!-- Selection for payment method -->
            <form class="myForm" action="./createNewPaymentServlet" method="post" id="paymentForm">
                <h1 class="myHeader">Add your Payment Details Here.</h1>
                <label for="paymentMethod">Payment Method:</label><br>
                <select name="paymentMethod" id="PaymentMethod">
                    <option value="Visa" ${selectedPaymentMethod == 'Visa' ? 'selected' : ''}>Visa</option>
                    <option value="Mastercard" ${selectedPaymentMethod == 'Mastercard' ? 'selected' : ''}>Mastercard</option>
                    <option value="Paypal" ${selectedPaymentMethod == 'Paypal' ? 'selected' : ''}>Paypal</option>
                    <option value="Google Pay" ${selectedPaymentMethod == 'Google Pay' ? 'selected' : ''}>Google Pay</option>
                </select><br><br>
            <!-- Expiry date placeholder pattern  (0[1-9]|1[0-2])\/\d{2} to successfully make a date-->
                <label for="ExpiryDate">Expiry Date</label><br>
                <input type="date" name="ExpiryDate" id="expiryDate" placeholder="MM/YY" pattern="(0[1-9]|1[0-2])\/\d{2}" value="${not empty selectedExpiryDate ? selectedExpiryDate : ''}" required><br>

            <!-- payment CVC for user to submit-->
                <label for="paymentCVC">CVC</label><br>
                <input type="text" name="paymentCVC" id="paymentCVC" placeholder="CVC" pattern="\d{3}" value="${not empty selectedPaymentCVC ? selectedPaymentCVC : ''}" required/><br>

           <!-- Card Number for user to submit-->
                <label for="paymentCardNumber">Card Number:</label><br>
                <input type="number" name="paymentCardNumber" id="paymentCardnumber" placeholder="Card Number" pattern="\d{9}" value="${not empty selectedPaymentCardNumber ? selectedPaymentCardNumber : ''}" required><br>   

            
                <input type="hidden" name="submitted" id="submitted" value="true" /><br>
                <button type="submit">Submit</button>
            </form>
            <!-- If the user is anonymous, then the menu to select payments previously made by the account will not be available.-->
            <% if (user != null) { %> 
                <h3><a href="http://localhost:8080/ISDAssignment1/paymentListServlet"> Saved Payments </a></h3>
            <% } %>
                
        </div>
<!-- Code to allow the error messages to appear if one of the validation tests do not pass.-->
        <div style="color:red;">
            <%
                String errorMsgs1 = (String) session.getAttribute("errorMsgs1");
                if (errorMsgs1 != null && !errorMsgs1.isEmpty()) {
                    out.println(errorMsgs1);
                    
                   %>
                   <br>
                   <%
                   
                } 
            %>
        </div>
        
        
      
    </body>
</html>