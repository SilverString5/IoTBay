<%-- 
    Document   : register
    Created on : 28/03/2024, 4:57:52 PM
    Author     : notba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="User.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <title>Registration</title>
    </head>
            

    <body>
        <h1>IotBay - Register Here!</h1>
      
     

               
        <% if (session.getAttribute("user")==null) {
                User user = new User();
                session.setAttribute("user", user);%>
                
                Creating New User
        
        <form action="/ISDAssignment1/welcome.jsp" method="post">
            <table>
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
            <% } else {  %>
            You have already registered.
            
           <form action="/ISDAssignment1/welcome.jsp" method="post">
               <input type="hidden" name="submitted" value="false"/>
         <button type="submit">Proceed</button>
            <% } %>
    </body>
</html>
