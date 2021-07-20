package com.capstone;

import java.util.Random;

public class PINGenerator implements PasswordGenerator {
	
	@Override
	public String generate(int length) {
		StringBuilder stringBuilder = new StringBuilder(length);
		Random random = new Random();
		
		while (stringBuilder.length() < length) {
			int character = random.nextInt(10);
			stringBuilder.append(character);
		}
		
		return stringBuilder.toString();
	}
}
