/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Product;
import uts.isd.model.dao.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author jijianlan
 */
public class deleteDeviceServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int productID = Integer.parseInt(request.getParameter("productID"));
        
        HttpSession session = request.getSession();
        ProductDAO productDAO = (ProductDAO) session.getAttribute("productDAO");
        
        try{
            productDAO.deleteProduct(productID);
            request.getRequestDispatcher("/ConnServlet").forward(request, response);
            response.sendRedirect("index.jsp");
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
}
