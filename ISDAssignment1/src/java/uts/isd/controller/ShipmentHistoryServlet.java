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
    
    private DBConnector db;
    private Connection conn;
    
    @Override
    public void init() {
        
    }
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        System.out.println("pass");
        
        User user = (User) session.getAttribute("user");
        System.out.println("hi" + user);
        
        if(user == null){
            System.out.println("pass null user");
            response.sendRedirect("./unregisteredWarning.jsp");
        }
        else {
        

        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        System.out.println(shipmentDAO);
        
        try {
            System.out.println("pass");
            session.setAttribute("shipmentFilterError", null);
            Shipments shipments = new Shipments(shipmentDAO.fetchShipmentFromACustomer(user.getUserID()));
            
            session.setAttribute("shipments", shipments);
            
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        }
        
        
    }
    
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        
        //String searchButton = request.getParameter("searchButton");
        String resetButton = request.getParameter("resetButton");
        System.out.println(resetButton);
        
        String date = request.getParameter("date");
        if((request.getParameter("shipmentID").equals("") || date.length()==0)){
            
            session.setAttribute("shipmentFilterError", "Please fill in both shipment ID and date");
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
        } else {
            session.setAttribute("shipmentFilterError", null);
            String shipmentIDAsString = request.getParameter("shipmentID");
            int shipmentID = Integer.parseInt(shipmentIDAsString);
          
            
            
        
            ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
            
            
            try {
            System.out.println("pass 3");
            Shipments shipments = new Shipments(shipmentDAO.fetchShipmentByFilter(user.getUserID(), shipmentID, date));
            
            session.setAttribute("shipments", shipments);
            
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }
            
            
        
        if(resetButton != null){
            try {
                System.out.println("pass 2");
            System.out.println("pass");
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

