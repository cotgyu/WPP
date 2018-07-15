package com.wpp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	@RequestMapping("403")
	public String Error403(){
		return("/commons/error403");
	}
	@RequestMapping("404")
	public String Error404(){
		return("/commons/error404");
	}
	@RequestMapping("405")
	public String Error405(){
		return("/commons/error405");
	}
	@RequestMapping("500")
	public String Error500(){
		return("/commons/error500");
	}
}
