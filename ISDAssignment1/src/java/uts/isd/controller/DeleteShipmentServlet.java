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
public class DeleteShipmentServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        
        
        
        try {
            shipmentDAO.deleteShipment(shipment.getShipmentID());
            
            Shipments shipments = (Shipments) session.getAttribute("shipments");
            
            for(Shipment currentShipment : shipments.getListOfCustomerShipments()){
                if(currentShipment.getShipmentID() == shipment.getShipmentID()){
                    shipments.getListOfCustomerShipments().remove(currentShipment);
                    break;
                }
            }
            
            session.removeAttribute("shipment");
            
            
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        
        
    }
    
}
