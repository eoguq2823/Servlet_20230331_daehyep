package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.study.servlet.entity.User;

//	servlet : Authentication
//	url : /auth
//	method: get / post
//
//	get 요청
//	요청데이터 : 쿼리파람
//	doGet()
//	UserLlist에서 username을 통해 사용자 정보 찾기
//	응답 데이터 : Json(User)
//
//	Post요청
//	요청 데이터 : Json(User)
//	doPost()
//	JSON을 User객체로 변환 후 ToString으로 출력
//	응답 데이터 : JSON {"success" : "true"}

@WebServlet("/auth")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		postmen에 주소 = http://localhost:8080/Servlet_Study_20230331/auth?username=aaa
//		request로 aaa를 받으면 aaa가 들어온다.
		String username = request.getParameter("username");
		System.out.println(username); //aaa가 들어왔는지 찍어보기
		
		List<User> userList = new ArrayList<>();
		userList.add(new User("aaa", "1234", "a", "aaa@gmail.com"));
		userList.add(new User("bbb", "1234", "b", "bbb@gmail.com"));
		userList.add(new User("ccc", "1234", "c", "ccc@gmail.com"));
		userList.add(new User("ddd", "1234", "d", "ddd@gmail.com"));
		System.out.println(userList); //userList안에 잘 들어왔는지 찍어보기
		
		User findUser = null;
		
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				findUser = user;
				System.out.println("findUser:"+findUser);
				break;
			}
		}
		
		Gson gson = new Gson();
		
		String userJson = gson.toJson(findUser);
		System.out.println("userJson:" + userJson);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(userJson);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		String requstBody = bufferedReader.lines().collect(Collectors.joining());
		Gson gson = new Gson();
		User user = gson.fromJson(requstBody, User.class);
		
		System.out.println("user:" + user);
		
		response.setContentType("application/json;charrset=UTF-8");	 //Writer하기전에 항상 해야한다.
		PrintWriter out = response.getWriter();
		
		JsonObject respJsonBody = new JsonObject();
		
		if(user == null) {
			respJsonBody.addProperty("success", false);
			
		} else {
			respJsonBody.addProperty("success", true);
		}
		
		out.println(respJsonBody.toString());
		
	}
}
