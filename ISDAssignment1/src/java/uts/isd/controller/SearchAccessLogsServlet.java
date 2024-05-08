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

                String origin = request.getParameter("origin");
                String enteredDate = request.getParameter("logdate");
                String dateError="";
                if (user==null){
                String anonError = "You must log in to see your access logs.";
                session.setAttribute("annonError", anonError);
                                }


                else if (origin.equals("welcome")){
                    try{
                        ArrayList<UserAccessLog> accessLogs = accessLogDAO.viewAccessLogs(user.getUserID());
                        session.setAttribute("accessLogs", accessLogs);
                        }
                catch (SQLException e) {
                System.out.println(e);
                                       }
                                                  }
                else if (origin.equals("viewAccessLogs")){
                    try{
            
                        Date convertedDate = Date.valueOf(enteredDate);
                        long now = System.currentTimeMillis();
                        Date nowDate = new Date(now);
                        if (convertedDate.after(nowDate)){
                            dateError+=" The entered date is in the future. ";
                            session.setAttribute("dateError", dateError);
                                                          }
                ArrayList<UserAccessLog> accessLogs = accessLogDAO.filterAccessLogDate(user.getUserID(), enteredDate);
                session.setAttribute("accessLogs", accessLogs);
                }
                catch (SQLException e) {
                System.out.println(e);
                                       }
                catch (IllegalArgumentException e){
                System.out.println(e);
                dateError+=" The date entered must be a valid non-empty date. ";
                session.setAttribute("dateError",dateError);
                                                  }

                                                                }
            
            request.getRequestDispatcher("viewAccessLogs.jsp").include(request, response);

            }


            }




