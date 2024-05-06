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

            }




}
