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

public class VerificationCodeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplicationService applicationService;

	public VerificationCodeController() {
		applicationService = new ApplicationServiceImpl(MySQLConnectionFactory.getInstance());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String code = request.getParameter("code");

		if (code.trim().equalsIgnoreCase(request.getParameter("codeGenerated").trim())) {
			try {
				User user = applicationService.getUserByEmail(email);
				request.setAttribute("user", user);
				request.getRequestDispatcher("new_pw.jsp").forward(request, response);
			} catch (SQLException e) {

				e.printStackTrace();
			}

		} else {
			System.out.println("Wrong code, try again.");
			request.setAttribute("email", email);
			request.setAttribute("code", code);
			request.setAttribute("codeGenerated", request.getParameter("codeGenerated"));
			request.getRequestDispatcher("recover_confirm.jsp").forward(request, response);
		}
	}

}
