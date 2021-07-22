package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.dao.UserDaoImpl;
import com.capstone.dbconnection.MySQLConnectionFactory;
import com.capstone.model.User;
import com.capstone.util.CodeGenerator;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplicationService applicationService;
	UserDaoImpl userDao;

	public RegisterController() {
		applicationService = new ApplicationServiceImpl(MySQLConnectionFactory.getInstance());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("uname");
		String email = request.getParameter("email");
		String password = DigestUtils.md5Hex(request.getParameter("pword"));
		String passwordConf = DigestUtils.md5Hex(request.getParameter("pword2"));
		User user = null;
		try {
			user = applicationService.getUserByUsername(username);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		List<User> users;
		try {
			users = applicationService.getAllUsers();
		
			if (password.equals(passwordConf)) {
				if (users.size() > 0) {
					if (!users.stream().anyMatch(userIndex -> username.equalsIgnoreCase(userIndex.getUsername()))) {
						if (!users.stream().anyMatch(userIndex -> email.equalsIgnoreCase(userIndex.getEmail()))) {
							user = new User(username, email, password, false, CodeGenerator.generateCode());
							applicationService.insertUser(user);
							request.setAttribute("user", user);
							request.getRequestDispatcher("/activate.jsp").forward(request, response);
						} else {
							user = users.stream()
									.filter(userWithEmail -> email.equalsIgnoreCase(userWithEmail.getEmail()))
									.findFirst().get();

							request.setAttribute("erroMessage", email + " is already registered.");
							request.getRequestDispatcher("/register.jsp").forward(request, response);
						}
					} else {
						request.setAttribute("erroMessage", "user " + username + " already exists.");
						request.getRequestDispatcher("/register.jsp").forward(request, response);

					}
				} else {
					user = new User(username, email, password, false, CodeGenerator.generateCode());
					applicationService.insertUser(user);
					request.setAttribute("user", user);
					request.getRequestDispatcher("/activate.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("uname", username);
				request.setAttribute("email", email);
				request.setAttribute("erroMessage", "Password doesn't match");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
