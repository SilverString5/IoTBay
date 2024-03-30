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
            

    <body>
 
               <% User user = new User();
                session.setAttribute("user", user);%>
                %>
                
          <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/login.jsp" >Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
         <h1>IotBay - Register Here!</h1>
        <form action="http://localhost:8080/ISDAssignment1/welcome.jsp" method="post">
            <table class="centre">
            <tr>
                <td><label for="email">Email:</label>
                </td>
                
                <td><input type ="email" name ="email" id="email" placeholder="email" required/></td>
            </tr>  
            <tr>
                <td><label for="name">Name:</label></td>
                <td><input type="text" name="name" id="name" placeholder="name" required/></td>
            </tr>
            <tr>
                <td><label for="phone">Phone Number:</label></td>
                <td><input type="number" name="phonenumber" id="phonenumber" placeholder="phone number" required></td>              
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td>
                    <input type="password" name="password" id="password" placeholder="password" required>
                </td>
            </tr>
            <tr>
                <td><label for="address">Address:</label></td>
                <td>
                    <input type="text" name="address" id="address" placeholder="address" required>
                </td>
            </tr>
            
               </table>
              <input type="hidden" name="submitted" id="submitted" value="true" />
            <button type="submit">Register Account</button>
        </form>
                    
            

            
    </body>
</html>
