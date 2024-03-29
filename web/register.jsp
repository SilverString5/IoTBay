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
        <link rel="stylesheet" href="css/layout.css">
        <title>Registration</title>
    </head>

    <body> 

         <%  String isRegistered = request.getParameter("isRegistered") ;
                String email = request.getParameter("email");
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String password = request.getParameter("password");
                String address = request.getParameter("address");
        
                if (isRegistered!=null){
                User user = new User(email, name, phone, password, address);
                session.setAttribute("user",user);
         }%>
        <% if (isRegistered==null){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/IoTBayWebApplication/">Home</a></li>
                <li><a href="http://localhost:8080/IoTBayWebApplication/login.jsp" > Login</a></li>
                <li><a href="http://localhost:8080/IoTBayWebApplication/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/IoTBayWebApplication/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
            <h1>IotBay - Register Here!</h1>
            <form method="post" action="http://localhost:8080/IoTBayWebApplication/welcome.jsp">
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
                <td><input type="number" name="phone number" id="phone number" placeholder="phone number" required></td>              
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
              <input type="hidden" name="isRegistered" id="isRegistered" value="true" />
            <button type="submit">Register Account</button>
        </form>
        
        <%} else { %>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/IoTBayWebApplication/">Home</a></li>
                <li><a href="http://localhost:8080/IoTBayWebApplication/register.jsp" > Register</a></li>
                <li><a href="http://localhost:8080/IoTBayWebApplication/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
            <h1>You have already registered.</h1>
            <button><a href="http://localhost:8080/IoTBayWebApplication/welcome.jsp">Welcome, <%= name %></a></button>

        <% } %>
        
    </body>
</html>