package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.model.User;
import com.capstone.util.CodeGenerator;

/**
 * Servlet implementation class ActivationController
 */
public class ActivationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	ApplicationService applicationService;

	public ActivationController() {
		super();
		applicationService = new ApplicationServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String verificationCode = request.getParameter("verificationCode");
		try {
			List<User> users = applicationService.getUsers();
			User user = users.stream().filter(userTemp -> email.equalsIgnoreCase(userTemp.getEmail())).findFirst().get();
			if (user.getVerificationCode()!=null) {
				if (user.getVerificationCode().trim().equalsIgnoreCase(verificationCode.trim())) {
					user.setVerified(true);
					applicationService.updateUser(user);
					request.setAttribute("activationMessage", "YOUR ACCOUNT WAS ACTIVATED");
					request.getRequestDispatcher("").forward(request, response);
				} else {
					request.setAttribute("user", user);
					request.setAttribute("messageOnActivation", "INVALID CODE, PLEASE TRY AGAIN");
					request.getRequestDispatcher("activate.jsp").forward(request, response);
				} 
			}else {
				user.setVerificationCode(CodeGenerator.generateCode());
				applicationService.updateUser(user);
				request.setAttribute("user", user);
				request.setAttribute("messageOnActivation", "CODE HAS EXPIRED, PLEASE TRY AGAIN");
				request.getRequestDispatcher("activate.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
