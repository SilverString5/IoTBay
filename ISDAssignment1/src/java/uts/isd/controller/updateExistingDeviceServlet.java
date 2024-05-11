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
        
        //get the product data from request object 
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productType = request.getParameter("productType");
        String productDetails = request.getParameter("productDetails");
        double productPrice = 0;
        int productInStock = 0;
        
        //create testing variables, check valid numbers, input length and empty input
        boolean isProductUnitPrice = isDouble(request.getParameter("productUnitPrice"));
        boolean isProductInStock = isInteger(request.getParameter("productInStock"));
        String str[] = productDetails.split(" ");
        String productUnitPriceTest = request.getParameter("productUnitPrice");
        String productInStockTest = request.getParameter("productInStock");
        
        //check empty name
        if(productName == null||productName.equals(""))
        {
            request.setAttribute("productNameErr", "Device name can't be empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        //check empty type
        if(productType == null || productType.equals(""))
        {
            request.setAttribute("productTypeErr", "Device type can't be empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        //check empty or 0 price
        if(productUnitPriceTest.equals("")||productUnitPriceTest == null||productUnitPriceTest.equals("0"))
        {
            request.setAttribute("productUnitPriceErr", "Device price can't be 0 or empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        //check if price input is not a number
        if(!isProductUnitPrice)
        {
            request.setAttribute("productUnitPriceErr", "Invalid device price input!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        //check empty details
        if(productDetails == null || productDetails.equals("")){
            
            request.setAttribute("productDetailsErr", "Device details can't be empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        //check invalid details length
        if(str.length < 3){
            request.setAttribute("productDetailsErr", "Device details should be at least 3 words!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        //check empty stock
        if(productInStockTest.equals("")||productInStockTest == null){
            request.setAttribute("productInStockErr", "Device stock can't be empty!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
        //check if stock input is not a number
        if(!isProductInStock){
            request.setAttribute("productInStockErr", "Invalid stock value input!");
            request.getRequestDispatcher("/updateDeviceFormServlet?productID=" + productID).include(request, response);
        }
  
        else{
            
            try{
                //Finish updating operation and redirect staff back to the list of devices
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
    
    //Number checking method for price
    private static boolean isDouble(String str) { 
      try {  
        Double.parseDouble(str);  
        return true;
      } catch(NumberFormatException e){  
        return false;  
      }  
    }
    
    //Number checking method for stock
    private static boolean isInteger(String str){
      try {  
        Integer.parseInt(str);  
        return true;
      } catch(NumberFormatException e){  
        return false;  
      }  
    }
}
