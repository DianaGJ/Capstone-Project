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

/**
 * Servlet implementation class UpdatePassController
 */
//@WebServlet("/UpdatePassController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	ApplicationService applicationService;

	public UpdateController() {
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id").length()>0 ?  request.getParameter("id") : null;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
