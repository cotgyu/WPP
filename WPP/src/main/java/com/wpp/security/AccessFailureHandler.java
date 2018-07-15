package com.wpp.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

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
		String accept = request.getHeader("accept");
		String error = "true";
		String message = exception.getMessage();
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setCharacterEncoding("EUC-KR");
		String data = StringUtils.join(new String[] { "권한이 없습니다. 정상적인 경로로 이용해주세요! " });
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
	}
}
