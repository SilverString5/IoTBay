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
public class updateExistingDeviceServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO productDAO = (ProductDAO) session.getAttribute("productDAO");
        
        
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productType = request.getParameter("productType");
        String productDetails = request.getParameter("productDetails");
        double productPrice = 0;
        int productInStock = 0;
        
        String str[] = productDetails.split(" ");
        String productUnitPriceTest = request.getParameter("productUnitPrice");
        String productInStockTest = request.getParameter("productInStock");
        
        
        if(productName == null||productName.equals(""))
        {
            request.setAttribute("productNameErr", "Device name can't be empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        
        if(productType == null || productType.equals(""))
        {
            request.setAttribute("productTypeErr", "Device type can't be empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        
        if(productUnitPriceTest.equals("")||productUnitPriceTest == null||productUnitPriceTest.equals("0"))
        {
            
            request.setAttribute("productUnitPriceErr", "Device price can't be 0 or empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        
        if(productDetails == null || productDetails.equals("")){
            
            request.setAttribute("productDetailsErr", "Device details can't be empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        
        if(str.length < 3){
            request.setAttribute("productDetailsErr", "Device details should be at least 3 words!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        
        if(productInStockTest.equals("")||productInStockTest == null){
            request.setAttribute("productInStockErr", "Device stock can't be empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        
        else{
            
            try{
                productPrice = Double.parseDouble(request.getParameter("productUnitPrice"));
                productInStock = Integer.parseInt(request.getParameter("productInStock"));
                productDAO.updateProduct(productName, productType, productPrice, productDetails, productInStock, productID);
                request.getRequestDispatcher("/ConnServlet").forward(request, response);
                response.sendRedirect("index.jsp");

            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }
}
