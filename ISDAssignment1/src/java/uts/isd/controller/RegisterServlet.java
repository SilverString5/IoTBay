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


public class RegisterServlet extends HttpServlet {
@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
                String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phonenumber");
                String address = request.getParameter("address");
                String stringDOB = request.getParameter("DOB");
                String gender = request.getParameter("gender");
               

		UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
                AccessLogDAO accessLogDAO = (AccessLogDAO) session.getAttribute("accessLogDAO");

                int errorCount = 0;
                String errorMsgs="";
                

                    //Series of if statements to check whether entered registration input is valid
                    //Email validation
                    if ((!email.contains("@")) || !email.contains(".com") || email.isEmpty()){
                            errorCount++;
                            errorMsgs+=" The entered email address is invalid.\n";
                    }
                    //Password validation
                    if (password.length()<7){
                            errorCount++;
                            errorMsgs+=" Your password must be at least 7 characters long.\n" ;
                     }
                     // Phone number validation
                        try {
                        int intPhone = Integer.parseInt(phone);
                            if (phone.length()>10){
                        errorCount++;
                        errorMsgs+= "Phone numbers must not be longer than 10 characters.\n";
                            }
                        }
                        catch (NumberFormatException e){
                        System.out.println(e);
                        errorCount++;
                        errorMsgs+="A phone number cannot have non-numeric characters";
                        }

                    // Address validation
                    if (address.length()<5){
                        errorCount++;
                        errorMsgs+=" The address entered must be at least 5 characters.\n";
                    }
                    
                    // Date validation
                    try {
                    System.out.print("DOB: " + stringDOB);
                    Date DOB = Date.valueOf(stringDOB);
                    long now = System.currentTimeMillis();
                    Date nowDate = new Date(now);
                    if (DOB.after(nowDate)){
                        errorCount++;
                        errorMsgs+=" Your date of birth is in the future.";
                      }
                    }

                    catch (IllegalArgumentException e) {
                    System.out.println(e);
                    errorCount++;
                    errorMsgs+="The date entered must be a non-empty date";
}
                    // Checks if an email address is already taken
                    try{
                    if (userDAO.checkExists(email)){
                            errorCount++;
                            errorMsgs+="This email address is already registered in our database.";
                                                    }
                    } catch (SQLException e){
                            System.out.println(e);
                                            }
                // Will allow error messgage to be displayed for incorrect input
		if (errorCount>0) {
			session.setAttribute("errorMsgs", errorMsgs);
			request.getRequestDispatcher("register.jsp").include(request, response);
		} else {
			try {
                                //Create user in database and set in session
				userDAO.createUser(email, password, name, phone, address, Date.valueOf(stringDOB), gender);
				User user = new User();
                                user.setUserID(userDAO.retrieveUserID(email));
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				user.setPhone(phone);
                                user.setuserDOB(Date.valueOf(stringDOB));
                                user.setGender(gender);
                                user.setAddress(address);
                                user.setUserType("C");
                                session.setAttribute("user", user);
                                // Create user access log in database and set in session
                                accessLogDAO.createUserAccessLog(user.getUserID());
                                UserAccessLog accessLog = accessLogDAO.findMostRecent(user.getUserID());
                                session.setAttribute("accessLog",accessLog);
                                // Fetches user access logs from database in an ArrayList
                                ArrayList<UserAccessLog> accessLogs = accessLogDAO.viewAccessLogs(user.getUserID());
                                session.setAttribute("accessLogs", accessLogs);

				request.getRequestDispatcher("welcome.jsp").include(request, response);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
}