/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import uts.isd.model.*;
import uts.isd.model.dao.*;
import uts.isd.controller.*;

/**
 *
 * @author pyaephyozaw
 */
public class RemoveCartItemServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>)session.getAttribute("shoppingCart");
            String function = request.getParameter("function");
            if(function.equals("RemoveAnItem")){
                int productID = Integer.parseInt(request.getParameter("productID"));
                if(shoppingCart.containsKey(productID)){
                    shoppingCart.remove(productID);
                }
            }else if(function.equals("ClearCart")){
                shoppingCart.clear();
            }
            
            request.getRequestDispatcher("DisplayCartServlet").forward(request, response);
        }
}