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

public class RecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplicationService applicationService;

	public RecoveryController() {
		applicationService = new ApplicationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");

		List<User> users = new ArrayList<User>();
		User userToUpdate = null;
		try {
			users = applicationService.getAllUsers();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		for (User user : users) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				userToUpdate = user;
			}
		}
		if (userToUpdate != null) {
			request.setAttribute("user", userToUpdate);
			request.getRequestDispatcher("new_pw.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "user " + email + " doesn't exist");
			request.getRequestDispatcher("").forward(request, response);
		}
	}

}
