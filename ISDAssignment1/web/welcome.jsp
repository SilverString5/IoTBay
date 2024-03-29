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
        <title>JSP Page</title>
    </head>
    <body>
        
     
        
        <% User u =(User)session.getAttribute("user"); %>
        
        Welcome, <%=u.getName()%>
    </body>
</html>
