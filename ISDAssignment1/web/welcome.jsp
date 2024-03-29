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
        
      <%User user = (User)session.getAttribute("user");
      String submitted="nothing";
      submitted = request.getParameter("submitted") ;%>
      
        <% if (session.getAttribute("user")!=null && (submitted).equals("true") ){
                user.setName(request.getParameter("name"));
                user.setEmail(request.getParameter("email"));
                user.setPhone(request.getParameter("phonenumber"));
                user.setAddress(request.getAddress("address"));
        }%>
                
               User's name is <%=user.getName()%>
               User's email is <%=user.getEmail()%>
               User's phone number is <%=user.getPhone()%>
               User's address is <%=user.getAddress()%>
    </body>
</html>
