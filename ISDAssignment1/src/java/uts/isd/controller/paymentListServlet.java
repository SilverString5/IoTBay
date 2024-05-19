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
     
        ArrayList<Payment> paymentList = new ArrayList();
        //if the user is not signed in
        if(user == null){
            //redirect them to a warning page
            response.sendRedirect("./unregisteredWarning.jsp");
            
        } else {
            //declared the paymentdao variable from session.
            PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
            
            try {
                //sets the paymentList to function of fetching all the payments from a customer from DAO. parameter is the userID from the session.
                paymentList = paymentDAO.fetchPaymentsFromACustomer(user.getUserID());
                //If there is nothing in paymentList, system will print out error messages. Used for debugging purposes.
                if (paymentList != null){
                    System.out.println("paymentList is null");
                }
                //to reset the payment filter error if page is reloaded.
                session.setAttribute("paymentFilterError", null);
                
                //Refresh the payment records currently stored in the session
                session.setAttribute("paymentList", paymentList);
                //'redirect' to the payment List page
                request.getRequestDispatcher("paymentsList.jsp").forward(request, response);
            
            
            } catch(SQLException e) {
                System.out.println(e);
            }
        }
        
        
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //session information
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        //declaring the reset button session parameter.
        String resetButton = request.getParameter("resetButton");
        
        
        
        
        //Declaring a string and int variable for card number 
        //String cardnumber = request.getParameter("cardnumber");
        //int paymentCardNumber = Integer.parseInt(cardnumber);
        
        ArrayList<Payment> paymentList;
        
        //if the shipment ID or date input box is empty, an error message should appear asking to fill in the information
        if((request.getParameter("paymentID").equals("") || request.getParameter("cardnumber").equals(""))){
            System.out.println("pass");
            session.setAttribute("paymentFilterError", "Please fill in both the payment ID and card number");
            request.getRequestDispatcher("paymentsList.jsp").forward(request, response);
            
        } else {
            //prevent the error messages from the last time user filter the list
            session.removeAttribute("paymentFilterError");
            
            //Declaring a string and an int variable for paymentID.
            String paymentIDstring = request.getParameter("paymentID");
            int paymentID = Integer.parseInt(paymentIDstring);
            
            //declaring the card number parameter from the session and making another int variable for it.
            String cardnumber = request.getParameter("cardnumber");
            int paymentCardNumber = Integer.parseInt(cardnumber);
            
            
            PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
            
            
            try {
                //fetching the payment records filtered by the paymentID and card number.
                paymentList = paymentDAO.fetchPaymentByFilter(user.getUserID(), paymentID, paymentCardNumber);
                
                //updating the paymentList values to be from the "fetchPaymentByFilter" function.
                session.setAttribute("paymentList", paymentList);
                //setting the paymentID variable.
                session.setAttribute("paymentID", paymentID);
                
                //'redirect' user back to the payment List web page.
                request.getRequestDispatcher("paymentsList.jsp").forward(request, response);

            } catch(SQLException e) {
                System.out.println(e);
            }
            
            
            //if the reset button was submitted
            if(resetButton != null){
                
                //set the payments from the current session.
                try {
                    System.out.println("pass");
                    //If the user resets the search, site wll load with all the customer payments again without the filter.
                    paymentList = paymentDAO.fetchPaymentsFromACustomer(user.getUserID());
                    //setting the paymentList session to equal to fetchPaymentsFromACustomer.
                    session.setAttribute("paymentList", paymentList);
                    //redirecting back to the paymentsList paga and forwarding the session variables to it.
                    request.getRequestDispatcher("paymentsList.jsp").forward(request, response);


                } catch(SQLException e) {
                    System.out.println(e);
                }
            }
            
            
        }
        
        
        
    }
}
