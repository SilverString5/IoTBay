<%-- 
    Document   : main
    Created on : Mar 28, 2024, 3:29:37 PM
    Author     : pyaephyozaw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/layout.css">
        <title>Welcome Page</title>
    </head>
    <body>
      <%User user = (User)session.getAttribute("user");
      String submitted = "nothing";
      if (request.getParameter("submitted")!=null){
      submitted = request.getParameter("submitted");
      }%>
        
        <div class="menu">
            <ul>
                <li><a id="active" href="http://localhost:8080/ISDAssignment1/index.jsp">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp">Logout</a></li>
            </ul>
        </div>
        <br>  
        <br>
        <div>
        <%if(user != null) {%>
            <% if ((submitted).equals("true")){
                user.setName(request.getParameter("name"));
                user.setEmail(request.getParameter("email"));
                user.setPhone(request.getParameter("phonenumber"));
                user.setAddress(request.getParameter("address"));
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
    </body>
</html>
