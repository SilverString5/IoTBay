<%-- 
    Document   : deleteShipment
    Created on : 10 May 2024, 4:17:44 pm
    Author     : lorinchanel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/style.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
          User user = (User) session.getAttribute("user");
        %>
        
         <%if(user != null) {%>
            <div class="body-background">
        
                <div class="window">
                    <h1>Would you like to cancel your shipment?</h1>
                    <p>By clicking the button below, you are confirming that your shipment for this order will be cancelled</p>

                    <% int shipmentID = Integer.parseInt(request.getParameter("shippingID")); %>

                    <form method="POST" action="./deleteShipmentDetail?shippingID=<%=shipmentID%>">
                        <button type="submit">Delete</button>
                    </form>
                </div>
            </div>
                    
        <% } else { 
            response.sendRedirect("./unregisteredWarning.jsp");
        }%>
    </body>
</html>
