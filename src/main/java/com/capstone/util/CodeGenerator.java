package com.capstone.util;

import java.util.Random;

public class CodeGenerator {
	
	
	public static String generateCode() {
		String verificationCode = "";
		StringBuilder sb = new StringBuilder();
		
		while(sb.length()<=5) {
			Random aleatorio = new Random();
			Character character =  (char)( 97+aleatorio.nextInt( (122) - 97));
			sb.append(character);
		}
		verificationCode= sb.toString().toUpperCase();

		return verificationCode;
	}

}
