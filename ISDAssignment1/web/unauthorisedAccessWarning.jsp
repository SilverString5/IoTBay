<%-- 
    Document   : unauthorisedAccessWarning
    Created on : 13 May 2024, 7:34:55 am
    Author     : lorinchanel
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/style.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        
        <title>Unable To Access</title>
    </head>
    <body>
        
        <div class="body-background">
        
            <div class="window">
                <h1>Unauthorised Access</h1>
                <p>You are attempting to access a page that you should only access from shipment history. Please go back to the shipment history page to delete or update your shipment record</p>
            
                <form method="POST" action="./index.jsp">
                    <button type="submit">Back to Home</button>
                </form>
            
            </div>
            
        </div>
    </body>
</html>
