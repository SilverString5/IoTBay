/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import uts.isd.controller.*;
import uts.isd.model.*;
import uts.isd.model.dao.*;

/**
 *
 * @author pyaephyozaw
 */
public class SearchOrderServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        OrderDAO orderDAO = (OrderDAO)session.getAttribute("orderDAO");
        
            String orderIdStr = request.getParameter("orderID");
            String orderDate = request.getParameter("orderDate");
            StringBuilder errorMsg = new StringBuilder();
            boolean validInput = true;
            if(orderIdStr == null || orderIdStr.trim().isEmpty()){
                validInput = false;
                errorMsg.append("Please fill in order number. ");
            }else if(!(orderIdStr.matches("[0-9]+"))){
                validInput = false;
                errorMsg.append("Order number cannot be non-numeric or negative. ");
            }
                       
            if(orderDate == null || orderDate.trim().isEmpty()){  //check the date format matches (YYYY-MM-DD)
                validInput = false;
                errorMsg.append("Please fill in order date. ");
            }else if(!(orderDate.matches("\\d{4}-\\d{2}-\\d{2}"))){
                validInput = false;
                errorMsg.append("Invalid date format. Use this format:(YYYY-MM-DD).");
            }
            
            if(!validInput){
                request.setAttribute("errorMessage", errorMsg.toString());
                request.getRequestDispatcher("orders.jsp").forward(request, response);
                return;
            }else{
                int orderID = Integer.parseInt(orderIdStr);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date sqlOrderDate = null;
                try {
                    Date date = df.parse(orderDate);
                    sqlOrderDate = new java.sql.Date(date.getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(SearchOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                try{
                    Order order = orderDAO.searchOrder(orderID, sqlOrderDate);
                    if(order != null){
                        request.setAttribute("searchedOrder", order);
                    }

            }catch(SQLException e){
                System.out.print(e);
            }
        }
            
        request.getRequestDispatcher("orders.jsp").include(request, response);
    }
    
}