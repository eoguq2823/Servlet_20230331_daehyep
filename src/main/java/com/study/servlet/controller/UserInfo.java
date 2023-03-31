package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/user")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	get요청 받을수있는녀석
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // 얘는 보통 젤위에 올라간다.
		System.out.println("Get 요청");
//		System.out.println(request.getParameter("name"));		
//		System.out.println(request.getParameter("phone"));
		
//		Json 만들기 1
		Gson gson = new Gson();
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");

//		Json 2
		Map<String, String> userMap = new HashMap<>();
		userMap.put("name", name);
		userMap.put("phone", phone);
		
		String userJson = gson.toJson(userMap);
		System.out.println(userJson);
		
//		System.out.println(name);
		
//		Get 요청 특징
//		1. 주소창, src, href, replace 등으로 요청할 수 있음.
//		2. 데이터를 전달하는 방법(Query String) 
//		방법- http(s)://서버주소:포트/요청메세지?key=value (http://www.example.com/search?q=apple&category=fruits)
		System.out.println(response.getCharacterEncoding());
		
//		response.addHeader("test", "test data");
//		response.addHeader("Content-Type", "text/html;charset=UTF-8"); 밑에랑 동일
//		response.setContentType("text/html;charset=UTF-8"); 			HTML 응답
		
//		Json 응답
		response.setContentType("application/json;charset=UTF-8");
		
//		바디 (실질적인 응답데이터)
		PrintWriter out = response.getWriter();
		out.println("이름: " + name);
		out.println("연락처: " + phone);
		
//		Json
		out.println(userJson);
	}
//		post요청 받을수있는녀석
//		form 태그에 메소드로 바꾸면 사용가능
//		비동기 통신을 (자바스크립트, 리액트) 코드안에서 통신을 요청 할 수 있다.
//		테스트툴을 사용 (포스트맨)
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post 요청");
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("address"));
		
		Gson gson = new Gson();
//		Json으로 받는법		
		ServletInputStream inputStream = request.getInputStream(); //외부에서 안으로 들어오기때문에 InputStream해줘야함
		BufferedReader bufferdreader = new BufferedReader(new InputStreamReader(inputStream));
//		1번방법
		String json = "";
		String line = null;
		while((line = bufferdreader.readLine()) != null) {
			json += line;
		}
		json.replaceAll(" ", ""); //띄어쓰기 변경
		System.out.println(json);
		
//		2번방법
//		AtomicReference<String> jsonAtomic = new AtomicReference<>("");
//		bufferdreader.lines().forEach(line -> {
//			jsonAtomic.getAndUpdate(t -> t + line);
//		});
//		String json = jsonAtomic.get().replaceAll(" ","");
//		System.out.println(json);
		
//		3번방법
//		String json = bufferdreader.lines().collect(Collectors.joining()); 콜렉터 사용해서 한줄 정리
		
		Map<String, String> jsonMap = gson.fromJson(json, Map.class);
		System.out.println(jsonMap);
		
		//Post 요청 특징
//		 1. <form action="요청메세지" method="post">
//				<input name="key" value="value">
//				<button type="submit">요청</button>
//			</form>
		
//		get 요청을 할때는 Json을 안쓴다
//		언제 쓰냐? post요청, put요청할때만 Json으로 보낸다
//		요청에 대한 응답은 다 Json으로 받을것이다.
		
		
	}
}
