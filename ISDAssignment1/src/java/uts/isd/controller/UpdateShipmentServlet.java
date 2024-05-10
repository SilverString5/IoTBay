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
public class UpdateShipmentServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("pass throughupdate");
        
        
        
        HttpSession session = request.getSession();
        
        session.removeAttribute("invalidUpdateAddress");
        session.removeAttribute("invalidUpdateAddressArray");
        
        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        
        
        
        String streetAddress = request.getParameter("streetAddress");
        /*String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");*/
        String deliveryMethod = request.getParameter("deliveryMethod");
        
        //String shipmentAddress = streetAddress + ", " + city + " " + state + " " + postcode;
        
        if(streetAddress.isEmpty()){
            session.setAttribute("invalidUpdateAddress", "Please fill in a street address");
            request.getRequestDispatcher("updateShipmentForm.jsp").forward(request, response);
        }
        else if(streetAddress.length() <= 19){
             session.setAttribute("invalidUpdateAddress", "Please fill in a valid street address");
            request.getRequestDispatcher("updateShipmentForm.jsp").forward(request, response);
        }
        else if(!checkAddressFormat(streetAddress).isEmpty()){
            session.setAttribute("invalidUpdateAddressArray", checkAddressFormat(streetAddress));
            request.getRequestDispatcher("updateShipmentForm.jsp").forward(request, response);
        }
        else {
            try {
            shipmentDAO.updateShipmentAddress(shipment.getShipmentID(), streetAddress);
            shipmentDAO.updateShipmentMethod(shipment.getShipmentID(), deliveryMethod);
            
            System.out.println("pass");
            
            Shipments shipments = new Shipments(shipmentDAO.fetchShipmentFromACustomer(user.getUserID()));
            
            session.setAttribute("shipments", shipments);
            
            request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);
            
            //
            
            
            
        } catch(SQLException e) {
            System.out.println(e);
        }
        }
        
        
        
    }
    
    public ArrayList<String> checkAddressFormat(String streetAddress){
        //check if there is a street in beginning
        String[] streetAddressArray = streetAddress.split(" ");
        System.out.println(streetAddressArray);
        ArrayList<String> errorMsg = new ArrayList<>();
        
        
        
        int amountOfState = 0;
        
        for(String word : streetAddressArray){
            
            if(isAState(word) && amountOfState <= 1){
                amountOfState++;
            }
            
        }
        
        if(amountOfState == 0 || amountOfState > 1){
            errorMsg.add("Please fix your state");
        }
        

        try {
            Integer.parseInt(streetAddressArray[streetAddressArray.length - 1]);
            
            if(streetAddressArray[streetAddressArray.length - 1].length() != 4){
                errorMsg.add("Invalid postcode");
            }
            
        } catch (NumberFormatException e) {
            errorMsg.add("Please add a postcode");
        }
        
        return errorMsg;
    }
    
    public boolean isAState(String word){
        
        String[] listOfState = {"NSW", "VIC", "QLD", "WA", "SA", "TAS", "ACT", "NT"};
        
        for(String state: listOfState){
            if(word.equalsIgnoreCase(state)){
                return true;
            }
        }
        
        
        return false;
        
    }
    
}
