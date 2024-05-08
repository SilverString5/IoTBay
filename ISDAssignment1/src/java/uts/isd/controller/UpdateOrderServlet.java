package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
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
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;
import uts.isd.model.dao.ProductDAO;

/**
 *
 * @author pyaephyozaw
 */
public class UpdateOrderServlet extends HttpServlet{
    private OrderDAO orderDAO; 
    private ProductDAO productDAO;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        orderDAO = (OrderDAO)session.getAttribute("orderDAO");
        productDAO = (ProductDAO)session.getAttribute("productDAO");
 //       System.out.println("It reached");
        HashMap<Integer, Integer> quantityMap = (HashMap<Integer, Integer>)session.getAttribute("quantityMap");
//        int orderID = Integer.parseInt(request.getParameter("orderID")); //check null
        if(request.getParameter("saved") == null){
            int productID = Integer.parseInt(request.getParameter("productID"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        if(quantityMap.containsKey(productID)){
            quantityMap.put(productID, productQuantity);
        }
        request.getRequestDispatcher("updateorder.jsp").include(request, response);
        }
//        int productID = Integer.parseInt(request.getParameter("productID"));
//        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
//        if(quantityMap.containsKey(productID)){
//            quantityMap.put(productID, productQuantity);
//        }
       
//       ArrayList<Integer> updatedQuantityList = new ArrayList();
//       for(Map.Entry<Integer, Integer> entry : quantityMap.entrySet()){
//           Integer quantity = entry.getValue();
//           updatedQuantityList.add(quantity);
//       }
//       ArrayList<Product> productList = (ArrayList<Product>)session.getAttribute("productList");
       
//       double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
//       double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        else if(request.getParameter("saved") != null && request.getParameter("saved").equals("yes")){
            ArrayList<Integer> updatedQuantityList = new ArrayList();
            for(Map.Entry<Integer, Integer> entry : quantityMap.entrySet()){
                Integer quantity = entry.getValue();
                updatedQuantityList.add(quantity);
            }
       
//            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int orderID = (int)session.getAttribute("orderID");
            double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
            HashMap<Integer, Integer> original = (HashMap<Integer, Integer>)session.getAttribute("originalQuantity");
            ArrayList<Product> productList = (ArrayList<Product>)session.getAttribute("productList");
//            ArrayList<Integer> quantityList = new ArrayList();
            HashMap<Integer, Integer> newMap = new HashMap();
            for(Product product : productList){
                int productid = product.getProductID();
                int originalQuan = original.get(productid);
                int updatedQuan = quantityMap.get(productid);
                System.out.println("Original " + originalQuan);
                System.out.println("Updated " + updatedQuan);
                if(updatedQuan > originalQuan){
                    
                    int newStock = updatedQuan - originalQuan;
//                    quantityList.add(newStock);
                    newMap.put(productid, newStock);
                }else if(updatedQuan < originalQuan){
                    int newStock = updatedQuan - originalQuan;
//                    quantityList.add(newStock);
                    newMap.put(productid, newStock);
                }else{
//                    quantityList.add(originalQuan);
//                    newMap.put(productid, originalQuan);
                }
            }
            try{
                orderDAO.updateOrder(orderID, totalAmount, updatedQuantityList, productList);
                HashMap<Integer, Integer> products = productDAO.fetchStock();
                for(Map.Entry<Integer, Integer> entry : newMap.entrySet()){
                    Integer id = entry.getKey();
                    Integer quantity = entry.getValue();
                    if(products.containsKey(id)){
                        System.out.println("ID: " + id + " Quantity: " + quantity);
                        int newStock = products.get(id) - quantity;
                        System.out.println("New: " + newStock);
                        productDAO.updateStock(id, newStock);
                    }
                }               
            }catch(SQLException e){
                System.out.print(e);
            }
            request.getRequestDispatcher("OrderHistoryServlet").forward(request, response);
       }
       
       
//       request.getRequestDispatcher("updateorder.jsp").include(request, response);
       
    }
}
