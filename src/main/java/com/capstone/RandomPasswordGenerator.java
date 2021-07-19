package com.capstone;

import java.util.Random;

public class RandomPasswordGenerator implements PasswordGenerator {
	private static final String VALID_CHARACTERS = "abcdefghijklmonpqrstuvwxyz0123456789!@#$%^&";
	
	@Override
	public String generate(int length) {
		StringBuilder stringBuilder = new StringBuilder(length);
		Random random = new Random();
		
		while (stringBuilder.length() < length) {
			int index = random.nextInt(VALID_CHARACTERS.length());
			char character = VALID_CHARACTERS.charAt(index);
			
			if (Character.isLetter(character)) {
				if (random.nextBoolean()) {
					stringBuilder.append(Character.toUpperCase(character));
				}
				else {
					stringBuilder.append(character);
				}
			}
			else {
				stringBuilder.append(character);
			}
		}
		
		return stringBuilder.toString();
	}
}
