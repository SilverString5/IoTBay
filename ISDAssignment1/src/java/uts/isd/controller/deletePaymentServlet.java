/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

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
/**
 *
 * @author sienn
 */



public class deletePaymentServlet extends HttpServlet{
    //draft
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        
        HttpSession session = request.getSession();
        
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        
        try{
            //calling delete Payment function from paymentDAO.
            paymentDAO.deletePayment(paymentID);
            
            //gets all the oayment records made by the user that is stored in the session.
            Payments payments = (Payments) session.getAttribute("payments");
            
            //delete the payment record currently stored in the session.
            for (Payment currentPayment : payments.getListOfCustomerPayments()) {
            
                //if the payment record in the loop matches the payment ID
                if(currentPayment.getPaymentID() == paymentID){
                //remove the payment record in the session.
                payments.getListOfCustomerPayments().remove(currentPayment);
                break;
                }
            }
            
            
            request.getRequestDispatcher("/ConnServlet").forward(request, response);
            response.sendRedirect("userPaymentRecordList.jsp");
        }
        
        
        catch(SQLException e){
            System.out.println(e);
        }

    }
}
