/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uts.isd.controller;

import java.sql.SQLException;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Payment;
import uts.isd.model.Payments;
import uts.isd.model.dao.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import uts.isd.model.dao.PaymentDAO;
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
        
        String selectedPaymentInfo = request.getParameter("previousPayments");


        session.removeAttribute("errorMsgs1");

        if (selectedPaymentInfo != null && !selectedPaymentInfo.isEmpty()) {
            try {
                // Parse the selected payment info
                String[] paymentInfo = selectedPaymentInfo.split("\\|");
                if (paymentInfo.length == 5) {
                    // Set payment details as request attributes
                    String selectedPaymentMethod = paymentInfo[1];
                    // Set the selected payment method attribute
                    request.setAttribute("selectedPaymentMethod", selectedPaymentMethod);

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
        String stringexpiryDate = request.getParameter("ExpiryDate");
        System.out.println(stringexpiryDate);
        Date expiryDate = Date.valueOf(stringexpiryDate);
        //Getting the CVC as a string, and then converting it to an integer.
        String stringCVC = request.getParameter("paymentCVC");
        int paymentCVC = Integer.parseInt(stringCVC);
        //getting paymentCardNumber string, and then converting to an integer.
        String stringpaymentCardNumber = request.getParameter("paymentCardNumber");
//        System.out.println("CardNum: " + stringpaymentCardNumber);
        int paymentCardNumber = Integer.parseInt(stringpaymentCardNumber);
//        System.out.println("CardNUM: " + paymentCardNumber);
        
        String errorMsgs1="";
        
        if (paymentMethod.isEmpty()){
            errorMsgs1+="Your Payment Method is not filled in. \n";
        }
        
        try {
            long now = System.currentTimeMillis();
            Date nowDate = new Date(now);
            if (expiryDate.before(nowDate)){
                errorMsgs1+="Your expiry Date has reached. You are no longer able to use this payment Method.\n";
            }
        }

        catch (IllegalArgumentException e) {
            System.out.println(e);
            errorMsgs1+="The expiry date is empty. Please fill in this section.\n";
        }
        

        if (stringCVC.length()<3){
            errorMsgs1+="Your CVC must be at least 3 numbers.\n";
        }
        if (stringCVC.isEmpty()){
            errorMsgs1+="Please fill in the CVC.\n";
        }
        
        
        
        if (stringpaymentCardNumber.length()!=9){
            errorMsgs1+="You have not added your card number properly. Your Card Number should have 9 numbers.\n";
        }
        if(stringpaymentCardNumber.isEmpty()){
            errorMsgs1 += "Please fill in the card number.\n";
        }
        
        System.out.println("Entered Before if");
        if (!errorMsgs1.isEmpty()) {
            session.setAttribute("errorMsgs1", errorMsgs1);
            request.getRequestDispatcher("paymentForm.jsp").include(request,response);
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
                
                
//                System.out.println("Entered");
                session.setAttribute("paymentMethod", paymentMethod);
                session.setAttribute("expiryDate", stringexpiryDate);
                session.setAttribute("cardNumber", paymentCardNumber);
                session.setAttribute("cvc", paymentCVC);
                request.getRequestDispatcher("shippingOrder.jsp").forward(request, response);
            } catch(SQLException e){
                System.out.println(e);
            }
        }
        
    }
}

