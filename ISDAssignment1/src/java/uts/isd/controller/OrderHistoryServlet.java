/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import uts.isd.model.dao.*;
import uts.isd.model.*;
import uts.isd.controller.*;
import javax.servlet.http.*;

/**
 *
 * @author pyaephyozaw
 */
public class OrderHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        OrderDAO orderDAO = (OrderDAO)session.getAttribute("orderDAO");
        ArrayList<Order> orderList = new ArrayList();
        try {
            orderList = orderDAO.readOrderHistory(user.getUserID());
        }catch (SQLException e){
            System.out.print(e);
        }
        session.setAttribute("orderList", orderList);
        request.getRequestDispatcher("orders.jsp").include(request, response);
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
                doGet(request, response);
//		request.getRequestDispatcher("updateorder.jsp").forward(request, response);
	} 
}
