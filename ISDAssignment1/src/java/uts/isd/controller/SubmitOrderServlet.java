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
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;
import uts.isd.model.dao.ProductDAO;

/**
 *
 * @author pyaephyozaw
 */
public class SubmitOrderServlet extends HttpServlet{
    private OrderDAO orderDAO;   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       HttpSession session = request.getSession();
//       conn = db.openConnection();
       orderDAO = (OrderDAO)session.getAttribute("orderDAO");
//       HashMap<Integer, Integer> shoppingCart = (HashMap<Integer, Integer>) session.getAttribute("shoppingCart");
//       ArrayList<Product> cartList = (ArrayList<Product>) session.getAttribute("cartList");
//       ArrayList<Integer> quantityList = new ArrayList();
//       double totalAmount = (double)session.getAttribute("totalAmount");
//       for(Map.Entry<Integer, Integer> entry : shoppingCart.entrySet()){
//                  Integer quantity = entry.getValue();
////                  product = productDAO.getProduct(productID);
////                  productList.add(product);
//                  quantityList.add(quantity); 
//        }
       try {
//           orderDAO = new OrderDAO(conn);
//           orderDAO.CreateOrder(1, totalAmount, quantityList, cartList);
            orderDAO.SubmitOrder(1);
       } catch(SQLException e) {
           System.out.print(e);
        }
       request.getRequestDispatcher("shoppingcart.jsp").include(request, response); 
    }
    
//    @Override
//	public void destroy() {
//		try {
//			db.closeConnection();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}
}
