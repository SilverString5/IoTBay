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




public class paymentListServlet extends HttpServlet{
     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Gets all the necessary data from the session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //if the user is not signed in
        if(user == null){
            //redirect them to a warning page
            response.sendRedirect("./unregisteredWarning.jsp");
            
        } else {
            
            PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        
            try {
                //resets the error messages from the last time user access the form
                session.setAttribute("paymentFilterError", null);
                
                //Refresh the shipment records currently stored in the session
                Payments payments = new Payments(paymentDAO.fetchPaymentsFromACustomer(user.getUserID()));
                session.setAttribute("payments", payments);
            
                //'redirect' to the shipment history page
                request.getRequestDispatcher("UserPaymentRecordList.jsp").forward(request, response);
            
            
            } catch(SQLException e) {
                System.out.println(e);
            }
        }
        
        
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Payment payment = (Payment) session.getAttribute("payment");
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        
    }
}
