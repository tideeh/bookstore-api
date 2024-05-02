package com.example.bookstoreapi.utils;

import com.google.gson.Gson;

public class JsonUtil {
	
	public static <T> T jsonToObject(String json, Class<T> classOfT) {
		return (T) new Gson().fromJson(json, classOfT);
	}
	
}
