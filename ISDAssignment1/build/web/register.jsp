<%-- 
    Document   : register
    Created on : 28/03/2024, 4:57:52 PM
    Author     : Katherine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Registration</title>
    </head>

      <%
        String errorMsgs = (String) session.getAttribute("errorMsgs");
    %>      


    <body>
 
               <% User user = new User();
                session.setAttribute("user", user);%>
                
                
          <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/login.jsp" >Login</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp" > You</a></li>
            </ul>
            </div>
            <br>
            <br>
         <h1 class="myheader">Register Here!</h1>
         <div class="myFormdiv">
        <form class="myForm" action="<%= request.getContextPath()%>/RegisterServlet" method="post">
            <label for="email">Email:</label><br>
           
            <input type ="email" name ="email" id="email" placeholder="Email" required/><br>
            
                <label for="name">Name:</label><br>
                <input type="text" name="name" id="name" placeholder="Name" required/><br>

           
           <label for="phone">Phone Number:</label><br>
                <input type="number" name="phonenumber" id="phonenumber" placeholder="Phone Number" required><br>           
            
               <label for="password">Password:</label><br>
                
                    <input type="password" name="password" id="password" placeholder="Password" required><br>
               
            
            
                <label for="address">Address:</label><br>
                
                    <input type="text" name="address" id="address" placeholder="Adddress" required><br>
                
             Gender <br>
                    <input type="radio" id="female" name="gender" value="F">
                    <label for="female"> Female </label>
                    <input type="radio" id="male" name="gender" value="M">
                    <label for="male"> Male </label>
                    <input type="radio" id="other" name="gender" value="O"
                    <label for="other"> Other </label>
                    <input type="radio" id="private" name="gender" value="P"
                           <label for="private">Prefer not to say</label><br>
                    
                    <br>
                    <label for="DOB">Date of Birth</label><br>
                    <input type="date" name="DOB" id="DOB" required><br>
  
              <input type="hidden" name="submitted" id="submitted" value="true" /><br>
            <button type="submit">Register Account</button>
        </form>
         </div>
            
                <% if(errorMsgs != null) { %>
           <br>
                <p id="errors"><%=errorMsgs%></p>
            <% } %>
             
                    
            

            
    </body>
</html>
