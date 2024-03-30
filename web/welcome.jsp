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
        
        <div class="menu">
            <ul>
                <li><a id="active" href="http://localhost:8080/IoTBayWebApplication/index.jsp">Home</a></li>
                <li><a href="http://localhost:8080/IoTBayWebApplication/logout.jsp">Logout</a></li>
            </ul>
        </div>
        <br>  
        <br>
        <div>
            <%if(request.getParameter("submitted") != null){%>
            <%  
            
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phoneNumber = request.getParameter("phonenumber");
                String address = request.getParameter("address");
                String password = request.getParameter("password");
                User user = new User(email, name, phoneNumber, password, address);
                session.setAttribute("user", user);
               
            %>
        <%}%>
        <% User user = (User)session.getAttribute("user");%>
        
        <%if(user != null){%>
            <h1>Hello <%=user.getName()%>,
                <br> 
                Welcome to IoTBay Web Application
            </h1>

            </div>
            
        <%}  else {%>
        <h1> Hello Anonymous User, </h1>
        <h2> Do you want to register? </h2>
        <button><a href="register.jsp">Register</a></button>
        
        <% } %>
    </body>
</html>
