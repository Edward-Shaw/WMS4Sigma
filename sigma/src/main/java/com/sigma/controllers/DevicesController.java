package com.sigma.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sigma.service.IDeviceService;
import com.sigma.service.IUserService;

@Controller
@RequestMapping(value = "/devices")
public class DevicesController {

	@Autowired @Qualifier("deviceService")
	private IDeviceService deviceService = null;
	
	@Autowired @Qualifier("userService")
	private IUserService userService = null;
	
	/**
	 * 获取样机列表
	 * @param request
	 * @param status ALL || IN_STORE || UNDER_REPAIR || INTERNAL_BORROWING || EXTERNAL_BORROWING || SELL_OUT
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getDeviceList(HttpServletRequest request,
			@RequestParam(value = "status", defaultValue = "ALL") String status,
			@RequestParam(value = "user", defaultValue = "ALL") String user,
			@RequestParam(value = "serialcode", defaultValue = "ALL") String serialcode,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size
			){

		if(request.getSession().getAttribute("username") == null){
			return "redirect:/login";
		}
		
		List<Map<String, ?>> devices = null;
		
		if(deviceService != null){
			devices = deviceService.getDeviceList(status, user, serialcode, page, size);
			if(devices != null && devices.size() > 0){
				request.setAttribute("paging", devices.get(0));
				devices.remove(0);
			}
		}
		
		List<Map<String, Object>> userList = userService.getUserList(size * page, size);

		request.setAttribute("users", userList);
		request.setAttribute("devices", devices);
		
		if(request.getSession().getAttribute("role").toString().equals("READER")){
			return "visitor-devices";
		}else if(request.getSession().getAttribute("role").toString().equals("EDITOR")){
			return "staff-all-devices";
		}else if(request.getSession().getAttribute("role").toString().equals("ADMIN")){
			return "admin-devices";
		}else{
			return "login";
		}
	}
}
