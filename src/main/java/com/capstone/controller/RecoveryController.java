package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.model.User;
import com.capstone.util.RecoveryUtil;

public class RecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplicationService applicationService;

	public RecoveryController() {
		applicationService = new ApplicationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String code = request.getParameter("code");
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
		String code = RecoveryUtil.encodeUrl(email);
		String url = "RecoveryController?code=" + code;
		System.out.println("Emailing password recovery link: " + url + " to " + email);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<p>You have recently requested to reset your password.</p>");
		sb.append("<p>Please click the link below to perform the reset.</p>");
		sb.append("<p><a href=\"" + url + "\">Reset Password</a></p>");
		response.getWriter().write(sb.toString());
	}

}
