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
import java.util.ArrayList;
import uts.isd.model.UserAccessLog;
import uts.isd.model.dao.AccessLogDAO;

/**
 *
 * @author notba
 */
public class LoginServlet extends HttpServlet {
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                UserDAO userDAO = (UserDAO)session.getAttribute("userDAO");
                AccessLogDAO accessLogDAO = (AccessLogDAO) session.getAttribute("accessLogDAO");
                String invalidLogin = "";
                try {
                // Logs user in. The variable user will be null if login is unsuccessful
                User user = userDAO.login(email, password);
                    if (user!=null){
                        // Creates an user access log and sets it in the session
                        accessLogDAO.createUserAccessLog(user.getUserID());
                        UserAccessLog accessLog = accessLogDAO.findMostRecent(user.getUserID());
                        session.setAttribute("user", user);
                        session.setAttribute("accessLog",accessLog);
                        user =(User)session.getAttribute("user");
                        ArrayList<UserAccessLog> accessLogs = accessLogDAO.viewAccessLogs(user.getUserID());
                        session.setAttribute("accessLogs", accessLogs);
                        request.getRequestDispatcher("welcome.jsp").include(request, response);

                        }
                    else {
                        // Allows an error message to be displayed later if login details are wrong
                        invalidLogin+="Your login details are incorrect. Please try again or register.";
                        session.setAttribute("invalidLogin", invalidLogin);
                        request.getRequestDispatcher("login.jsp").include(request, response);
                            }
                    }
                catch (SQLException e) {
                      System.out.println(e);
                    }
      }

}