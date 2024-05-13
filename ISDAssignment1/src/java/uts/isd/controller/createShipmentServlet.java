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
import java.util.*;


/**
 *
 * @author lorinchanel
 */
public class createShipmentServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Gets all the necessary data from the session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        
        //'resets' the error message to prevent warnings from the last time user submits the form
        session.removeAttribute("errorMessage");
        
        //Gets all the input from the updateFormShipment web page
        String streetAddress = request.getParameter("streetAddress");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        String shipmentMethod = request.getParameter("shipmentMethod");
        
        ArrayList<String> errorMsg = new ArrayList<>();
        
        //Checks if the streetAddress is null   
        if(!streetAddress.equals("")){
            
            //To be a valid streetAddress, it needs to be longer than 15 characters and must contain a house number
            if(streetAddress.length() <= 15 && hasHouseNumber(streetAddress) == false){
                errorMsg.add("Invalid street address");
            }
        
        //If the streetAddress is null/empty, web page should display a message asking to add a streetAddress
        } else {
            errorMsg.add("Please add your street address");
        }
        
        //If the suburb input box is null/empty
        if(city.equals("")){
            errorMsg.add("Please add you suburb name");
        }
        
        //If none of the states were selected
        if(state.equals("")){
            errorMsg.add("Please select one of the following states");
        }
        
        //If postcode input is empty
        if(postcode != null){
            
            //Checks if its not a word or character
            try {
                Integer.parseInt(postcode);
            
                //Checks if the postcode is 4 digit long
                if(postcode.length() != 4){
                    errorMsg.add("Invalid postcode");
                }
            
            //If the input was a word or character, error message would display asking a valid postcode
            } catch (NumberFormatException e) {
                errorMsg.add("Please add a valid postcode");
            }
        
        
        } else {
            errorMsg.add("Please add a postcode");
        }
        
        //If no shipment method option was selected
        if(shipmentMethod.equals("")) {
            errorMsg.add("Please select a shipment method");
        }

        //If there are no error with the input user place in
        if(errorMsg.isEmpty()){
            
            String shipmentAddress = streetAddress + ", " + city + " " + state + " " + postcode;
            
            String shipmentStatus = "Pending";
            Date shipmentDate = new Date();
            
            try {
                //Creates a shipment record and adds to the database
                shipmentDAO.createShipment(user.getUserID(), shipmentAddress , shipmentMethod, shipmentStatus, shipmentDate);
                
                //Fetch all the shipments made from the customer 
                //Therefore when user is 'redirected' to the shipment history web page, their shipment records are updated
                Shipments shipments = new Shipments(shipmentDAO.fetchShipmentFromACustomer(user.getUserID()));
                session.setAttribute("shipments", shipments);
                request.setAttribute("shipmentAddress", shipmentAddress);  //code added
                request.setAttribute("shipmentMethod", shipmentMethod);     //code added
                request.getRequestDispatcher("orderSummary.jsp").forward(request, response);



            } catch(SQLException e) {
                System.out.println(e);
            }
        
        //If there are error with the user input, web page should 'refresh' and the error messages should be displayed 
        } else {
            session.setAttribute("errorMessage", errorMsg);
            request.getRequestDispatcher("shippingOrder.jsp").forward(request, response);
        }
        
    }
    
    //Method to check if there is a house number in the street address
    public boolean hasHouseNumber(String streetAddress){
        
        //Stores all the words from the street address in an array
        String[] streetAddressArray = streetAddress.split(" ");
        
        //Checks if one of the words is a number
        for(String word : streetAddressArray){
            try {
                Integer.parseInt(word);
                
                //return true as one of the words in the street address is a house number
                return true;
            } catch (NumberFormatException e) { }
        }
        
        return false;
        
        
        
    }
    
   
    
}
