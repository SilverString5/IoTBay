<%-- 
    Document   : register
    Created on : 28/03/2024, 4:57:52 PM
    Author     : Katherine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Registration</title>
    </head>

      <%
        String errorMsgs = (String) session.getAttribute("errorMsgs");
        User user = (User) session.getAttribute("user");
       if(user != null && user.getUserType().equals("S")){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" >You</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/viewAccessLogs.jsp" >Your Access Logs</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/manageRegistration.jsp" >Manage Account Details</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp" >Logout</a></li>
            </ul>
                                
            </div>
            <br>
            <br>
        <%}else if(user != null && user.getUserType().equals("C")){%>
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
        <% } %>
        

    <body>
    <br>
    <br>
        <% if (user==null) { %>
        <div class="myFormdiv">
            
        <form class="myForm" action="<%= request.getContextPath()%>/RegisterServlet" method="post">
            <h1 class="myHeader">Register Here!</h1>
            
            <label for="email">Email:</label><br>
            <input type ="email" name ="email" id="email" placeholder="Email" required/><br>
            
            <label for="password">Password:</label><br>     
            <input type="password" name="password" id="password" placeholder="Password" required><br>
               
            <label for="name">Name:</label><br>
            <input type="text" name="name" id="name" placeholder="Name" required/><br>
           
            <label for="phone">Phone Number:</label><br>
            <input type="number" name="phonenumber" id="phonenumber" placeholder="Phone Number" required><br>           
            

            <label for="address">Address:</label><br>
                
            <input type="text" name="address" id="address" placeholder="Adddress" required>
            <br>Gender<br>
            
            <input type="radio" id="female" name="gender" value="F">
            <label for="female"> Female </label>
                    
            <input type="radio" id="male" name="gender" value="M">                   
            <label for="male"> Male </label>
                    
            <input type="radio" id="other" name="gender" value="O"
            <label for="other"> Other </label>
                    
            <input type="radio" id="private" name="gender" value="P"
            <label for="private">Prefer not to say</label><br><br>
                    
            <label for="DOB">Date of Birth</label><br>
            <input type="date" name="DOB" id="DOB" required><br>
  
            <input type="hidden" name="submitted" id="submitted" value="true" /><br>
            <button type="submit">Register Account</button>
            <% if(errorMsgs != null) { %>
           <br>
            <p class="errors"><%=errorMsgs%></p>
            <% } %>
            
        </form>
        </div>
            <% } else { %>
            <p>You have already logged in</p>
            <% } %>
           
    </body>
</html>