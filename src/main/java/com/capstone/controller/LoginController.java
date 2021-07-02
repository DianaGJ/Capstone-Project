package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.model.User;
import com.capstone.util.CodeGenerator;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	ApplicationService applicationService;

	public LoginController() {
		applicationService = new ApplicationServiceImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("uname");
		String password = request.getParameter("psw");
		
		Boolean authenticatedUser = true;
		User user = null;
		try {
			user = applicationService.getUserByUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			if (user.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(password))) {
				if (user.isVerified()) {
					// user set verification code
					System.out.println("Logged as " + username);
					Cookie loginCookie = new Cookie("user", user.getUsername());
					Cookie userIdCookie = new Cookie("userId", String.valueOf(user.getId()));
					int lifeSessionInSec = 120;
					loginCookie.setMaxAge(lifeSessionInSec);
					response.addCookie(loginCookie);
					response.addCookie(userIdCookie);
					response.sendRedirect("list");	
				}else {
					System.out.println("please verify your account");
					request.setAttribute("user", user);
					if (user.getVerificationCode() != null) {
						request.setAttribute("user", user);
						request.getRequestDispatcher("/activate.jsp").forward(request, response);
					}else {
						user.setVerificationCode(CodeGenerator.generateCode());
						try {
							
							applicationService.updateUser(user);
							request.getRequestDispatcher("/activate.jsp").forward(request, response);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
			}else {
				System.out.println("please verify your password");
				
				request.setAttribute("errorMessage", "please verify your password");
				authenticatedUser = false;
			}
		} else {
			System.out.println("User doesn't exist");
			request.setAttribute("errorMessage", "User doesn't exist");
			authenticatedUser = false;
		}
		
		if (authenticatedUser) {
			
		}else {
			request.getRequestDispatcher("").forward(request, response);
		}

	}

}
