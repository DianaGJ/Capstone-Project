package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.dbconnection.MySQLConnectionFactory;
import com.capstone.model.User;
import com.capstone.util.CodeGenerator;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplicationService applicationService;

	public LoginController() {
		applicationService = new ApplicationServiceImpl(MySQLConnectionFactory.getInstance());

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Login Page");
		HttpSession session = request.getSession();
		System.out.println(session.getId());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("uname");
		String password = request.getParameter("psw");

		Boolean authenticatedUser = true;
		User user = null;
		try {
			user = applicationService.getUserByUsername(username);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (user != null) {
			if (user.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(password))) {
				if (user.isVerified()) {
					System.out.println("Logged as " + username);
					Cookie loginCookie = new Cookie("user", user.getUsername());
					Cookie userIdCookie = new Cookie("userId", String.valueOf(user.getId()));
					int lifeSessionInSec = 120;
					loginCookie.setMaxAge(lifeSessionInSec);
					response.addCookie(loginCookie);
					response.addCookie(userIdCookie);
					response.sendRedirect("list");
				} else {
					System.out.println("Please verify your account");
					request.setAttribute("user", user);
					if (user.getVerificationCode() != null) {
						request.setAttribute("user", user);
						request.getRequestDispatcher("/activate.jsp").forward(request, response);
					} else {
						user.setVerificationCode(CodeGenerator.generateCode());
						try {

							applicationService.updateUser(user);
							request.getRequestDispatcher("/activate.jsp").forward(request, response);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				System.out.println("Please verify your password");

				request.setAttribute("errorMessage", "Incorrect, please verify your password");
				authenticatedUser = false;
			}
		} else {
			System.out.println("User doesn't exist");
			request.setAttribute("errorMessage", "User doesn't exist");
			authenticatedUser = false;
		}

		if (authenticatedUser) {

		} else {
			request.getRequestDispatcher("").forward(request, response);
		}

	}

}
