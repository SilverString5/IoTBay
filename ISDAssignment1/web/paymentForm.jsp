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
            <form class="myForm" action="./createNewPaymentServlet" method="post" onsubmit="return validateForm()">
                <h1 class="myHeader">Add your Payment Details Here.</h1>
                <label for="paymentMethod">Payment Method:</label><br>
                <select name="paymentMethod" id="PaymentMethod">
                    <option value="Visa">Visa</option>
                    <option value="Mastercard">Mastercard</option>
                    <option value="Paypal">Paypal</option>
                    <option value="GooglePay">Google Pay</option>
                </select>
            
                <label for="ExpiryDate">Expiry Date</label><br>
                <input type="date" name="ExpiryDate" id="expiryDate" placeholder="MM/YY" pattern="(0[1-9]|1[0-2])\/\d{2}" required><br>
                <span id="expiryDateError" class="error-message"></span><br><br>
            
                <label for="paymentCVC">CVC</label><br>
                <input type="text" name="paymentCVC" id="paymentCVC" placeholder="CVC" pattern="\d{3}" required/><br>
                <span id="paymentCVCError" class="error-message"></span><br><br>
           
                <label for="paymentCardNumber">Card Number:</label><br>
                <input type="number" name="paymentCardNumber" id="paymentCardnumber" placeholder="Card Number" pattern="\d{9}" required><br>   
                <span id="paymentCardNumberError" class="error-message"></span><br><br>
                
                
                
                <label for="previousPayments"> Previous Payments:</label>
                <select id="previousPayments" name="previousPayments">
                <!-- Options will be dynamically populated using JavaScript -->
                </select><br><br>
            
                <input type="hidden" name="submitted" id="submitted" value="true" /><br>
                <button type="submit">Submit</button>
            </form>
                
                <script>
                    function validateForm(){
                        //Reset error messahes
                        document.getElementById("expiryDateError").innerHTML = "";
                        document.getElementById("paymentCVCError").innerHTML = "";
                        document.getElementById("paymentCardNumberError").innerHTML = "";
                        
                        var isValid = true;
                        
                        // Validate Expiry Date
                        var expiryDate = document.getElementById("expiryDate").value;
                        if (!expiryDate) {
                            document.getElementById("expiryDateError").innerHTML = "Please fill in the expiry date.";
                            isValid = false;
                        }
                        
                        //Validate CVC
                        var paymentCVC = document.getElementById("paymentCVC").value;
                        if(!paymentCVC) {
                            document.getElementById("paymentCVCError").innerHTML = "Please fill in the CVC";
                            isValid = false;
                        }
                        
                        var paymentCardNumber = document.getElementById("paymentCardNumber").value;
                        if(!paymentCardNumber) {
                            document.getElementById("paymentCardNumberError").innerHTML = "Please fill in the card number";
                            isValid = false;
                        }
                        
                        return isValid;

                    }
                    
                    function populatePreviousPayments() {
                        var xhr = new XMLHttpRequest();
                        xhr.open("GET", "FetchPreviousPaymentServlet", true);
                        xhr.onreadystatechange = function() {
                            if (xhr.readyState === 4 && xhr.status === 200) {
                                var previousPayments = JSON.parse(xhr.responseText);
                                var dropdown = document.getElementById("previousPayments");
                                dropdown.innerHTML = ""; // Clear previous options.
                                
                                for (var i = 0; i < previousPayments.length; i++) {
                                    var payment = previousPayments[i];
                                    var option = document.createElement("option");
                                    option.text = payment.paymentMethod + " - " + payment.paymentCardNumber;
                                    option.value = JSON.stringify(payment); //Storing the entire payment.
                                    dropdown.appendChild(option);
                                }        
                            }
                        };
                        xhr.send();
                    }
                    
                    //Calling the populatePreviousPayments function when the page loads.
                    window.onload = function() {
                        populatePreviousPayments();
                    }
                    
                    
                   
                    
                </script>
                
                
        </div>
        
        
      
    </body>
</html>
