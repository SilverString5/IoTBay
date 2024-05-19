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
import java.sql.Date;
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
                String enteredDate = request.getParameter("logdate");
                String dateError="";
                // Check whether user is logged in
                if (user==null){
                String anonError = "You must log in to see your access logs.";
                session.setAttribute("anonError", anonError);
                                } else {
                // Validates the search date entered
                try{
                    Date date = Date.valueOf(enteredDate);
                    //Searches the access logss
                    ArrayList<UserAccessLog> accessLogs = accessLogDAO.filterAccessLogDate(user.getUserID(), enteredDate);
                    session.setAttribute("accessLogs", accessLogs);
                }
                catch (IllegalArgumentException e){
                dateError+="The entered date must be a valid non-empty date.";
                session.setAttribute("dateError", dateError);
                }
                catch( SQLException e){
                    System.out.println(e);}
                }

            request.getRequestDispatcher("viewAccessLogs.jsp").include(request, response);
            }
}




