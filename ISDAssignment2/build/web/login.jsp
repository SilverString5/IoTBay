<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Login</title>
    </head>
    <% 
        // invalidLogin will display an error message if the login details are not correct
        String invalidLogin = (String) session.getAttribute("invalidLogin");
        User user = (User) session.getAttribute("user");
        
        // The navigation bar will change depending on what type of user you are
        if(user != null && user.getUserType().equals("S")){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" >You</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/viewAccessLogs.jsp" >Your Access Logs</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/manageRegistration.jsp" >Manage Account Details</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp" >Logout</a></li>
            </ul>
                                
            </div>
            <br>
            <br>
        <%}else if(user != null && user.getUserType().equals("C")){%>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" >You</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/viewAccessLogs.jsp" >Your Access Logs</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/manageRegistration.jsp" >Manage Account Details</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/DisplayCartServlet">Shopping Cart</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/OrderHistoryServlet" >Orders</a></li>
                <li><a href="./shipmentHistory" >Shipping</a></li>                 
                <li><a href="http://localhost:8080/ISDAssignment1/logout.jsp" >Logout</a></li>
            </ul>                             
            </div>
            <br>
            <br>
        <%}else{ %>
            <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/login.jsp" >Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp" >Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/DisplayCartServlet">Shopping Cart</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/OrderHistoryServlet" >Orders </a></li>
            </ul>              
            </div>
            <br>
            <br>
        <% } %>
   
    <body>
        
        <% if (user==null) { %>
        <div class="myFormdiv">
        <!-- Form placed in div so CSS will centre the login form.
        This form will direct to the LoginServlet so that inputs can be verified -->
        <form class="myForm" method="post" action="<%= request.getContextPath()%>/LoginServlet">
            <h1 class="myHeader">Login Here!</h1>
            <label for="email1">Email: </label><br>
            <input type="text" id = "email" placeholder = "email" name = "email" required><br><br>

            <label for="Password">Password: </label><br>
            <input type= "password" id = "password" placeholder="password" name="password" required><br><br>

            <input type="hidden" name="submitted" id="submitted" value="true" />
            <button type="submit">Login</button><br>
                <% if(invalidLogin != null) { %>
           <br>
                <p><%=invalidLogin%></p>
            <% } %>
                    <p>Don't have an account?</p>
        <a href="register.jsp">Register</a>
<% } else { %>
<p>You have already logged in</p>
<% } %>
        
        </form>
                    
            <br>

                    </div>
           

   
    </body>
    
</html>