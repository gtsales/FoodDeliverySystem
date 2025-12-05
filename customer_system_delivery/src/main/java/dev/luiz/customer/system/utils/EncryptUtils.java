package dev.luiz.customer.system.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtils {

	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static String encode(String password) {

		return passwordEncoder.encode(password);
	}
}
