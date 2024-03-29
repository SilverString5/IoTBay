<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/layout.css">
        <title>Login</title>
    </head>
    <body>
        <%
            String email1 = request.getParameter("email1");
            String password1 = request.getParameter("password1");
            String submitted = request.getParameter("submitted");
            
            if (submitted != null){
                
            }
            else {
                
            }
            

        %>
        <% if (submitted==null){%>
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
            
            <form method="post">
                <label for="email1">Email: </label><br>
                <input type="text" id = "email1" name = "email1"><br>

                <label for="Password">Password: </label><br>
                <input type= "password" id = "password1" name = "password1">
                <input type="hidden" name="submitted" id="submitted" value="true" />
                <button type="submit">Login</button>
            </form>
            
            <h3>Don't have an account?</h1>
                <div>
                    <a href="register.jsp">Register</a>
                </div>  
            
        <%} %>
    </body>
    <!-- need to revise.!S-->
</html>