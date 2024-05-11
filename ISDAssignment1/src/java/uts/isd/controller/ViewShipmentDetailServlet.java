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
    
     private DBConnector db;
    private Connection conn;
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        int shipmentID = Integer.parseInt(request.getParameter("shippingID"));
        
        User user = (User) session.getAttribute("user");
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        
        if(user == null){
            System.out.println("pass null user");
            response.sendRedirect("./unregisteredWarning.jsp");
        }
        else {
        
        try {
            Shipment shipment = shipmentDAO.fetchShipmentByFilter(user.getUserID(), shipmentID);
            session.setAttribute("shipment", shipment);
            
            request.getRequestDispatcher("shipmentDetail.jsp").forward(request, response);
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        }
 
        
    }
    
    
    
    
}
