package com.wpp.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

//일반사용자가 관리자 권한 페이지 이동시 처리 
public class AccessFailureHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(AccessFailureHandler.class);
	private String errorPage;

	public AccessFailureHandler() {
	}

	public AccessFailureHandler(String errorPage) {
		this.errorPage = errorPage;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
			throws IOException, ServletException {
	//권한에 맞지않는 접근 시도 시  403으로 처리 
		RequestDispatcher Rd = request.getRequestDispatcher("/error/403");
		Rd.forward(request, response);
		
	}
}
