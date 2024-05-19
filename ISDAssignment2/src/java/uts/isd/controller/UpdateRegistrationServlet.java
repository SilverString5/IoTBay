/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.controller;
import java.io.IOException;
import java.sql.Date;
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
                String stringDOB = request.getParameter("DOB");
                String gender = request.getParameter("gender");
                int errorCount = 0;
                String updateMsgs="";
                
                   // Validates the updated registration inputs
                    //Email validation
                   if ((!email.contains("@")) || !email.contains(".com") || email.isEmpty()){
                            errorCount++;
                            updateMsgs+=" The entered email address is invalid.\n";
                    }

                    // Password validation
                    if (password.length()<7){
                            errorCount++;
                            updateMsgs+=" Your password must be at least 7 characters long.\n" ;
                     }

                     // Phone number validation
                        try {
                        int intPhone = Integer.parseInt(phoneNumber);
                            if (phoneNumber.length()>10){
                                errorCount++;
                                updateMsgs+= "Phone numbers must not be longer than 10 characters.\n";
                            }
                        }
                        catch (NumberFormatException e){
                        System.out.println(e);
                        errorCount++;
                        updateMsgs+="A phone number cannot have non-numeric characters. \n";
                        }

                    // Address validation
                    if (address.length()<5){
                        errorCount++;
                        updateMsgs+=" The address entered must be at least 5 characters. \n";
                    }
                    
                    // Date validation
                    try {
                    Date DOB = Date.valueOf(stringDOB);
                    long now = System.currentTimeMillis();
                    Date nowDate = new Date(now);
                    if (DOB.after(nowDate)){
                        errorCount++;
                        updateMsgs+=" Your date of birth is in the future. \n";
                      }
                    }

                    catch (IllegalArgumentException e) {
                    System.out.println(e);
                    errorCount++;
                    updateMsgs+=" The date entered must be a non-empty date, \n";
                                                        }

    

		if (errorCount>0) {
			session.setAttribute("updateMsgs", updateMsgs);
		} else {
			try {
                        user = userDAO.update(email, password, fullName, 
                        phoneNumber, address, stringDOB, gender, ID);
                        session.setAttribute("user", user);
                       updateMsgs+=" You have successfully updated your account details";
                        session.setAttribute("updateMsgs", updateMsgs);
                            }
                        catch (SQLException e) {
                        System.out.println(e);
                                                }
                        }
			request.getRequestDispatcher("manageRegistration.jsp").include(request, response);                   
    
}

}