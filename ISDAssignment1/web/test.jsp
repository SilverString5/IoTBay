<%-- 
    Document   : test.jsp
    Created on : 08/05/2024, 10:01:58 AM
    Author     : notba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                <% if (request.getParameter("submitted")!=null){
            String dateString = request.getParameter("date");
            if (dateString.isEmpty()){ %>
            <p> Empty</p>
          <%  }
            else if(dateString.equals("null")){ %>
            <p> Null value </p>
         <%   }
            else{ %>
            <p>  Something else </p>
          <%  }
  }%>
        <br><br><br>
        <form>
            <input type="date" name="date" id="date">
            <input type="hidden" name="submitted" value="submitted">
            <button type="submit">Submit</button>
        </form>
        <br><br><br>
        <%System.out.println("Hello"); %>

        
        
        
    </body>
</html>
