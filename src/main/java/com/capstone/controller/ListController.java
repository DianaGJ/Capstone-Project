package com.capstone.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capstone.dao.ApplicationService;
import com.capstone.dao.ApplicationServiceImpl;
import com.capstone.dbconnection.MysqlConnection;
import com.capstone.model.Password;
import com.capstone.model.User;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/listServlet")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	ApplicationService applicationService;

	public ListController() {
		applicationService = new ApplicationServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String instruccion = request.getParameter("instruccion");
		if (instruccion != null) {

			switch (instruccion) {
			case "edit":
				
				try {
					edit(request,response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			case "delete":
				try {
					delete(request,response);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Password password = applicationService.getPassword(id);
		request.setAttribute("password", password);
		request.getRequestDispatcher("details.jsp").forward(request, response);
		
		
		
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		applicationService.deletePassword(id);
		response.sendRedirect("list");
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		List<User> users = new ArrayList<User>();
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

			passwords = applicationService.getAllPasswords(Integer.valueOf(userId));
			request.setAttribute("passwords", passwords);
			request.setAttribute("user", username);
			request.getRequestDispatcher("/list.jsp").forward(request, response);

		} else {
			response.sendRedirect("");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
