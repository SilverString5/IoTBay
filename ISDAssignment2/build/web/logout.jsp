<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        
        <title>IoTBay Log Out</title>
    </head>
    
   <% String logoutTimeUpdated;
       logoutTimeUpdated = (String)session.getAttribute("logoutTimeUpdated");%>
    <body>

        <%  //This if statement checks if the user has been redirected to LogoutServlet previously
            if (logoutTimeUpdated!=null){
            session.invalidate(); %>
             <div class="window">
            <h1>You have successfully logged out</h1>
            <p>Please delete the browser window or click on the button below to transfer to IoTBay home page</p>
            <form action="http://localhost:8080/ISDAssignment1/">
                <button type="submit">Back to Home</button>
            </form>
        </div>
          <%  }  else { %>
        <div class="window">
            <h1>Please confirm you wish to log out</h1>
            <!--Registered and logged in users will have their logout times updated in the
            current access log after visiting LogoutServlet-->
        <form action="<%= request.getContextPath()%>/LogoutServlet" method="post">
            <button type="submit">Confirm Logout</button>
        </form>
        </div> 
          
          <% } %>    
    </body>
</html>