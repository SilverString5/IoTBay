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
    <body>
        <% 
            session.invalidate();
        %>
        
        <div class="window">
            <h1>You have successfully logged out</h1>
            <p>Please delete the browser window or click on the button below to transfer to IoTBay home page</p>
            <form action="http://localhost:8080/ISDAssignment1/">
                <button type="submit">Back to Home</button>
            </form>
        </div>
    </body>
</html>
