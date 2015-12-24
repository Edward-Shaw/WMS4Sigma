package com.sigma.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sigma.service.IUserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController{
	
	@Autowired @Qualifier("userService")
	private IUserService userService = null;
	
	/**
	 * 显示登录页面
	 * @param request
	 * @param backUrl
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response){
		
		Object message = request.getSession().getAttribute("message");
		
		if(message != null && !message.toString().equals("")){
			request.getSession().removeAttribute("message");
			request.setAttribute("msg", message.toString());
		}else{
			request.setAttribute("msg", "");
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "username", required = false) String userName,
			@RequestParam(value = "password", required = false) String password)
	{
		if(userService == null){
			return null;
		}
		
		Map<String, Object> user = userService.getUserByName(encodeStr(userName));
		if(user == null){
			request.getSession().setAttribute("message", "用户名和密码不匹配，请重新输入！");
			return "redirect:../login";
		}
		
		if(user.get("PASSWORD").toString().equals(password)){
			request.getSession().setAttribute("username", encodeStr(userName));
			request.getSession().setAttribute("role", user.get("ROLE").toString());
		}else{
			request.getSession().setAttribute("message", "用户名和密码不匹配，请重新输入！");
			return "redirect:../login";
		}
		
		if(user.get("ROLE").toString().equals("ADMIN")){
			return "redirect:../admin";
		}else if(user.get("ROLE").toString().equals("EDITOR")){
			return "redirect:../staff";
		}else{
			return "redirect:../visitor";
		}
		
		//TODO: 通过用户名获取到用户信息，主要是密码和权限
		//TODO: check用户名和密码是否匹配
		//TODO：根据权限跳转不同的页面
		//TODO: 将用户名和权限作为参数传递给相应的页面
		//return new ModelAndView(new RedirectView("../devices"));
	}
	 
    public static String encodeStr(String str) {    
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");    
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;    
        }    
    }
}