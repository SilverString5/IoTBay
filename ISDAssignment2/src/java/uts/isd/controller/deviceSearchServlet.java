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
public class deviceSearchServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO productDAO = (ProductDAO) session.getAttribute("productDAO");
        ArrayList<Product> products = null;
        String searchName = request.getParameter("searchName");
        String searchType = request.getParameter("searchType");

        try{
            products = productDAO.fetchFilteredProducts(searchName, searchType);
            session.setAttribute("listDevice", products);
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }
    
}
