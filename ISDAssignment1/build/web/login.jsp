<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<!--<%@page import="uts.isd.model.*"%>-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
    </head>
    <body>
        <%
            String email1 = request.getParameter("email1");
            String password1 = request.getParameter("password1");
            String submitted1 = request.getParameter("submitted1");
            
            if (submitted != null){
                <!-- IF email1 and password1 != email and password from user in register-->
                <!-- Show another label that explains password or username is wrong; try again -->
            }
            else {
                <!-- go to account page (logged in) -->
            }
            

        %>
        <form method="post">
            <label for="email1">Email: </label><br>
            <input type="text" id = "email1" name = "email1"><br>

            <label for="Password">Password: </label><br>
            <input type= "password" id = "password1" name = "password1">

            <button type="submit">Login</button>
        </form>

        <h3>Don't have an account?</h1>
            <div>
                <a href="register.jsp">Register</a>
                 
            </div>  
    </body>
    <!-- need to revise.!S-->
</html>
