package com.study.servlet.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello") //주소창에 /hello입력하면 이 클래스를 호출함 최초요청이 들어오면 객체가 생성 생성이 되었을때는 인잇이 실행
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Hello() {
    	
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("최초 1회만 호출");
	}

	public void destroy() {
		System.out.println("마지막 1회만 호출");
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "KangDaehyeop";

		System.out.println("요청이 들어올 때마다 호출");
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURI());
		response.getWriter().println("<html><head><title>Hello</title></head><body><h1>Hello Servlet</h1><h2>" + name + "</h2></body></html>");
	}

}
