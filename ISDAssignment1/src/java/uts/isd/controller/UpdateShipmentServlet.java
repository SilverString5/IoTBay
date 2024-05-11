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
public class UpdateShipmentServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Gets all the necessary data from the session
        HttpSession session = request.getSession();        
        User user = (User) session.getAttribute("user");
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        ShipmentDAO shipmentDAO = (ShipmentDAO) session.getAttribute("shipmentDAO");
        
        //removes the error messages from the last time user submits the form
        session.removeAttribute("invalidUpdateAddress");
        session.removeAttribute("invalidUpdateAddressArray");

        //Gets the user input from the form
        String streetAddress = request.getParameter("streetAddress");
        String deliveryMethod = request.getParameter("deliveryMethod");
        
        //if the street address is empty
        if(streetAddress.isEmpty()){
            session.setAttribute("invalidUpdateAddress", "Please fill in a street address");
            request.getRequestDispatcher("updateShipmentForm.jsp").forward(request, response);
        }
        
        //if the street address is less than 19 characters (if so, it is not considered as a valid address)
        else if(streetAddress.length() <= 19){
             session.setAttribute("invalidUpdateAddress", "Please fill in a valid street address");
            request.getRequestDispatcher("updateShipmentForm.jsp").forward(request, response);
        }
        //if the streetAddress is not in a valid format (does not contains a state and postcode)
        else if(!checkAddressFormat(streetAddress).isEmpty()){
            session.setAttribute("invalidUpdateAddressArray", checkAddressFormat(streetAddress));
            request.getRequestDispatcher("updateShipmentForm.jsp").forward(request, response);
        }
        //If the address is valid
        else {
            
            try {
                //update shipment address and method of the record in the database
                shipmentDAO.updateShipmentAddress(shipment.getShipmentID(), streetAddress);
                shipmentDAO.updateShipmentMethod(shipment.getShipmentID(), deliveryMethod);
            
                //updates the shipment records in the session
                //if the user access the shipment history at any time, information is updated
                Shipments shipments = new Shipments(shipmentDAO.fetchShipmentFromACustomer(user.getUserID()));
                session.setAttribute("shipments", shipments);
            
                //'redirected' to the shipment history web page
                request.getRequestDispatcher("shipmentHistory.jsp").forward(request, response);

            } catch(SQLException e) {
                System.out.println(e);
            }
        }
        
    }
    
    //Method that checks if the street address contains a state and postcode
    public ArrayList<String> checkAddressFormat(String streetAddress){
        
        //stores all the words in an array
        String[] streetAddressArray = streetAddress.split(" ");
        ArrayList<String> errorMsg = new ArrayList<>();
        
        int amountOfState = 0;
        
        //for each word in the street address
        for(String word : streetAddressArray){
            
            //checks if the word is a state
            if(isAState(word) && amountOfState <= 1){
                amountOfState++;
            }
            
        }
        
        //if there is no state or there is more than one state added to the address
        if(amountOfState == 0 || amountOfState > 1){
            errorMsg.add("Please add or remove a state in your updated address");
        }
        
        //Checks if there is a postcode at the end of the address
        try {
            Integer.parseInt(streetAddressArray[streetAddressArray.length - 1]);
            
            //if the postcode is not 4 digit long
            if(streetAddressArray[streetAddressArray.length - 1].length() != 4){
                errorMsg.add("Invalid postcode");
            }
        
        //if there is no postcode, ask users to add a postcode
        } catch (NumberFormatException e) {
            errorMsg.add("Please add a postcode");
        }
        
        return errorMsg;
    }
    
    //Method that checks if the word is a state
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
