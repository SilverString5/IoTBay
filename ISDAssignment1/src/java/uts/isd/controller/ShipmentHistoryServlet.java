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
        
        //User user = (User) session.getAttribute("user");

        User user = new User();
        user.setUserID(8);
	user.setName("Ryuji Sakamoto");
	user.setEmail("SakamotoR@phantom.com");
	user.setPassword("Persona5");
	user.setPhone("0477777777");
        user.setAddress("15 Shujin St, Jefferson, SA, 4021");
        user.setuserDOB(new java.sql.Date(new Date(2002, 8, 29).getTime()));
        user.setGender("M");
	session.setAttribute("user", user);

        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        try {
                db = new DBConnector();
                conn = db.openConnection();
                shipmentDAO = new ShipmentDAO(conn);
                session.setAttribute("shipmentDAO", shipmentDAO);
            } catch (SQLException | ClassNotFoundException e) {
                // Handle exception
                e.printStackTrace();
            }
        
        try {
            System.out.println("pass");
            Shipments shipments = new Shipments(shipmentDAO.fetchShipmentFromACustomer(user.getUserID()));
            
            session.setAttribute("shipments", shipments);
            
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        
    }
    
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        
        String searchButton = request.getParameter("searchButton");
        
        if(searchButton != null) {
          String date = request.getParameter("date");
        if(request.getParameter("shipmentID").equals("") || date == null){
            session.setAttribute("shipmentFilterError", "Please fill in both shipment ID and date");
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
        } else {
            session.setAttribute("shipmentFilterError", null);
            String shipmentIDAsString = request.getParameter("shipmentID");
            int shipmentID = Integer.parseInt(shipmentIDAsString);
          
            
            
        
            ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
            try {
                    db = new DBConnector();
                    conn = db.openConnection();
                    shipmentDAO = new ShipmentDAO(conn);
                    session.setAttribute("shipmentDAO", shipmentDAO);
                } catch (SQLException | ClassNotFoundException e) {
                    // Handle exception
                    e.printStackTrace();
                }
            
            try {
            System.out.println("pass");
            Shipments shipments = new Shipments(shipmentDAO.fetchShipmentByFilter(user.getUserID(), shipmentID, date));
            
            session.setAttribute("shipments", shipments);
            
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        }
        }
        
        /*String viewButton = request.getParameter("viewButton");
        if(viewButton != null) {
            

            String shipmentIDRecord = request.getParameter("shipmentIDRecord");
            String shipmentAddressRecord = request.getParameter("shipmentAddressRecord");
            String shipmentMethodRecord = request.getParameter("shipmentMethodRecord");
            String shipmentStatusRecord = request.getParameter("shipmentStatusRecord");
            String shipmentDateRecord = request.getParameter("shipmentDateRecord");
            
            Shipment shipment = new Shipment(Integer.parseInt(shipmentIDRecord), user.getUserID(), shipmentAddressRecord, shipmentMethodRecord, shipmentStatusRecord, new Date(shipmentDateRecord));
            
            session.setAttribute("shipment", shipment);
            
            
            request.getRequestDispatcher("shipmentDetail.jsp").forward(request, response);
            
            
            
        }*/
        
        /*
        String shipmentIDAsString = request.getParameter("shipmentID");
        int shipmentID = Integer.parseInt(shipmentIDAsString);
        String date = request.getParameter("date");
        
        User user = (User) session.getAttribute("user");
        
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        if (shipmentDAO == null) {
            // Initialize ShipmentDAO
            try {
                db = new DBConnector();
                conn = db.openConnection();
                shipmentDAO = new ShipmentDAO(conn);
                session.setAttribute("shipmentDAO", shipmentDAO);
            } catch (SQLException | ClassNotFoundException e) {
                // Handle exception
                e.printStackTrace();
            }
        }
        
        try {
            System.out.println("pass");
            Shipments shipments = new Shipments(shipmentDAO.fetchShipmentByFilter(user.getUserID(), shipmentID, date));
            
            session.setAttribute("shipments", shipments);
            
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }*/
        
       
        
        
        
        
        
    }
        
    }

