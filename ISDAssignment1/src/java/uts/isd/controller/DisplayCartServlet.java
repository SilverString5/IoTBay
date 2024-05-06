/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Product;
import uts.isd.model.User;
import uts.isd.model.dao.ProductDAO;
import uts.isd.model.dao.UserDAO;
/**
 *
 * @author pyaephyozaw
 */
public class DisplayCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession();
          
          HashMap<Integer, Integer> shoppingCart;
          ArrayList<Product> productList = new ArrayList();
          ProductDAO productDAO = (ProductDAO)session.getAttribute("productDAO");
          Product product = null;
          try {
            if(session.getAttribute("shoppingCart") != null) {
               shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart");
  //             shoppingCart.forEach((key, value) -> {
  //                 product = productDAO.getProduct(key);
  //             });
              for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
                  Integer productID = entry.getKey();
                  product = productDAO.getProduct(productID);
                  productList.add(product);
              }
            }
          } catch (SQLException e){
              System.out.print(e);
          }
          session.setAttribute("cartList", productList);
          request.getRequestDispatcher("shoppingcart.jsp").include(request, response);       
    }
}
