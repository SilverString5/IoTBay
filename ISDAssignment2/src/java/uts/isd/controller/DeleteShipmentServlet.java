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
public class DeleteShipmentServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Gets all the necessary data from the session
        HttpSession session = request.getSession();
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        int shipmentID = Integer.parseInt(request.getParameter("shippingID"));
        
        try {
            
            //delete the shipment record from the database
            shipmentDAO.deleteShipment(shipmentID);
            
            //gets all the shipment records made by the user that is stored in the session
            Shipments shipments = (Shipments) session.getAttribute("shipments");
            
            //delete the shipment record currently stored in the session
            //checks every shipment record in the session made by the user
            for(Shipment currentShipment : shipments.getListOfCustomerShipments()){
                
                //if the shipment record in the loop matches the shipment ID
                if(currentShipment.getShipmentID() == shipmentID){
                    //remove the shipment record in the session
                    shipments.getListOfCustomerShipments().remove(currentShipment);
                    break;
                }
            }
            
            //remove the shipping ID which is stored in the session
            session.removeAttribute("shippingID");
            
            //'redirect' them back to the shipment history page
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        
        
    }
    
}
