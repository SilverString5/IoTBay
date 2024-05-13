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
import uts.isd.model.dao.OrderDAO;
import uts.isd.model.dao.ProductDAO;
/**
 *
 * @author pyaephyozaw
 */
public class DisplayCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          HttpSession session = request.getSession();
          
          HashMap<Integer, Integer> shoppingCart;
//          HashMap<Integer, Integer> shoppingCart = new HashMap();
          ArrayList<Product> productList = new ArrayList();
          ProductDAO productDAO = (ProductDAO)session.getAttribute("productDAO");
          OrderDAO orderDAO = (OrderDAO)session.getAttribute("orderDAO");
          User user = (User)session.getAttribute("user");
          Product product = null;
          ArrayList<Integer> productIDList = new ArrayList();
          try {
                shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart");  //key is productID, value is quantity of the product in the cart
                if(shoppingCart != null){
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
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        doGet(request, response);
    } 
}