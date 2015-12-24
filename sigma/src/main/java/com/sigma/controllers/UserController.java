package com.sigma.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sigma.service.IUserService;

@Controller
@RequestMapping({"/user", "/users"})
public class UserController {

	@Autowired @Qualifier("userService")
	private IUserService userService = null;
	
	/**
	 * 修改密码
	 * @param request
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String user(
			HttpServletRequest request
			){
		
		return "user";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String listUser(
			HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size
			){
		List<Map<String, Object>> userList = userService.getAllUserList(size * page, size);
		request.setAttribute("users", userList);
		
		return "admin-user-list";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String changePassword(
			HttpServletRequest request,
			@RequestParam(value="username") String username,
			@RequestParam(value="oldpassword") String oldpassword,
			@RequestParam(value="newpassword") String newpassword
			){
		
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("role") == null){
			return "redirect:../user";
		}
		
		Map<String, Object> user = userService.getUserByName(username);
		if(user == null){
			request.getSession().setAttribute("message", "没有此用户，请重新输入！");
			return "redirect:../user";
		}
		
		if(oldpassword.compareTo(user.get("PASSWORD").toString()) != 0){
			request.getSession().setAttribute("message", "密码输入错误，请重新输入！");
			return "redirect:../user";
		}
		
		userService.changePassword(user.get("ID").toString(), newpassword);
		
		return "login";
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
