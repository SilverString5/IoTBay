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
        
        int paymentID = Integer.parseInt(request.getParameter("PaymentID"));
        
        HttpSession session = request.getSession();
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        
        try{
            paymentDAO.deletePayment(paymentID);
            request.getRequestDispatcher("/ConnServlet").forward(request, response);
            response.sendRedirect("index.jsp");
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
}
