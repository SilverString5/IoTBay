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
                Date DOB = Date.valueOf(request.getParameter("DOB"));
                String gender = request.getParameter("gender");
               

		UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");
                AccessLogDAO accessLogDAO = (AccessLogDAO) session.getAttribute("accessLogDAO");

                int errorCount = 0;
                String errorMsgs="";
                


                    if ((!email.contains("@")) || !email.contains(".com")){
                            errorCount++;
                            errorMsgs+=" The entered email address is invalid.\n";
                    }

                    if (password.length()<7){
                            errorCount++;
                            errorMsgs+=" Your password must be at least 7 characters long.\n" ;
                     }

                    if (phone.length()>10){
                        errorCount++;
                        errorMsgs+= " Please enter a valid phone number without an area code.\n";
                    }

                    if (address.length()<5){
                        errorCount++;
                        errorMsgs+=" The address entered must be at least 5 characters.\n";
                    }

                    long now = System.currentTimeMillis();
                    Date nowDate = new Date(now);
                    if (DOB.after(nowDate)){
                        errorCount++;
                        errorMsgs+=" Your date of birth is in the future.";
                      }

                    try{
                    if (userDAO.checkExists(email)){
                            errorCount++;
                            errorMsgs+="This email address is already registered in our database.";
                                                    }
                    } catch (SQLException e){
                            System.out.println(e);
                                            }





		if (errorCount>0) {
			session.setAttribute("errorMsgs", errorMsgs);
			request.getRequestDispatcher("register.jsp").include(request, response);
		} else {
			try {
				userDAO.createUser(email, password, name, phone, address, DOB, gender);
				User user = new User();
                                user.setUserID(userDAO.retrieveUserID(email));
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				user.setPhone(phone);
                                user.setuserDOB(DOB);
                                user.setGender(gender);
                                user.setAddress(address);
                                user.setUserType("C");
                                session.setAttribute("user", user);
                                accessLogDAO.createUserAccessLog(user.getUserID());
                                UserAccessLog accessLog = accessLogDAO.findMostRecent(user.getUserID());
                                session.setAttribute("accessLog",accessLog);

				request.getRequestDispatcher("welcome.jsp").include(request, response);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
}