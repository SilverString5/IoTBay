

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        <title>Manage Registration</title>
    </head>
    <body>
        
    <%User user = (User)session.getAttribute("user");%>
  
        <h1>Your Registration Details</h1>
    </body>
    <form action="<%= request.getContextPath()%>/UpdateRegistrationServlet" method="post">
    <table>
        <input type="hidden" name="ID" value="<%=user.getUserID()%>"">
        <tr>
            <th>Email: </th>
            <td>
                  <input type="text" name="email" value="<%=user.getEmail()%>">
            </td>
        </tr>
        <tr>
            <th>Password: </th>
            <td>
                <input type="password" name="password" value=<%=user.getPassword()%>>
            <td>
        </tr>
        <tr>
            <th>Full Name: </th>
            <td>
                <input type="text" name="name" value=<%=user.getName()%>>
            </td>
        </tr>
        <tr>
            <th>Phone Number: </th>
            <td>
                <input type="text" name="phone" value="<%=user.getPhone()%>">
            </td>  
        </tr>
        <tr>
            <th>Address: </th>
             <td>
                 <input type="text" name="address" value="<%=user.getAddress()%>">
             </td>
        </tr>
        <tr>
            <th>Date of Birth: </th>
            <td>
                <input type="date" name="DOB" value="<%=user.getuserDOB()%>">
            </td>
        </tr>
        <tr>
        <td>
        <th>Gender: </th>
        <input type="radio" id="female" name="gender" value="F">
        <label for="female"> Female </label>
        <input type="radio" id="male" name="gender" value="M">
        <label for="male"> Male </label>
        <input type="radio" id="other" name="gender" value="O"
        <label for="other"> Other </label>
        <input type="radio" id="private" name="gender" value="P"
        <label for="private">Prefer not to say</label><br>
        </td>       
        <br>
        </tr>
    </table>
    </form>
</html>