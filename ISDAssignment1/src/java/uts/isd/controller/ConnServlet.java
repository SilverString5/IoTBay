package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Product;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDAO;
import uts.isd.model.dao.ProductDAO;
import uts.isd.model.dao.UserDAO;

public class ConnServlet extends HttpServlet {

	private DBConnector db;
	private ProductDAO productDAO;
        private OrderDAO orderDAO;
        private UserDAO userDAO;
	private Connection conn;

	@Override
	public void init() {
		try {
			db = new DBConnector();
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		conn = db.openConnection();
                
		try {
			productDAO = new ProductDAO(conn);
                        orderDAO = new OrderDAO(conn);
                        userDAO = new UserDAO(conn);
                        
                        ArrayList<Product> products = productDAO.fetchAllProducts();
                        session.setAttribute("listDevice", products);
                        
		} catch (SQLException e) {
			System.out.print(e);
		}
                
		session.setAttribute("productDAO", productDAO);
                session.setAttribute("orderDAO", orderDAO);
                session.setAttribute("userDAO", userDAO);
                if(request != null && response != null)
                    request.getRequestDispatcher("index.jsp").include(request, response);
                
                   
	}
        
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
                doGet(request, response);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
		try {
			db.closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}