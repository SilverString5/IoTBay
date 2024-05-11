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
        
        session.removeAttribute("errorMessage");
        
        String streetAddress = request.getParameter("streetAddress");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        String shipmentMethod = request.getParameter("shipmentMethod");
        
        ArrayList<String> errorMsg = new ArrayList<>();
        
        //streetAddress is not less than 15 characters and has a number in it
        
        System.out.println("pass");
        
        if(!streetAddress.equals("")){
            
            if(streetAddress.length() <= 15 && hasHouseNumber(streetAddress) == false){
                errorMsg.add("Invalid street address");
            }
        }
        else {
            errorMsg.add("Please add your street address");
        }
        
        if(city.equals("")){
            System.out.println("city");
            errorMsg.add("Please add you suburb name");
        }
        
        if(state.equals("")){
            errorMsg.add("Please select one of the following states");
        }
        
        if(postcode != null){
            try {
                Integer.parseInt(postcode);
            
                if(postcode.length() != 4){
                    errorMsg.add("Invalid postcode");
                }
            
            } catch (NumberFormatException e) {
                errorMsg.add("Please add a valid postcode");
            }
                
        } else {
            errorMsg.add("Please add a postcode");
        }
        
        if(shipmentMethod.equals("")) {
            errorMsg.add("Please select a shipment method");
        }

        
        if(errorMsg.isEmpty()){
            
            String shipmentAddress = streetAddress + ", " + city + " " + state + " " + postcode;
            
            String shipmentStatus = "Pending";
            Date shipmentDate = new Date();

            try {
                shipmentDAO.createShipment(user.getUserID(), shipmentAddress , shipmentMethod, shipmentStatus, shipmentDate);
                
                  Shipments shipments = new Shipments(shipmentDAO.fetchShipmentFromACustomer(user.getUserID()));
            
                  session.setAttribute("shipments", shipments);

                request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);



            } catch(SQLException e) {
                System.out.println(e);
            }
        
        
            
            
        } else {
            session.setAttribute("errorMessage", errorMsg);
            request.getRequestDispatcher("shippingOrder.jsp").forward(request, response);
        }
        
        
        
        
    }
    
    public boolean hasHouseNumber(String streetAddress){
        String[] streetAddressArray = streetAddress.split(" ");
        
        for(String word : streetAddressArray){
            try {
            
            Integer.parseInt(word);
            return true;
            
            } catch (NumberFormatException e) { 
                
                
                
            }
        }
        
        return false;
        
        
        
    }
    
   
    
}
