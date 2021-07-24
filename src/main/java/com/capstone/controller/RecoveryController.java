package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.dbconnection.MySQLConnectionFactory;
import com.capstone.model.User;
import com.capstone.util.CodeGenerator;
import com.capstone.util.RecoveryUtil;
import com.capstone.util.Sender;

public class RecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplicationService applicationService;

	public RecoveryController() {
		applicationService = new ApplicationServiceImpl(MySQLConnectionFactory.getInstance());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");

		if (code == null) {
			request.getRequestDispatcher("/recover.jsp").forward(request, response);
			return;
		}

		String email = RecoveryUtil.decodeUrl(code);

		if (email == null) {
			request.getRequestDispatcher("/recover.jsp").forward(request, response);
			return;
		}

		User userToUpdate = null;
		try {
			userToUpdate = applicationService.getUserByEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (userToUpdate != null) {
			request.setAttribute("user", userToUpdate);
			request.getRequestDispatcher("new_pw.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "user " + email + " doesn't exist");
			request.getRequestDispatcher("").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String codeGenerated = CodeGenerator.generateCode();
		User user = null;
		try {
			user = applicationService.getUserByEmail(email);
			if (user.getEmail() == null) {

				System.out.println("User doesn't exists");
				request.setAttribute("errorMessage", "Please check if you are already registered.");
				request.getRequestDispatcher("recover.jsp").forward(request, response);

			}else {
				Sender sender = new Sender();
				if (!sender.sendEmail(codeGenerated, email, user.getUsername()) || user == null) {
					System.out.println("fail on send email to " + email);
					request.getRequestDispatcher("recover.jsp").forward(request, response);
				}else {
				request.setAttribute("email", email);
				request.setAttribute("codeGenerated", codeGenerated);
				request.getRequestDispatcher("recover_confirm.jsp").forward(request, response);
				}
			}
		} catch (SQLException e) {

		}


	}

}
