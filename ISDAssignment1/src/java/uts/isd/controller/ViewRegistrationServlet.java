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
                UserDAO userDAO = (UserDAO)session.getAttribute("userDAO");
                User currentUser = (User) session.getAttribute("user");
                try {                
                User user = userDAO.findUser(currentUser.getUserID());
                session.setAttribute("user", user);

                }
                catch (SQLException e){
                System.out.println(e);
                }
                request.getRequestDispatcher("manageRegistration.jsp").include(request, response);

            }


    


}
