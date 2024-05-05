/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.UserDAO;
import java.sql.Date;


public class RegisterServlet extends HttpServlet {
@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
                String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                Date DOB = Date.valueOf(request.getParameter("DOB"));
                String gender = request.getParameter("gender");
               

		UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");

		if (name.length() <= 5) {
			session.setAttribute("nameErr", "The name provided was not long enough!");
			request.getRequestDispatcher("register.jsp").include(request, response);
		} else {
			try {
				userDAO.createUser(email, password, name, phone, address, DOB, gender);

				User user = new User();
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				user.setPhone(phone);
                                user.setuserDOB(DOB);
                                user.setGender(gender);
                                user.setAddress(address);
                                user.setUserType("C");

				session.setAttribute("user", user);

				request.getRequestDispatcher("welcome.jsp").include(request, response);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
}