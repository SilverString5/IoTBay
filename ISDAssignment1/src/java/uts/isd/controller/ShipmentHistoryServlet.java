/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ShipmentDAO;
import uts.isd.model.*;
import uts.isd.model.User;
import uts.isd.model.Shipments;
import uts.isd.model.dao.UserDAO;
import java.util.*;

/**
 *
 * @author lorinchanel
 */
public class ShipmentHistoryServlet extends HttpServlet{    
    
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
            
            ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        
            try {
                //resets the error messages from the last time user access the form
                session.setAttribute("shipmentFilterError", null);
                
                //Refresh the shipment records currently stored in the session
                Shipments shipments = new Shipments(shipmentDAO.fetchShipmentFromACustomer(user.getUserID()));
                session.setAttribute("shipments", shipments);
            
                //'redirect' to the shipment history page
                request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
            } catch(SQLException e) {
                System.out.println(e);
            }
        }
        
        
    }
    
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Gets all the necessary data from the session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        //checks if the reset button was pressed
        String resetButton = request.getParameter("resetButton");
        
        //Gets the date submitted by the user
        String date = request.getParameter("date");
        
        //if the shipment ID or date input box is empty, an error message should appear asking to fill in the information
        if((request.getParameter("shipmentID").equals("") || date.length()==0)){
            
            session.setAttribute("shipmentFilterError", "Please fill in both shipment ID and date");
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
        } else {
            //prevent the error messages from the last time user filter the list
            session.setAttribute("shipmentFilterError", null);
            String shipmentID = request.getParameter("shipmentID");
 
            ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
            
            
            try {
                //fetch the shipment records that has the same shipment ID and shipment date
                Shipments shipments = new Shipments(shipmentDAO.fetchShipmentByFilter(user.getUserID(), Integer.parseInt(shipmentID), date));
                
                session.setAttribute("shipments", shipments);
                
                //'redirect' user back to the shipment history web page
                request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);

            } catch(SQLException e) {
                System.out.println(e);
            }
            
            
            //if the reset button was submitted
            if(resetButton != null){
                
                //set the shipment records in the current session to contain all shipments made rather than one created from the filter
                try {
                    Shipments shipments = new Shipments(shipmentDAO.fetchShipmentFromACustomer(user.getUserID()));
                    session.setAttribute("shipments", shipments);
                    request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);


                } catch(SQLException e) {
                    System.out.println(e);
                }
            }
            
            
        }
        
        
        
        
        
    }
        
}

