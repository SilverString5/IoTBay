/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.User;
import uts.isd.model.dao.UserDAO;

/**
 *
 * @author pyaephyozaw
 */
public class ShoppingCartServlet extends HttpServlet{
    
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession();
          String productIDStr = request.getParameter("proID");
          int productID = Integer.parseInt(productIDStr);
          HashMap<Integer, Integer> shoppingCart = new HashMap<>();
          if(session.getAttribute("shoppingCart") != null){
              shoppingCart = (HashMap<Integer, Integer>)session.getAttribute("shoppingCart");
              if(!(shoppingCart.containsKey(productID))){
                shoppingCart.put(productID, 1);
              }else{
                int newQuan = shoppingCart.get(productID);
                newQuan++;
                shoppingCart.put(productID, newQuan);
              }    
//              session.setAttribute("shoppingCart", shoppingCart);
          }else{
              shoppingCart.put(productID, 1);
              session.setAttribute("shoppingCart", shoppingCart);
          }
          request.getRequestDispatcher("index.jsp").include(request, response);
        
    }
}