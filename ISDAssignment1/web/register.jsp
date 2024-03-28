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
        <title>Registration</title>
    </head>
            <% String isRegistered = request.getParameter("isRegistered") ;
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        
        
        if (isRegistered!=null){
            User user = new User(email, name, phone, password, address);
            session.setAttribute("user",user);
        }
        %>
    <body>
        <h1>IotBay - Register Here!</h1>
      

               
        <% if (isRegistered==null){%>
        
        <form>
            <table>
            <tr>
                <td><label for="email">Email:</label>
                </td>
                
                <td><input type ="email" name ="email" id="email" placeholder="email" required/></td>
            </tr>  
            <tr>
                <td><label for="name">Name:</label></td>
                <td><input type="text" name="name" id="name" required/></td>
            </tr>
            <tr>
                <td><label for="phone">Phone Number:</label></td>
                <td><input type="number" name="number" id="number" placeholder="number" required></td>              
            </tr>
            <tr>
                <td><label for="password"></label></td>
                <td>
                    <input type="password" name="password" id="password" placeholder="password" required>
                </td>
            </tr>
            <tr>
                <td><label for="address">Address:</label></td>
                <td>
                    <input type="text" name="address" id="address" placeholder="address required">
                </td>
            </tr>
            
               </table>
              <input type="hidden" name="isRegistered" id="isRegistered" value="true" />
            <button type="submit">Register Account</button>
        </form>
        
        <%} else { %>
        <h1>You have already registered. Hello, <%=name %></h1>
        
        <% } %>
    </body>
</html>
