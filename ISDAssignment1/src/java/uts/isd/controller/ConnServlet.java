/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;

import uts.isd.model.dao.ShipmentDAO;

import uts.isd.model.dao.AccessLogDAO;
import uts.isd.model.Product;
import uts.isd.model.dao.ProductDAO;


public class ConnServlet extends HttpServlet {

	private DBConnector db;
	private UserDAO userDAO;
        private ShipmentDAO shipmentDAO;
	private Connection conn;
        private AccessLogDAO accessLogDAO;
        private ProductDAO productDAO;


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
			userDAO = new UserDAO(conn);

                        shipmentDAO = new ShipmentDAO(conn);

                        accessLogDAO = new AccessLogDAO(conn);
			productDAO = new ProductDAO(conn);
                        ArrayList<Product> products = productDAO.fetchAllProducts();
                        session.setAttribute("listDevice", products);

		} catch (SQLException e) {
			System.out.print(e);
		}
                session.setAttribute("productDAO", productDAO);
		session.setAttribute("userDAO", userDAO);

                session.setAttribute("shipmentDAO", shipmentDAO);

                session.setAttribute("accessLogDAO",accessLogDAO);

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