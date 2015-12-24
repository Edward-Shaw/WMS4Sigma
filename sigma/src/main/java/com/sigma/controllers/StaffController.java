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
import com.sigma.service.IRentLogService;
import com.sigma.service.IUserService;

@Controller
@RequestMapping(value = "/staff")
public class StaffController{
	@Autowired @Qualifier("deviceService")
	private IDeviceService deviceService = null;
	
	@Autowired @Qualifier("userService")
	private IUserService userService = null;
	
	@Autowired @Qualifier("rentLogService")
	private IRentLogService rentLogService = null;
	
	/**
	 * 显示管理员首页
	 * @param request
	 * @param backUrl
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String staff(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "status", defaultValue = "ALL") String status,
			@RequestParam(value = "serialcode", defaultValue = "ALL") String serialcode,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "500") int size){
		List<Map<String, ?>> devices = null;
		
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("EDITOR")){
			return "redirect:/login";
		}
		
		String user = request.getSession().getAttribute("username").toString();
		
		if(deviceService != null){
			devices = deviceService.getDeviceListByUser(user, status, serialcode, page, size);
		}
		
		List<Map<String, Object>> userList = userService.getUserList(size * page, size);
		if(userList != null && userList.size() > 0){
			request.setAttribute("paging", userList.get(0));
			userList.remove(0);
		}

		request.setAttribute("devices", devices);
		
		return "staff-devices";
	}
	
	/**
	 * 显示管理员首页
	 * @param request
	 * @param backUrl
	 * @return
	 */
	@RequestMapping(value="/log", method = RequestMethod.GET)
	public String log(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", defaultValue = "") String user,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "500") int size){
		List<Map<String, ?>> logs = null;
		
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("EDITOR")){
			return "redirect:/login";
		}
		user = request.getSession().getAttribute("username").toString();
		if(rentLogService != null){
			logs = rentLogService.getRentLogList(user, page, size);
		}
		
		request.setAttribute("logs", logs);
		
		return "staff-log";
	}
	
	@RequestMapping(value="/current", method = RequestMethod.GET)
	public String currentRentLog(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", defaultValue = "") String user,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "500") int size){
		List<Map<String, ?>> logs = null;
		
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("EDITOR")){
			return "redirect:/login";
		}
		
		user = request.getSession().getAttribute("username").toString();
		if(rentLogService != null){
			logs = rentLogService.getCurrentRentLogList(user, page, size);
		}
		
		request.setAttribute("logs", logs);
		
		return "current-rent";
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