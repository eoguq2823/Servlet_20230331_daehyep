package com.study.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.servlet.Dto.RequestDto;
import com.study.servlet.Dto.ResponseDto;
import com.study.servlet.entity.User;
import com.study.servlet.service.UserService;
import com.study.servlet.service.UserServiceImpl;

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
	private UserService userService;
	private Gson gson;
	
	public Authentication() {
		userService = UserServiceImpl.getInstance();
		gson = new Gson();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = RequestDto.<User>convertRequestBody(request, User.class);
//		Map<String, String> map = RequestDto.<Map<String, String>>convertRequestBody(request, Map.class);
//		System.out.println(user);
//		System.out.println("map:" + map);
	
		boolean duplicatedFlag = userService.duplicatedUsername(user.getUsername());
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(duplicatedFlag) {
//			true == 중복, false == 가입가능
			ResponseDto<Boolean> responseDto = new ResponseDto<Boolean>(400, "duplicated username", duplicatedFlag);
			out.println(gson.toJson(responseDto));
			return; // 리턴안해주면 밑에까지 계속타고 내려간다.
		}
		userService.addUser(user);
		
		
	}
}
