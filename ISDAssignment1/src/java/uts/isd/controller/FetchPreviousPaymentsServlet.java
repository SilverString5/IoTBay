/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isd.controller;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import isd.model.Payment;
import isd.model.Payments;
import isd.model.dao.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import isd.model.dao.PaymentDAO;
import java.util.*;
import uts.isd.model.User;
/**
 *
 * @author sienn
 */
public class FetchPreviousPaymentsServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
            return;
        }
        try {
            PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
            ArrayList<Payment> previousPayments = paymentDAO.fetchPaymentsFromACustomer(user.getUserID());
            
            String jsonPayments = convertToJson(previousPayments);
            
            //Set response content type to JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            //Write JSON response to the response body
            response.getWriter().write(jsonPayments);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to fetch previous payments");
            e.printStackTrace();
        }

    }
    
    private String convertToJson(ArrayList<Payment> payments) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < payments.size(); i++) {
           Payment payment = payments.get(i);
           json.append("{");
           json.append("\"paymentID\":").append(payment.getPaymentID()).append(",");
           json.append("\"paymentMethod\":\"").append(payment.getPaymentMethod()).append("\",");
           //Converting ExpiryDate (Date) to String.
           String expiryDateString = payment.getExpiryDate().toString();
           json.append("\"expiryDate\":\"").append(expiryDateString).append("\",");
           //Convert PaymentCVC (int) to String
           String paymentCVCString = Integer.toString(payment.getPaymentCVC());
           json.append("\"paymentCVC\":\"").append(paymentCVCString).append("\",");
           //Convert PaymentCardNumber (int) to String
           String paymentCardNumberString = Integer.toString(payment.getPaymentCardNumber());
           json.append("\"paymentCardNumber\":\"").append(paymentCardNumberString).append("\",");
           json.append("\"userID\":").append(payment.getCustomerID());
           json.append("}");
           if(i < payments.size() - 1){
               json.append(",");
           }     
        }
        json.append("]");
        return json.toString();
    }
    
}
