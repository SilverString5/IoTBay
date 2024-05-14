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

import uts.isd.model.User;
import uts.isd.model.dao.UserDAO;
import uts.isd.model.dao.OrderDAO;
import uts.isd.model.dao.ShipmentDAO;

/**
 *
 * @author notba
 */
public class DeleteRegistrationServlet extends HttpServlet {
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDAO userDAO = (UserDAO)session.getAttribute("userDAO");
        OrderDAO orderDAO = (OrderDAO)session.getAttribute("orderDAO");
        ShipmentDAO shipmentDAO = (ShipmentDAO)session.getAttribute("shipmentDAO");
        User user = (User)session.getAttribute("user");
        if (user!=null){
        try{
        orderDAO.changeOrderStatus(user.getUserID());
        shipmentDAO.updateShipmentStatus(user.getUserID());
        userDAO.delete(user.getUserID());
        request.getRequestDispatcher("logout.jsp").include(request, response);
        }
        
       catch (SQLException e){
        System.out.println(e);
        }
                        }
        request.getRequestDispatcher("manageRegistration.jsp").include(request,response);
        

}



    
}
