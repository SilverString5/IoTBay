/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;
import uts.isd.model.dao.AccessLogDAO;
<<<<<<< Updated upstream
=======
import uts.isd.model.Product;
import uts.isd.model.User;
import uts.isd.model.UserAccessLog;
import uts.isd.model.dao.ProductDAO;
>>>>>>> Stashed changes

public class ConnServlet extends HttpServlet {

	private DBConnector db;
	private UserDAO userDAO;
	private Connection conn;
        private AccessLogDAO accessLogDAO;

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
                        accessLogDAO = new AccessLogDAO(conn);
<<<<<<< Updated upstream
		} catch (SQLException e) {
=======
			productDAO = new ProductDAO(conn);
                        ArrayList<Product> products = productDAO.fetchAllProducts();
                        session.setAttribute("listDevice", products);
                        if (session.getAttribute("user")!=null){
                            User user = (User)session.getAttribute("user");
                            ArrayList<UserAccessLog> allMyLogs = accessLogDAO.viewAccessLogs(user.getUserID());
                            session.setAttribute("allMyLogs", allMyLogs);
                                                        }
                       } catch (SQLException e) {
>>>>>>> Stashed changes
			System.out.print(e);
		}

		session.setAttribute("userDAO", userDAO);
                session.setAttribute("accessLogDAO",accessLogDAO);
		request.getRequestDispatcher("index.jsp").include(request, response);
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