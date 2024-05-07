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
public class createShipmentServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        
        String streetAddress = request.getParameter("streetAddress");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        
        
        
        String shipmentAddress = streetAddress + ", " + city + " " + state + " " + postcode;
        String shipmentMethod = request.getParameter("shipmentMethod");
        String shipmentStatus = "Pending";
        Date shipmentDate = new Date();
       
        
        
        
        try {
            shipmentDAO.createShipment(user.getUserID(), shipmentAddress , shipmentMethod, shipmentStatus, shipmentDate);
           
            
            
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        
        
    }
    
}
