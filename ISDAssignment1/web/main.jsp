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
        <title>JSP Page</title>
    </head>
    <body>
        <%
           String email = request.getParameter("email");
           String name = request.getParameter("name");
           String phone = request.getParameter("phone");
           String password = request.getParameter("password");
           String address = request.getParameter("address");          
           User user = new User(email, name, phone, password, address);
           session.setAttribute("user", user);
//             User user = (User) session.getAttribute("user");
        %>
        
        <h1>Hello <%=user.getName()%> 
            <br> 
            Welcome to IoTBay Web Application
        </h1>
            <button><a href="logout.jsp">Logout</button>
    </body>
</html>
