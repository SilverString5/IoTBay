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


/**
 *
 * @author notba
 */
public class UpdateRegistrationServlet extends HttpServlet {
        @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
                UserDAO userDAO = (UserDAO)session.getAttribute("userDAO");
                User user = (User)session.getAttribute("user");
                int ID = Integer.parseInt(request.getParameter("ID"));
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String fullName = request.getParameter("name");
                String phoneNumber = request.getParameter("phone");
                String address = request.getParameter("address");  

    
}

}