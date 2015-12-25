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

import com.sigma.service.IDeviceLogService;
import com.sigma.service.IDeviceService;
import com.sigma.service.IRentLogService;
import com.sigma.service.IUserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController{
	@Autowired @Qualifier("deviceService")
	private IDeviceService deviceService = null;
	
	@Autowired @Qualifier("deviceLogService")
	private IDeviceLogService deviceLogService = null;
	
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
	public String admin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "status", defaultValue = "ALL") String status,
			@RequestParam(value = "user", defaultValue = "ALL") String user,
			@RequestParam(value = "serialcode", defaultValue = "ALL") String serialcode,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "2500") int size){
		List<Map<String, ?>> devices = null;
		
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("ADMIN")){
			return "redirect:/login";
		}
		
		if(deviceService != null){
			devices = deviceService.getDeviceList(status, user, serialcode, page, size);
			if(devices != null && devices.size() > 0){
				devices.remove(0);
			}
		}
		
		List<Map<String, Object>> userList = userService.getUserList(size * page, size);

		request.setAttribute("users", userList);
		request.setAttribute("devices", devices);
		
		return "admin-devices";
	}
	
	/**
	 * 显示管理员首页
	 * @param request
	 * @param backUrl
	 * @return
	 */
	@RequestMapping(value="/log", method = RequestMethod.GET)
	public String log(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "type", defaultValue = "ALL") String type,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "2500") int size){
		List<Map<String, ?>> logs = null;
		
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("ADMIN")){
			return "redirect:/login";
		}
		
		if(deviceLogService != null){
			logs = deviceLogService.getDeviceLogList(type, page, size);
		}
		
		request.setAttribute("logs", logs);
		
		return "admin-log";
	}
	
	@RequestMapping(value="/current-rent", method = RequestMethod.GET)
	public String currentRent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "2500") int size){
		List<Map<String, ?>> logs = null;
		
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("ADMIN")){
			return "redirect:/login";
		}
		
		if(rentLogService != null){
			logs = rentLogService.getCurrentRentLogList(type, page, size);
		}
		
		request.setAttribute("logs", logs);
		
		return "admin-current-rent";
	}
	
	@RequestMapping(value="/rent-log", method = RequestMethod.GET)
	public String rentLog(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "user", defaultValue = "") String user,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "2500") int size){
		List<Map<String, ?>> logs = null;
		
		if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("ADMIN")){
			return "redirect:/login";
		}
		
		if(rentLogService != null){
			logs = rentLogService.getRentLogList(user, page, size);
		}
		
		request.setAttribute("logs", logs);
		
		return "admin-rent-log";
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