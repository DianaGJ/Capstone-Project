package com.capstone.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.model.Password;

@WebServlet("/listServlet")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ApplicationService applicationService;

	public ListController() {
		applicationService = new ApplicationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String instruccion = request.getParameter("instruccion");
		if (instruccion != null) {

			switch (instruccion) {
			case "edit":
				try {
					edit(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				break;
			case "delete":
				try {
					delete(request, response);
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}

				break;
			case "create":
				break;
			}

		} else {
			try {
				listar(request, response);
			} catch (NumberFormatException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}

		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Password password = applicationService.getPasswordByUserId(id);
		request.setAttribute("password", password);
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		applicationService.deletePassword(id);
		response.sendRedirect("list");
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		Cookie[] cookies = request.getCookies();
		List<Password> passwords = new ArrayList<Password>();
		String username = null;
		String userId = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					username = cookie.getValue();
				}
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
				}
			}

			passwords = applicationService.getAllPasswordsForUserId(Integer.valueOf(userId));
			request.setAttribute("passwords", passwords);
			request.setAttribute("user", username);
			request.getRequestDispatcher("/list.jsp").forward(request, response);

		} else {
			response.sendRedirect("");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
