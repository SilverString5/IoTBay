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
<<<<<<< Updated upstream
=======
      <%User user = (User)session.getAttribute("user");
      String submitted = "nothing";
      if (request.getParameter("submitted")!=null){
      submitted = request.getParameter("submitted");
      }%>
>>>>>>> Stashed changes
        
      <%User user = (User)session.getAttribute("user");
      String submitted="nothing";
      submitted = request.getParameter("submitted");
      %>
      
      
      <div class="menu">
            <ul>
<<<<<<< Updated upstream
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
=======
                <li><a id="active" href="http://localhost:8080/ISDAssignment1/index.jsp">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp">Logout</a></li>
            </ul>
        </div>
        <br>  
        <br>
        <div>
        <%if(user != null) {%>
            <% if ((submitted).equals("true")){
>>>>>>> Stashed changes
                user.setName(request.getParameter("name"));
                user.setEmail(request.getParameter("email"));
                user.setPhone(request.getParameter("phonenumber"));
                user.setAddress(request.getParameter("address"));
<<<<<<< Updated upstream
       } %>
               
                User's name is <%=user.getName()%>
               User's email is <%=user.getEmail()%>
               User's phone number is <%=user.getPhone()%>
               User's address is <%=user.getAddress()%>
=======
            } %>
        <h1>Hello <%=user.getName()%>,
            <br> 
            Welcome to IoTBay Web Application
        </h1>
      
        <% } else {%>
        <h1> Hello Anonymous User, </h1>
        <h2> Do you want to register? </h2>
        <button><a href="register.jsp">Register</a></button>
        
        <% } %>
        </div>
>>>>>>> Stashed changes
    </body>
</html>
