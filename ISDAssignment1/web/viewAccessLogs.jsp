<%-- 
    Document   : viewAccessLogs
    Created on : 06/05/2024, 7:10:35 PM
    Author     : notba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reglayout.css">
        <title>Your Access Logs</title>
    </head>
    <body>
           <div class="menu">
            <ul>
                <li><a href="http://localhost:8080/ISDAssignment1/">Home</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/register.jsp">Register</a></li>
                <li><a href="http://localhost:8080/ISDAssignment1/welcome.jsp">You</a></li>
            </ul>
               <br><br><br>
                        <form action="<%= request.getContextPath()%>/ConnServlet" method="get">
                            <button type="submit">View Your Access Logs</button>
                        </form>                   
            </div>
            <br>
        <h1>Access Logs</h1>
        <div align="center">
            <table border="1", cellpadding="5">
                <tr>
                    <th>Log ID</th>
                    <th>Date</th>
                    <th>Login Time</th>
                    <th>Logout Time</th>       
                </tr>
            <c:forEach var="log" items="${allMyLogs}">
                <tr>
                    <td><c:out value="${log.accessLogID}" /></td>
                    <td><c:out value="${log.accessLogDate}" /></td>
                    <td><c:out value="${log.loginTime}" /></td>
                    <td><c:out value="${log.logoutTime}" /></td>
                </tr>
            </c:forEach>
            </table>   
        </div>
    </body>
</html>