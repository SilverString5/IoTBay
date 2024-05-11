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
          OrderDAO orderDAO = (OrderDAO)session.getAttribute("orderDAO");
//          UserDAO userDAO = (UserDAO)session.getAttribute("userDAO");
          User user = (User)session.getAttribute("user");
          Product product = null;
          ArrayList<Integer> productIDList = new ArrayList();
//          int orderID = orderDAO.getOrderID(user.getUserID());
          try {
//            if(session.getAttribute("shoppingCart") != null) {
//                int orderID = orderDAO.getOrderID(user.getUserID());
//                HashMap<Integer, Integer> savedCart = orderDAO.getQuantity(orderID);
                shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart");
//  //             shoppingCart.forEach((key, value) -> {
//  //                 product = productDAO.getProduct(key);
//  //             });
////                System.out.println("Hello");
//                if(!savedCart.isEmpty()){
//                    System.out.println("Hello");
////                    for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
////                        Integer productID = entry.getKey();
////                        Integer quantity = entry.getValue();
////                        for(Map.Entry<Integer, Integer> en : savedCart.entrySet()){
////                            if(en.getKey().equals(productID)){
////                                shoppingCart.put(productID, en.getValue()+quantity);
////                            }
////                        }                   
//////                    product = productDAO.getProduct(productID);
//////                    productList.add(product);
////                    }
//                    for(Map.Entry<Integer, Integer> entry : savedCart.entrySet()){
//                        Integer productID = entry.getKey();
//                        Integer quantity = entry.getValue();
//                        if(shoppingCart.containsKey(productID)){
//                            shoppingCart.put(productID, quantity+shoppingCart.get(productID));
//                        }else{
//                            shoppingCart.put(productID, quantity);
//                        }
//                    }
//                }
//                for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
//                    Integer productID = entry.getKey();
//                    product = productDAO.getProduct(productID);
//                    productList.add(product);
//                }
//            }else{
//                int orderID = orderDAO.getOrderID(user.getUserID());
//                productIDList = orderDAO.getProductIDList(orderID);
//                for (int id : productIDList){
//                    product = productDAO.getProduct(id);
//                    productList.add(product);
//                }
//                shoppingCart = orderDAO.getQuantity(orderID);
//                session.setAttribute("shoppingCart", shoppingCart);
//            }
                if(shoppingCart != null){
                    for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
                    Integer productID = entry.getKey();
                    product = productDAO.getProduct(productID);
                    productList.add(product);
                }
            }
//                for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
//                    Integer productID = entry.getKey();
//                    product = productDAO.getProduct(productID);
//                    productList.add(product);
//                }
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