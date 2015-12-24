package com.sigma.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户还原页
 */
@Controller
@RequestMapping(value = "/welcome")
public class WelcomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String welcome(HttpServletRequest request){
		return "welcome";
	}
}
