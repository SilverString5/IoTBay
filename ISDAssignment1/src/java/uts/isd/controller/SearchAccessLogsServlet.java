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
import javax.servlet.RequestDispatcher;

import uts.isd.model.User;
import java.util.ArrayList;
import uts.isd.model.UserAccessLog;
import uts.isd.model.dao.AccessLogDAO;

/**
 *
 * @author notba
 */
public class SearchAccessLogsServlet extends HttpServlet {
        @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();
                AccessLogDAO accessLogDAO = (AccessLogDAO)session.getAttribute("accessLogDAO");
                User user = (User)session.getAttribute("user");
                String origin = request.getParameter("origin");
                if (origin.equals("welcome")){
                try{
                ArrayList<UserAccessLog> accessLogs = accessLogDAO.viewAccessLogs(user.getUserID());
                session.setAttribute("accessLogs", accessLogs);
                request.getRequestDispatcher("viewAccessLogs.jsp").include(request, response);
                }
                catch (SQLException e) {
                System.out.println(e);
                                        }
                }
            if (origin.equals("viewAccessLogs")){
                try{
                String enteredDate = request.getParameter("logdate");
                ArrayList<UserAccessLog> accessLogs = accessLogDAO.filterAccessLogDate(user.getUserID(), enteredDate);
                session.setAttribute("accessLogs", accessLogs);
                request.getRequestDispatcher("viewAccessLogs.jsp").include(request, response);
                }
                catch (SQLException e) {
                System.out.println(e);
                                        }
                }
            


            }

            }




