<!-- Instead of using PaymentDAOTest, this file was used instead to test the functionality of the DAO because of technical issues on the day of evaluation..-->
<%@page import="java.util.Date"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Random"%> 
<%@page import="uts.isd.model.*"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page import="uts.isd.controller.*"%>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/reglayout.css">
    <title>IoTBay WebStore</title>
</head>

<body>
    <h1>Test</h1>
    <%
        DBConnector connector;
        Connection conn;
        PaymentDAO manager;

        connector = new DBConnector();
        conn = connector.openConnection();
        manager = new PaymentDAO(conn);
        
        ArrayList<Payment> payments = manager.fetchPayment();

        %>
        
        <h2>Payment size:<%=payments.size()%></h2>
        
        
        <h2>Test update Payment</h2>
        <p>Before update</p>
        <% Payment paymentex = manager.findPaymentRecord(4);%>
        
        <p><%=paymentex.getPaymentMethod()%></p>
        <p><%=paymentex.getExpiryDate()%></p>
        <p><%=paymentex.getPaymentCVC()%></p>
        <p><%=paymentex.getPaymentCardNumber()%></p>
        
        <% manager.updatePayment(4, "VISA", new java.sql.Date(new Date(2033, 02, 03).getTime()), 199, 898767876);%>
       
        <p>After update</p>
        <p><%=paymentex.getPaymentMethod()%></p>
        <p><%=paymentex.getExpiryDate()%></p>
        <p><%=paymentex.getPaymentCVC()%></p>
        <p><%=paymentex.getPaymentCardNumber()%></p>
        
        <h2>Trying to call all Payments from a User. (User ID: 8)</h2>

        <%
            ArrayList<Payment> payments1 = manager.fetchPaymentsFromACustomer(8);
    
            for (Payment payment : payments1) { %>
            <p><%=payment.getPaymentID()%></p><br>
            <p><%=payment.getPaymentMethod()%></p><br>
            <p><%=payment.getExpiryDate()%></p><br>
            <p><%=payment.getPaymentCVC()%></p><br>
            <p><%=payment.getPaymentCardNumber()%></p><br>
            <p><%=payment.getCustomerID()%></p><br>
                
           <%} %>
           
           <h2>Creating a new Record</h2>
           <% manager.createPayment("Paypal", new java.sql.Date(new Date(2030, 01, 01).getTime()), 123, 888777666, 8);
           Payment paymentex1 = manager.findPaymentRecordByCardNumber(888777666); %>
           <p>Inserted values: Paypal, Date: 2030-01-01, CVC: 123, 888777666, UserID: 8</p>
           
           <p>Output: (retrieved by card number)</p>
            <p><%=paymentex1.getPaymentID()%></p><br>
            <p><%=paymentex1.getPaymentMethod()%></p><br>
            <p><%=paymentex1.getExpiryDate()%></p><br>
            <p><%=paymentex1.getPaymentCVC()%></p><br>
            <p><%=paymentex1.getPaymentCardNumber()%></p><br>
            <p><%=paymentex1.getCustomerID()%></p><br>
            
            
            <p>Deleting a Record based off payment.</p>
            <p>Value: </p>
            
            
            <% Payment paymentgoner = manager.findPaymentRecord(9);%>
            <p>Output: (retrieved by card number)</p>
            <p><%=paymentex1.getPaymentID()%></p><br>
            <p><%=paymentex1.getPaymentMethod()%></p><br>
            <p><%=paymentex1.getExpiryDate()%></p><br>
            <p><%=paymentex1.getPaymentCVC()%></p><br>
            <p><%=paymentex1.getPaymentCardNumber()%></p><br>
            <p><%=paymentex1.getCustomerID()%></p><br>
            
            <%manager.deletePaymentByUser(18);%>
            
            <%// manager.deletePayment(18); %>
           
           
      
            
</body>
</html>
