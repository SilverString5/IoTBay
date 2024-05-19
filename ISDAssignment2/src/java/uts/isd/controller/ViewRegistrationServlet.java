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



public class ViewRegistrationServlet extends HttpServlet {
        @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
                String noLoginError = "";
                UserDAO userDAO = (UserDAO)session.getAttribute("userDAO");
                User user = (User) session.getAttribute("user");
                if (user==null){
                noLoginError+=" You are not logged in. ";
                session.setAttribute("noLoginError", noLoginError);
                }
                request.getRequestDispatcher("manageRegistration.jsp").include(request, response);

            }


    


}