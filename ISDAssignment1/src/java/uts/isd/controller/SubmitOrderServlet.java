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
//       conn = db.openConnection();
       orderDAO = (OrderDAO)session.getAttribute("orderDAO");
       productDAO = (ProductDAO)session.getAttribute("productDAO");
       HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart");
       ArrayList<Product> cartList = (ArrayList<Product>) session.getAttribute("cartList");
       User user = (User) session.getAttribute("user");
       ArrayList<Integer> quantityList = new ArrayList();
//       double totalAmount = (double)session.getAttribute("totalAmount");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
       for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
                  Integer quantity = entry.getValue();
//                  product = productDAO.getProduct(productID);
//                  productList.add(product);
                  quantityList.add(quantity); 
        }
//       HashMap<Integer, Integer> updateStock = new HashMap();
       try {
//            orderDAO.CreateOrder(user.getUserID(), totalAmount, quantityList, cartList);
            if(user != null){  //register user
                orderDAO.SubmitOrder(user.getUserID(), totalAmount, quantityList, cartList);
            }else{  //anonymouse
                orderDAO.anonymousOrder(totalAmount, quantityList, cartList);  //userid is 0 (null) for all anonymous users
            }
            
            HashMap<Integer, Integer> products = productDAO.fetchStock();
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