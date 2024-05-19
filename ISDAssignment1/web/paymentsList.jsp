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
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Payment List</title>
    </head>

    <!-- To get the user attribute-->
    <% User user = (User)session.getAttribute("user");
     //to get the paymentList from paymentListServlet.
       ArrayList<Payment> paymentList = (ArrayList<Payment>) session.getAttribute("paymentList");
       //if the user leave a slot empty in the search, the paymentFilter Error will present itself.
       String paymentFilterError = (String) session.getAttribute("paymentFilterError");
       
    %>
    
    
    <body>
        <% if(user != null){%>
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
            <br>
        <% } %>
        <div>
        
        
        <center>
            <h1>Payment Management</h1>
            <h2>
                <a href="./paymentForm.jsp">Go Back to Payment</a>
            </h2>
        </center>
           
            
            <form method="POST" action="<%= request.getContextPath() %>/paymentListServlet">
                
                    <div class="search-bar">
                        <!-- search bar for users to fill out a search from the Payment ID and the Card Number.-->
                        
                        <label for="paymentID">Payment ID: </label>
                        <input type="number" id = "paymentID" name = "paymentID">
                        
                        <label for="cardnumber">Card Number: </label>
                        <input type="number" id = "cardnumber" name = "cardnumber">

                        <input type="hidden" name="searchButton" value="true">
                        <button type="submit">Search</button>

                        <input type="hidden" name="resetButton" value="true">
                        <button><a href="./paymentsList.jsp">Reset</a></button>
                    </div>
                
                </form>
            <!-- If a part of the search bar is empty, it will notify the user of the mistake.-->
                <%if(paymentFilterError != null) {%> 
                    <p class="errorMessage"><%=paymentFilterError%></p> 
                <% } %>
            
            
    <div align="center">
        <% if (paymentList != null) { %>
            <table border="1" cellpadding="5">
                <caption><h2>List of Your Payments</h2></caption>
                <tr>
                    <th>Payment ID</th>
                    <th>Payment Method</th>
                    <th>Expiry Date</th>
                    <th>CVC</th>
                    <th>Card Number</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
                
                <!-- Calling the paymentList and iterating through it to print out all the payments in the paymentList.-->
            
                <%for (Payment payment : paymentList) { %>
                    <tr>
                        <td><%= payment.getPaymentID()%></td>
                        <td><%= payment.getPaymentMethod()%></td>
                        <td> <%=payment.getExpiryDate()%></td>
                        <td><%=payment.getPaymentCVC()%></td>
                        <td><%=payment.getPaymentCardNumber()%></td>

                        <td>
                         <!-- Button to delete selected payment.-->
                            <form method="POST" action="<%= request.getContextPath() %>/deletePaymentServlet">
                                <input type="hidden" name="function" value="DeletePayment"/>
                                <input type="hidden" name="paymentID" value="<%= payment.getPaymentID() %>"/>
                                <input class="deletepmt" type="submit" value="Delete"/>
                            </form>
                        </td>
                        <td>
                        <!-- Button to edit selected payment. (Does not work).-->
                            <form method="POST" action="<%= request.getContextPath() %>/updatePaymentServlet">
                                <input type="hidden" name="function" value="UpdatePayment"/>
                                <input type="hidden" name="paymentID" value="<%= payment.getPaymentID() %>"/>
                                <input class="updatepmt" type="submit" value="Update"/>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
        <!-- if the paymentList is null, this means that the user has made no current payments.-->
            <h3>You currently have no payments made!</h3>
        <% } %>
        
    </body>
</html>
