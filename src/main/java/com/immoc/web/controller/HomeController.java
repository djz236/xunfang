package com.immoc.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.immoc.base.ApiResponse;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		request.setAttribute("name",request.getParameter("name"));
		return "index";
	}
	 @GetMapping("/404")
	 public String notFoundPage(){
		 return "404";
	 }
	 @GetMapping("/403")
	 public String accessError(){
		 return "403";
	 }
	 @GetMapping("/500")
	 public String internalError(){
		 return "500";
	 }
	 @GetMapping("/logout/page")
	 public String logoutPage(){
		 return "logout";
	 }
}
