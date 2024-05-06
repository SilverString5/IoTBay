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
import uts.isd.model.UserAccessLog;
import uts.isd.model.dao.AccessLogDAO;
import java.sql.Time;
/**
 *
 * @author notba
 */
public class LogoutServlet extends HttpServlet {
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserAccessLog accessLog = (UserAccessLog) session.getAttribute("accessLog");
        String logoutTimeUpdated = "no";

        AccessLogDAO accessLogDAO = (AccessLogDAO) session.getAttribute("accessLogDAO");
        long now = System.currentTimeMillis();
        Time logoutTime = new Time(now);

        try { accessLog = accessLogDAO.updateLogoutTime(logoutTime, accessLog.getAccessLogID());
                session.setAttribute("accessLog", accessLog);
                logoutTimeUpdated = "yes";
                
                


        } catch (SQLException e) {
                System.out.println(e);

                                      }
        session.setAttribute("logoutTimeUpdated", logoutTimeUpdated);
        request.getRequestDispatcher("logout.jsp").include(request, response);
    }

}
