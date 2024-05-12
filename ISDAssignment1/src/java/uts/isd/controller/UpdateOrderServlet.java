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
import uts.isd.model.*;
import uts.isd.model.dao.*;

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

        HashMap<Integer, Integer> quantityMap = (HashMap<Integer, Integer>)session.getAttribute("quantityMap");

        if(request.getParameter("saved") == null){
//            System.out.println("Entered Here");
            String quantityStr = request.getParameter("productQuantity");
            if(quantityStr == null || quantityStr.trim().isEmpty() || !(quantityStr.matches("[1-9]+"))){ 
//                System.out.println("entered");
                request.setAttribute("quantityError", "Quantity can't be empty or non-numeric or negative numbers.");
                
            }else{               
                int productID = Integer.parseInt(request.getParameter("productID"));
                int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
                try{
                    int stockInDB = productDAO.fetchSingleStock(productID);  
                    if(stockInDB >= productQuantity){ //check if the quantity exceeds the actual stock in the database 
                        if(request.getParameter("cartUpdate") == null){ // this will be met when the user is updating the order
//                            System.out.println("ENTERED FIRST IF");
                            if(quantityMap.containsKey(productID)){
                                quantityMap.put(productID, productQuantity);
                            }
                        }else{     //this is for when the user is updating the shopping cart
                            HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>)session.getAttribute("shoppingCart");
                            if(shoppingCart.containsKey(productID)){
                                shoppingCart.put(productID, productQuantity);
                            }
                            request.getRequestDispatcher("DisplayCartServlet").forward(request, response);  
                            return;
                        }                      
                    }else{
                        request.setAttribute("insufficientStockError", "There is insufficient stock for the product. Please choose the lower quantity");
                    }
                }catch(SQLException e){
                    System.out.print(e);
                }              
            }
            if(request.getParameter("cartUpdate") == null){
                request.getRequestDispatcher("updateorder.jsp").include(request, response);
            }else{
                request.getRequestDispatcher("DisplayCartServlet").forward(request, response);
            }                  
        }else if(request.getParameter("saved") != null && request.getParameter("saved").equals("yes")){
            ArrayList<Integer> updatedQuantityList = new ArrayList();
            for(Map.Entry<Integer, Integer> entry : quantityMap.entrySet()){
                Integer quantity = entry.getValue();
                updatedQuantityList.add(quantity);
            }
       
            int orderID = (Integer)session.getAttribute("orderID");
            double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
            HashMap<Integer, Integer> original = (HashMap<Integer, Integer>)session.getAttribute("originalQuantity");
            ArrayList<Product> productList = (ArrayList<Product>)session.getAttribute("productList");

            HashMap<Integer, Integer> newMap = new HashMap();
            for(Product product : productList){
                int productid = product.getProductID();
                int originalQuan = original.get(productid);
                int updatedQuan = quantityMap.get(productid);
//                System.out.println("Original " + originalQuan);
//                System.out.println("Updated " + updatedQuan);
                
                if(updatedQuan > originalQuan){   
                    //when the updated quantity is larger than the original quantity, reduce the stock accordingly in the database
                    int newStock = updatedQuan - originalQuan;
                    newMap.put(productid, newStock);
                }else if(updatedQuan < originalQuan){ 
                    //when the updated quantity is smaller, it means the user reduces the quantity of a product in the order so increase the stock in the database
                    int newStock = updatedQuan - originalQuan;
                    newMap.put(productid, newStock);
                }else{
                    
                }
            }
            try{
                orderDAO.updateOrder(orderID, totalAmount, updatedQuantityList, productList);
                HashMap<Integer, Integer> products = productDAO.fetchStock();
                for(Map.Entry<Integer, Integer> entry : newMap.entrySet()){
                    Integer id = entry.getKey();
                    Integer quantity = entry.getValue();
                    if(products.containsKey(id)){
//                        System.out.println("ID: " + id + " Quantity: " + quantity);
                        //update the product stock in the database
                        int newStock = products.get(id) - quantity;
//                        System.out.println("New: " + newStock);
                        productDAO.updateStock(id, newStock);
                    }
                }               
            }catch(SQLException e){
                System.out.print(e);
            }
            request.getRequestDispatcher("OrderHistoryServlet").forward(request, response);
       }       
       
    }
}