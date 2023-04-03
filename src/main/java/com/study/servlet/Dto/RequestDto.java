package com.study.servlet.Dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class RequestDto<T> {

	public static <T> T convertRequestBody(HttpServletRequest request, Class<?> c) throws IOException {
		
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String requstBody = bufferedReader.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		T object = (T) gson.fromJson(requstBody, c);
		
		return object;
	}
}
