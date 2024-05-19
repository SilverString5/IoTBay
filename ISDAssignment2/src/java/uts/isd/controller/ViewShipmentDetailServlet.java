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
public class ViewShipmentDetailServlet extends HttpServlet{    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Gets all the necessary data from the session
        HttpSession session = request.getSession();
        String shipmentID = request.getParameter("shippingID");
        User user = (User) session.getAttribute("user");
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        
        if(shipmentID == null){
            response.sendRedirect("./unauthorisedAccessWarning.jsp");
        }
        
        //if the user is not signed in
        else if(user == null || user.getUserType().contains("C")){
            
            try {
                
                if(user == null){
                    Shipment shipment = shipmentDAO.fetchShipmentByID(Integer.parseInt(shipmentID));
                    //set this shipment record in the session 
                    // in order for the shipmentDetail.jsp to access details about this specific shipment record
                    session.setAttribute("shipment", shipment);

                    //'redirect' the user to the shipmentDetail page
                    request.getRequestDispatcher("shipmentDetail.jsp").forward(request, response);
                    
                } else {
                    //find the shipment by the user ID
                    Shipment shipment = shipmentDAO.fetchShipmentByFilter(user.getUserID(), Integer.parseInt(shipmentID));
                    //set this shipment record in the session 
                    // in order for the shipmentDetail.jsp to access details about this specific shipment record
                    session.setAttribute("shipment", shipment);

                    //'redirect' the user to the shipmentDetail page
                    request.getRequestDispatcher("shipmentDetail.jsp").forward(request, response);
                }
                
                
                
            
            } catch(SQLException e) {
                System.out.println(e);
            }
            
        } else {
            
            //redirect them to a warning page
            response.sendRedirect("./unregisteredWarning.jsp");
            
            
        }
 
        
    }   
    
}
