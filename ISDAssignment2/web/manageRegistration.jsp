
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Manage Account Details</title>
    </head>
    <body>
        
    <%User user = (User)session.getAttribute("user");
    String updateMsgs = (String)session.getAttribute("updateMsgs");
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
        <!--This form will be prefilled with the user's current account details, 
        and allows the user to update or delete their account -->
<% if (user!=null) { %>
<div class="myFormdiv">
    <form class="myForm" action="<%= request.getContextPath()%>/UpdateRegistrationServlet" method="post">
                <h1>Your Registration Details</h1>
        <input type="hidden" name="ID" value="<%=user.getUserID()%>"">
        <label for="email">Email:</label><br>
        <input type="text" name="email" value="<%=user.getEmail()%>"><br>
        <label for="password">Password: </label><br> 
        <input type="password" name="password" value=<%=user.getPassword()%>><br>
        <label for="name">Full Name: </label><br>
        <input type="text" name="name" value=<%=user.getName()%>><br>
        <label for="phone">Phone Number: </label><br>
        <input type="text" name="phone" value="<%=user.getPhone()%>"><br>
        <label for="address">Address: </label><br>
        <input type="text" name="address" value="<%=user.getAddress()%>"><br>
        <label for=DOB>Date of Birth: </label><br>
        <input type="date" name="DOB" value="<%=user.getuserDOB()%>"><br>
        Gender: <br>
        <input type="radio" id="female" name="gender" value="F">
        <label for="female"> Female </label><br>
        <input type="radio" id="male" name="gender" value="M">
        <label for="male"> Male </label><br>
        <input type="radio" id="other" name="gender" value="O"
        <label for="other"> Other </label><br>
        <input type="radio" id="private" name="gender" value="P"
               <label for="private">Prefer not to say</label>         
        <br><button type="Submit">Submit</button>
            <% if (updateMsgs!=null){%>
            <p><%=updateMsgs%></p>
           <% }%>
        <br>
        <button type="submit" formmethod="post"
        formaction="<%= request.getContextPath()%>/DeleteRegistrationServlet">Delete Your Account</button>
    </form>
>
    </div>
<% } else { %>
            <p>You are not logged in!</p>
<% } %>
</body>
</html>