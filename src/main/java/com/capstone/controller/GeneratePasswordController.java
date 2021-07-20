package com.capstone.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.PINGenerator;
import com.capstone.PasswordGenerator;
import com.capstone.RandomPasswordGenerator;

@WebServlet("/GeneratePassword")
public class GeneratePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GeneratePasswordController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String lengthString = request.getParameter("length");
		String generationMethod = request.getParameter("method");
		
		int length = 10;
		
		if (lengthString != null) {
			length = Integer.valueOf(lengthString);
		}
		
		if (generationMethod == null) {
			generationMethod = "random";
		}
		
		PasswordGenerator passwordGenerator = getGenerator(generationMethod);
		String password = passwordGenerator.generate(length);
		
		request.setAttribute("password", password);
		request.setAttribute("length", length);
		request.setAttribute("method", generationMethod);
		request.getRequestDispatcher("generate_password.jsp").forward(request, response);
	}
	
	private PasswordGenerator getGenerator(String method) {
		if (method != null) {
			if (method.equalsIgnoreCase("pin")) {
				return new PINGenerator();
			}
		}
		
		return new RandomPasswordGenerator();
	}
}
