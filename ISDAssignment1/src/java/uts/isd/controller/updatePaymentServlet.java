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
public class updatePaymentServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Payment payment = (Payment) session.getAttribute("payment");
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        
        session.removeAttribute("invalidPaymentCVC");
        session.removeAttribute("invalidPaymentCardNumber");
        session.removeAttribute("invalidExpiryDate");
        session.removeAttribute("invalidPaymentMethod");
        
        //getting expiryDate string, and then converting to an integer.
        String stringexpiryDate = request.getParameter("stringexpiryDate");
        Date expiryDate = Date.valueOf(stringexpiryDate);
        //Getting the CVC as a string, and then converting it to an integer.
        String stringCVC = request.getParameter("stringCVC");
        int paymentCVC = Integer.parseInt(stringCVC);
        //getting paymentCardNumber string, and then converting to an integer.
        String stringpaymentCardNumber = request.getParameter("stringpaymentCardNumber");
        int paymentCardNumber = Integer.parseInt(stringpaymentCardNumber);
        
        String paymentMethod = request.getParameter("paymentMethod");
       
        
        if (paymentMethod.isEmpty()){
            session.setAttribute("invalidPaymentMethod", "Your Payment Method is not filled in.");
            request.getRequestDispatcher("updatePaymentForm.jsp").forward(request, response);
        }
        
        try {
            long now = System.currentTimeMillis();
            Date nowDate = new Date(now);
            if (expiryDate.before(nowDate)){
                session.setAttribute("invalidExpiryDate","Your expiry Date has reached.");
                request.getRequestDispatcher("updatePaymentForm.jsp").forward(request, response);
            }
        }
        catch (IllegalArgumentException e) {
            session.setAttribute("invalidExpiryDate","The expiry date is empty. Please fill in this section.");
            request.getRequestDispatcher("updatePaymentForm.jsp").forward(request, response);
            System.out.println(e);
        }
        
        
        if (stringCVC.length()<3){
            session.setAttribute("invalidPaymentCVC", "Your CVC must be at least 3 numbers. ");
            request.getRequestDispatcher("updatePaymentForm.jsp").forward(request, response);
        }
        if (stringpaymentCardNumber.length()!=9){
            session.setAttribute("invalidPaymentCardNumber", "You have not added your card number properly. Your Card Number should have 9 numbers.");
            request.getRequestDispatcher("updatePaymentForm.jsp").forward(request, response);
        }
        
        
        try {
            //update expiryDate, paymentCVC and PaymentCardNumber
            paymentDAO.updateCardNumber(payment.getPaymentID(), paymentCardNumber);
            paymentDAO.updateExpiryDate(payment.getPaymentID(), expiryDate);
            paymentDAO.updatePaymentCVC(payment.getPaymentID(), paymentCVC);
            paymentDAO.updatePaymentMethod(payment.getPaymentID(), paymentMethod);
            
            Payments payments = new Payments(paymentDAO.fetchPaymentsFromACustomer(user.getUserID()));
            session.setAttribute("payments", payments);
            
            request.getRequestDispatcher("userPaymentRecordList.jsp").forward(request, response);
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        
    }
    
}
