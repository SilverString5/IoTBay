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
public class updateDeviceFormServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //getting the productID from request object
        int productID = Integer.parseInt(request.getParameter("productID"));
        
        HttpSession session = request.getSession();
        ProductDAO productDAO = (ProductDAO) session.getAttribute("productDAO");
        
        try{
            //set request object with attribute product of that specific ID
            //So the product data can be used in productForm, and also indicate it should perform an updating action.
            Product existingProduct = productDAO.getProduct(productID);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
            request.setAttribute("device", existingProduct);
            dispatcher.forward(request, response);
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
}
