package com.sigma.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sigma.service.IDeviceService;
import com.sigma.service.IUserService;

@Controller
@RequestMapping(value = "/visitor")
public class VisitorController{
	
	@Autowired @Qualifier("deviceService")
	private IDeviceService deviceService = null;
	
	@Autowired @Qualifier("userService")
	private IUserService userService = null;
	
	/**
	 * 显示管理员首页
	 * @param request
	 * @param backUrl
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String admin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "status", defaultValue = "ALL") String status,
			@RequestParam(value = "user", defaultValue = "ALL") String user,
			@RequestParam(value = "serialcode", defaultValue = "ALL") String serialcode,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "1000") int size){
		
		List<Map<String, ?>> devices = null;
		
		if(deviceService != null){
			devices = deviceService.getDeviceList(status, user, serialcode, page, size);
			if(devices != null && devices.size() > 0){
				devices.remove(0);
			}
		}
		
		List<Map<String, Object>> userList = userService.getUserList(size * page, size);
		if(userList != null && userList.size() > 0){
			request.setAttribute("paging", userList.get(0));
			userList.remove(0);
		}

		request.setAttribute("users", userList);
		request.setAttribute("devices", devices);
		
		return "visitor-devices";
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
			return "login";
		}
		
		if(user.get("PASSWORD").toString().equals(password)){
			request.getSession().setAttribute("username", encodeStr(userName));
			request.getSession().setAttribute("role", user.get("ROLE").toString());
		}else{
			return "login";
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