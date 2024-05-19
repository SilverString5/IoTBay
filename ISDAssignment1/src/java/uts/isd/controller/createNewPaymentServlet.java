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
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //getting required attributes from session.
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //getting payment attribute from session.
        Payment payment = (Payment) session.getAttribute("payment");
        //declaring the paymentDAO from the session.
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        

        //resetting the error messages so it doesn't show up when the page is reset.
        session.removeAttribute("errorMsgs1");
        
        
        
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
        //declaring the error messages.
        String errorMsgs1="";
        
        //Test case if the paymentMethod is empty/ not filled in.
        if (paymentMethod.isEmpty()){
            //adding to error message.
            errorMsgs1+="Your Payment Method is not filled in. \n";
        }
        
        try {
            //making a date for current date to compare to the date the user inserted into the form. If the expiry date is before the current date,
            //the appropriate error message will be made into the error Message string for the session.
            long now = System.currentTimeMillis();
            Date nowDate = new Date(now);
            if (expiryDate.before(nowDate)){
                errorMsgs1+="Your expiry Date has reached. You are no longer able to use this payment Method.\n";
            }
        }
        //catching the case if the expiry date is empty.
        catch (IllegalArgumentException e) {
            System.out.println(e);
            errorMsgs1+="The expiry date is empty. Please fill in this section.\n";
        }
        
        // catching the case if the CVC is at least 3 numbers.
        if (stringCVC.length()<3){
            errorMsgs1+="Your CVC must be at least 3 numbers.\n";
        }
        //catching the case if the CVC is empty/ not filled in.
        if (stringCVC.isEmpty()){
            errorMsgs1+="Please fill in the CVC.\n";
        }
        
        
        //testing if the payment card number length is not equal to 9. If that is the case, error message gets added to with approproate message.
        if (stringpaymentCardNumber.length()!=9){
            errorMsgs1+="You have not added your card number properly. Your Card Number should have 9 numbers.\n";
        }
        // testing case if the payment Card Number is empty.
        if(stringpaymentCardNumber.isEmpty()){
            errorMsgs1 += "Please fill in the card number.\n";
        }
        
        System.out.println("Entered Before if");
        
        //If there any error messages, it will set the attribute of error messages to the payments form, and will direct the user back to payment Form again, displaying the errors.
        if (!errorMsgs1.isEmpty()) {
            session.setAttribute("errorMsgs1", errorMsgs1);
            request.getRequestDispatcher("paymentForm.jsp").include(request,response);
        }
        else {
            try{
                if(user != null) {
                    //calling the paymentDAO exists function to check if the paymentCardNumber already exists. If it does, it will not add to create the payment, but will still redirect the values to the next page.
                    if (!paymentDAO.checkExists(paymentCardNumber)){
                         System.out.println("Entered");
                         //Creating a payment using the session attributes.
                        paymentDAO.createPayment(paymentMethod, expiryDate, paymentCVC, paymentCardNumber, user.getUserID());
                    }
                    Payments payments = new Payments(paymentDAO.fetchPaymentsFromACustomer(user.getUserID()));
                    session.setAttribute("payments", payments);
                    
                }
                else {
                    //if the user is null, it will create the a Payment with the UserID set to null, calling the required function from paymentDAO.
                    paymentDAO.createPaymentForAnonymousUser(paymentMethod, expiryDate, paymentCVC, paymentCardNumber);
                }
                
                
//                System.out.println("Entered");
//setting the required attributes to the session to pass on to the other page.
                session.setAttribute("paymentMethod", paymentMethod);
                session.setAttribute("expiryDate", stringexpiryDate);
                session.setAttribute("cardNumber", paymentCardNumber);
                session.setAttribute("cvc", paymentCVC);
                //sending the user to the shipping order page when they submit.
                request.getRequestDispatcher("shippingOrder.jsp").forward(request, response);
            } catch(SQLException e){
                System.out.println(e);
            }
        }
        
    }
}

