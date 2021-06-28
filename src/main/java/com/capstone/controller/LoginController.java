package com.capstone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.dao.ApplicationDao;
import com.capstone.dao.DaoService;
import com.capstone.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	DaoService daoService;
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		daoService = new ApplicationDao();
		String user = request.getParameter("uname");
		String password = request.getParameter("psw");
		List<User> users = new ArrayList<User>();
		
		
//		try {
//			ResultSet rs =  MysqlConnection.getConnection().prepareStatement("Select * from user").executeQuery();
//			while (rs.next()) {
//				User entityU = new User(null, rs.getString("username"), null, null);
//				
//				users.add(entityU);
//				
//			}
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		
//		if (daoService.getUsers().stream().anyMatch(item -> item.getUsername().equalsIgnoreCase(user))) {
//			System.out.println("welcome " + users.get(0).getUsername());
//		}
//			
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}