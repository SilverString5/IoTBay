<%-- 
    Document   : welcome
    Created on : 29/03/2024, 2:47:44 PM
    Author     : notba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="User.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome-Kat</title>
    </head>
    <body>
        
      <%User user = (User)session.getAttribute("user");
      String submitted="nothing";
      submitted = request.getParameter("submitted");
      %>
      
      
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
            
            <% if (user==null){ %>
            <h2>Hi anonymous user</h2>
            <h2>Creating an Account?</h2>
            <button><a href="http://localhost:8080/ISDAssignment1/register.jsp">Register</a></button>
            
            <% } %>
      
      
     
      <% if ((user!=null) && (submitted).equals("true")){
                user.setName(request.getParameter("name"));
                user.setEmail(request.getParameter("email"));
                user.setPhone(request.getParameter("phonenumber"));
                user.setAddress(request.getParameter("address"));
       } %>
               
                User's name is <%=user.getName()%>
               User's email is <%=user.getEmail()%>
               User's phone number is <%=user.getPhone()%>
               User's address is <%=user.getAddress()%>
    </body>
</html>
