<%-- 
    Document   : register
    Created on : 28/03/2024, 4:57:52 PM
    Author     : notba
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
        
        <%}%>
        
        <div>
         <h1 class="myheader">Register Here!</h1>
         <div class="myFormdiv">
        <form class="myForm" action="welcome.jsp" method="post">
            <label for="email">Email:</label><br>
           
            <input type ="email" name ="email" id="email" placeholder="Email" required/><br>
            
                <label for="name">Name:</label><br>
                <input type="text" name="name" id="name" placeholder="Name" required/><br>
           
           <label for="phone">Phone Number:</label><br>
                <input type="text" name="phonenumber" id="phonenumber" placeholder="Phone Number" required><br>           
            
               <label for="password">Password:</label><br>
                
                    <input type="password" name="password" id="password" placeholder="Password" required><br>
               
            
            
                <label for="address">Address:</label><br>
                
                    <input type="text" name="address" id="address" placeholder="Adddress" required><br>
                
            
  
              <input type="hidden" name="submitted" id="submitted" value="true" /><br>
            <button type="submit">Register Account</button>
         
        </form>
         </div>
             
                    
            

            
    </body>
</html>