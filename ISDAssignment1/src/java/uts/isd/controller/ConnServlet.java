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

public class ConnServlet extends HttpServlet {

	private DBConnector db;
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
			userDAO = new UserDAO(conn);
		} catch (SQLException e) {
			System.out.print(e);
		}

		session.setAttribute("userDAO", userDAO);
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