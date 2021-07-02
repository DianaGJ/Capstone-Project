package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.model.User;

/**
 * Servlet implementation class UpdatePassController
 */
public class UpdatePassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	ApplicationService applicationService;

	public UpdatePassController() {
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

		String username = request.getParameter("username");
		String oldPass = request.getParameter("newpw");
		String newPass = request.getParameter("newpw2");
		User user = null;

		try {
			user = applicationService.getUserByUsername(username);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (oldPass.equals(newPass)) {
			user.setPassword(DigestUtils.md5Hex(newPass));
			try {
				applicationService.updateUser(user);
				request.setAttribute("activationMessage", "¡PASSWORD UPDATED!");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.setAttribute("errorMessage", "PASSWORDS DOESN´T MATCH");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/new_pw.jsp").forward(request, response);
		}

	}

}
