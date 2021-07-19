package com.capstone.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.PasswordGenerator;
import com.capstone.RandomPasswordGenerator;


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
		
		PasswordGenerator passwordGenerator = getGenerator(generationMethod);
		String password = passwordGenerator.generate(length);
		
		response.getWriter().print(password);
	}
	
	private PasswordGenerator getGenerator(String method) {
		if (method == "random") {
			return new RandomPasswordGenerator();
		}
		
		return new RandomPasswordGenerator();
	}
}
