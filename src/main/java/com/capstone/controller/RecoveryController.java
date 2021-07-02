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

/**
 * Servlet implementation class RecoveryController
 */
public class RecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	ApplicationService applicationService;

	public RecoveryController() {
		applicationService = new ApplicationServiceImpl();
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
		String email = request.getParameter("email");

		List<User> users = new ArrayList<User>();
		User userToUpdate = null;
		try {
			users = applicationService.getUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		}else {
			request.setAttribute("errorMessage", "user " + email + " doesn´t exist");
			request.getRequestDispatcher("").forward(request, response);
		}
	}

}
