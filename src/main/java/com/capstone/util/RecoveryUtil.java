package com.capstone.util;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java
 * https://docs.oracle.com/javase/8/docs/api/java/util/Base64.html
 * @author patki
 *
 */
public final class RecoveryUtil {
	private static final Encoder encoder = Base64.getUrlEncoder();
	private static final Decoder decoder = Base64.getUrlDecoder();
	
	private static final long CODE_EXPIRY_MINUTES = 60;
	
	public static String encodeUrl(String email) {
		byte[] emailBytes = email.getBytes();
		LocalDateTime now = LocalDateTime.now();
		
		final ByteBuffer buffer = ByteBuffer.allocate(emailBytes.length + Long.BYTES);
		buffer.put(emailBytes);
		buffer.putLong(now.toEpochSecond(ZoneOffset.UTC));
		
		return encoder.encodeToString(buffer.array());
	}
	
	public static String decodeUrl(String code) {
		String email = null;
		
		try {		
			byte[] bytes = decoder.decode(code);
			
			if (bytes.length > Long.BYTES) {			
				final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
				buffer.put(bytes, bytes.length - Long.BYTES, Long.BYTES);
				buffer.flip();
				
				long time = buffer.getLong();
				LocalDateTime dateTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.UTC);
				
				if (LocalDateTime.now().isBefore(dateTime.plusMinutes(CODE_EXPIRY_MINUTES))) {
					email = new String(bytes, 0, bytes.length - Long.BYTES);
				}
			}
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return email;
	}
}
