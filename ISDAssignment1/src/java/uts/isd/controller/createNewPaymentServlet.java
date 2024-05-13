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

//Change string variables...

public class createNewPaymentServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Payment payment = (Payment) session.getAttribute("payment");
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        
        if (user != null) {
            try {
                ArrayList<Payment> previousPayments = paymentDAO.fetchPaymentsFromACustomer(user.getUserID());
                request.setAttribute("previousPayments", previousPayments);
            }
            catch (SQLException e){
                request.setAttribute("error", "Error fetching previous payments: ");
            }
        } else {
            request.setAttribute("error", "No previous payments found.");
        }
        
        request.getRequestDispatcher("PaymentForm.jsp").forward(request, response);
    }
    
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Payment payment = (Payment) session.getAttribute("payment");
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        
        
        //Get the selected payment information from the requested Parameter
        String selectedPaymentInfo = request.getParameter("previousPayments");
        
        session.removeAttribute("errorMsgs");
        
        
        if (selectedPaymentInfo != null && !selectedPaymentInfo.isEmpty()) {
            try {
                // Parse the selected payment info
                String[] paymentInfo = selectedPaymentInfo.split("\\|");
                if (paymentInfo.length == 5) {
                    // Set payment details as request attributes
                    request.setAttribute("selectedPaymentMethod", paymentInfo[1]);
                    request.setAttribute("selectedExpiryDate", paymentInfo[2]);
                    request.setAttribute("selectedPaymentCVC", paymentInfo[3]);
                    request.setAttribute("selectedPaymentCardNumber", paymentInfo[4]);
                }
            } catch (Exception e) {
                // Handle parsing error
                System.out.println("Error parsing selected payment info: " + e.getMessage());
            }
        }
        
        String paymentMethod = request.getParameter("paymentMethod");
        //getting expiryDate string, and then converting to an integer.
        String stringexpiryDate = request.getParameter("expiryDate");
        Date expiryDate = Date.valueOf(stringexpiryDate);
        //Getting the CVC as a string, and then converting it to an integer.
        String stringCVC = request.getParameter("paymentCVC");
        int paymentCVC = Integer.parseInt(stringCVC);
        //getting paymentCardNumber string, and then converting to an integer.
        String stringpaymentCardNumber = request.getParameter("paymentCardNumber");
        int paymentCardNumber = Integer.parseInt(stringpaymentCardNumber);
        
        String errorMsgs="";
        
        if (paymentMethod.isEmpty()){
            errorMsgs+="Your Payment Method is not filled in. <br>";
        }
        
        try {
            long now = System.currentTimeMillis();
            Date nowDate = new Date(now);
            if (expiryDate.before(nowDate)){
                errorMsgs+="Your expiry Date has reached. You are no longer able to use this payment Method.<br>";
            }
        }

        catch (IllegalArgumentException e) {
            System.out.println(e);
            errorMsgs+="The expiry date is empty. Please fill in this section.<br>";
        }
        

        if (stringCVC.length()<3){
            errorMsgs+="Your CVC must be at least 3 numbers.<br>";
        }
        if (stringCVC.isEmpty()){
            errorMsgs+="Please fill in the CVC.<br>";
        }
        
        
        
        if (stringpaymentCardNumber.length()!=9){
            errorMsgs+="You have not added your card number properly. Your Card Number should have 9 numbers.<br>";
        }
        if(stringpaymentCardNumber.isEmpty()){
            errorMsgs += "Please fill in the card number.<br>";
        }
        

        if (!errorMsgs.isEmpty()) {
            session.setAttribute("errorMsgs", errorMsgs);
            response.sendRedirect("paymentForm.jsp");
            //request.getRequestDispatcher("paymentForm.jsp").include(request,response);
        }
        else {
            try{
                
                if(user != null) {
                    if (!paymentDAO.checkExists(paymentCardNumber)){
                        paymentDAO.createPayment(paymentMethod, expiryDate, paymentCVC, paymentCardNumber, user.getUserID());
                    }
                    Payments payments = new Payments(paymentDAO.fetchPaymentsFromACustomer(user.getUserID()));
                    session.setAttribute("payments", payments);
                    
                }
                else {
                    paymentDAO.createPaymentForAnonymousUser(paymentMethod, expiryDate, paymentCVC, paymentCardNumber);
                }
                

                
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            } catch(SQLException e){
                System.out.println(e);
            }
        }
        
    }
}
