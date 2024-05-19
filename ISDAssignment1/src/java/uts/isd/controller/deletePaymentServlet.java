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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author sienn
 */



public class deletePaymentServlet extends HttpServlet{
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //getting the paymentID parameter from selecting the delete function in paymentsList. getting it in both string and int.
        String paymentIDstring = request.getParameter("paymentID");
        int paymentID = Integer.parseInt(paymentIDstring);
        
        
        //getting the session.
        HttpSession session = request.getSession();
        
        //getting the paymentdao attribute from the session.
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        //initialising paymentList to be of a newArray List .
        ArrayList<Payment> paymentList = new ArrayList();
        
        try{
            //calling delete Payment function from paymentDAO; deleting the payment variable that has the selected paymentID from the session.
            paymentDAO.deletePayment(paymentID);
            
            //gets all the payment records made by the user that is stored in the session.
            paymentList = (ArrayList <Payment>) session.getAttribute("paymentList");

            //if the payment List is not equal to null, it will traverse through the payment list to find the paymentID that metches the selected payment ID from the session.
            //It will then deletd the payment variable from the session.
            if (paymentList != null) {
                paymentList.removeIf(payment -> payment.getPaymentID() == (paymentID));
                //stores the remove change into the paymentList.
                session.setAttribute("paymentList", paymentList);
            }
            
            //Redirects the results to paymentsList.

            response.sendRedirect("paymentsList.jsp");
        } catch(SQLException e){
            System.out.println(e);
        }

    }
}



