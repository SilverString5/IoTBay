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
import javax.servlet.http.*;
import uts.isd.model.*;
import uts.isd.model.dao.*;
import uts.isd.controller.*;

/**
 *
 * @author pyaephyozaw
 */
public class ViewOrderServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String function = request.getParameter("function");
        OrderDAO orderDAO = (OrderDAO)session.getAttribute("orderDAO");
        ProductDAO productDAO = (ProductDAO)session.getAttribute("productDAO");
        
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        ArrayList<Product> productList = new ArrayList();
        HashMap<Integer, Integer> map = new HashMap();
        HashMap<Integer, Integer> unchanged = new HashMap();
        try{
            map = orderDAO.getQuantity(orderID);
            unchanged = orderDAO.getQuantity(orderID);
            ArrayList<Integer> productIDList = orderDAO.getProductIDList(orderID);
            for (int id : productIDList){
                Product product = productDAO.getProduct(id);
                productList.add(product);
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        session.setAttribute("productList", productList);
        session.setAttribute("quantityMap", map);
        if(function.equals("View")){
            session.setAttribute("orderID", orderID);
            request.getRequestDispatcher("orderdetail.jsp").include(request, response);        
        }else if(function.equals("Update")){
//            session.setAttribute("orderID", orderID);
            session.setAttribute("orderID", orderID);
            session.setAttribute("originalQuantity", unchanged);
            request.getRequestDispatcher("updateorder.jsp").include(request, response);
        }else if(function.equals("Cancel")){
            try{
                orderDAO.cancelOrder(orderID);
                HashMap<Integer, Integer> products = productDAO.fetchStock();
                for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                    Integer id = entry.getKey();
                    Integer quantity = entry.getValue();
                    if(products.containsKey(id)){
                        int newStock = products.get(id) + quantity;
                        productDAO.updateStock(id, newStock);
                    }
                }
            }catch(SQLException e){
                System.out.print(e);
            }
//            request.setAttribute("")
            request.getRequestDispatcher("OrderHistoryServlet").forward(request, response);
        }
    }
       
}