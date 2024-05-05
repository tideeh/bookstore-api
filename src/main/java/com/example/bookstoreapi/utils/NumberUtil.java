package com.example.bookstoreapi.utils;

public class NumberUtil {

	public static int getIntOrValue(String str, int value) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return value;
		}
	}
	
}