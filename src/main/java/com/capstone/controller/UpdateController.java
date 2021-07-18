package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.model.Password;

public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplicationService applicationService;

	public UpdateController() {
		super();
		applicationService = new ApplicationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id").length() > 0 ? request.getParameter("id") : null;
		String website = request.getParameter("website");
		String websiteUser = request.getParameter("websiteUser");
		String password = request.getParameter("password");
		Password newPassword = new Password();
		if (id != null) {
			newPassword.setId(Integer.valueOf(id));
		}
		newPassword.setWebsite(website);
		newPassword.setWebsiteUser(websiteUser);
		newPassword.setPassword(password);
		Cookie[] cookies = request.getCookies();
		@SuppressWarnings("unused")
		String username = null;
		String userId = null;

		try {
			if (id != null) {

				applicationService.updatePassword(newPassword);
			} else {
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("user")) {
							username = cookie.getValue();
						}
						if (cookie.getName().equals("userId")) {
							userId = cookie.getValue();
						}
					}
				}

				newPassword.setUserId(Integer.valueOf(userId));
				applicationService.addPasswordToUser(newPassword);
			}
			response.sendRedirect("list");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
