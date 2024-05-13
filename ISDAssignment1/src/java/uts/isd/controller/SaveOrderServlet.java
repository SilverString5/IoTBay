///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package uts.isd.controller;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import uts.isd.model.Product;
//import uts.isd.model.User;
//import uts.isd.model.dao.DBConnector;
//import uts.isd.model.dao.OrderDAO;
//import uts.isd.model.dao.ProductDAO;
//
///**
// *
// * @author pyaephyozaw
// */
//public class SaveOrderServlet extends HttpServlet{
//    private DBConnector db;
//    private OrderDAO orderDAO;
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//       HttpSession session = request.getSession();
//
//       orderDAO = (OrderDAO)session.getAttribute("orderDAO");
//       HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart");
//       ArrayList<Product> cartList = (ArrayList<Product>) session.getAttribute("cartList");
//       User user = (User) session.getAttribute("user");
//       ArrayList<Integer> quantityList = new ArrayList();
//       double totalAmount = (double)session.getAttribute("totalAmount");
//       for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
//                  Integer quantity = entry.getValue();
//                  quantityList.add(quantity); 
//        }
//       try {
//           orderDAO.CreateOrder(user.getUserID(), totalAmount, quantityList, cartList);
//       } catch(SQLException e) {
//           System.out.print(e);
//        }
//       request.getRequestDispatcher("shoppingcart.jsp").include(request, response); 
//    }
//}