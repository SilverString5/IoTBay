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
public class SubmitOrderServlet extends HttpServlet{
    private OrderDAO orderDAO; 
    private ProductDAO productDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       HttpSession session = request.getSession();

       orderDAO = (OrderDAO)session.getAttribute("orderDAO");
       productDAO = (ProductDAO)session.getAttribute("productDAO");
       HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart");
       ArrayList<Product> cartList = (ArrayList<Product>) session.getAttribute("cartList");
       User user = (User) session.getAttribute("user");
       ArrayList<Integer> quantityList = new ArrayList();

        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
       for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
                  Integer quantity = entry.getValue();

                  quantityList.add(quantity); 
        }

       try {
            if(user != null){  //registered user
                orderDAO.SubmitOrder(user.getUserID(), totalAmount, quantityList, cartList);
            }else{  //anonymouse
                orderDAO.anonymousOrder(totalAmount, quantityList, cartList);  //userid is 0 (null) for all anonymous users
            }
            
            HashMap<Integer, Integer> products = productDAO.fetchStock();
            //update the product stock in the database accordingly when the user submit the order
            for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
                Integer productID = entry.getKey();
                Integer quantity = entry.getValue();
                if(products.containsKey(productID)){
                    int newStock = products.get(productID) - quantity;
                    productDAO.updateStock(productID, newStock);
                }
            }
       } catch(SQLException e) {
           System.out.print(e);
        }
       request.getRequestDispatcher("shoppingcart.jsp").include(request, response); 
    }
}