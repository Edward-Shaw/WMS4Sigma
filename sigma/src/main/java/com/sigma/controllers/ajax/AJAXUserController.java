package com.sigma.controllers.ajax;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sigma.api.common.APIResponse;
import com.sigma.service.IRentLogService;
import com.sigma.service.IUserService;

@Controller
@RequestMapping({"/users"})
public class AJAXUserController {
	
	@Autowired @Qualifier("userService") private IUserService userService = null;
	@Autowired @Qualifier("rentLogService") private IRentLogService rentLogService = null;
	
	/**
	 * 获得所有用户信息
	 * @param request
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public APIResponse listUsers(
			HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size
			){
		
		List<Map<String, Object>> userList = userService.getUserList(size * page, size);
		/*
		if(userList != null && userList.size() > 0){
			request.setAttribute("paging", userList.get(0));
			userList.remove(0);
		}*/

		return new APIResponse(APIResponse.RC_OK, userList);
	}
	
	/**
	 * 获得所有用户信息
	 * @param request
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/rent", method = RequestMethod.POST, params = {"company", "renter", "purpose", "rent-type", "phone", "rent-time", "except-time"})
	@ResponseBody
	public APIResponse rentLog(HttpServletRequest request,
			@RequestParam(value = "company", required = false, defaultValue = "") String company,
			@RequestParam(value = "renter", required = false, defaultValue = "") String renter,
			@RequestParam(value = "purpose", required = false, defaultValue = "") String purpose,
			@RequestParam(value = "renttype", required = false, defaultValue = "") String rent_type,
			@RequestParam(value = "phone", required = false, defaultValue = "") String phone,
			@RequestParam(value = "renttime", required = false, defaultValue = "") Timestamp rent_time,
			@RequestParam(value = "excepttime", required = false, defaultValue = "") Timestamp except_time
			){
		
		if(rentLogService == null){
			return new APIResponse(APIResponse.RC_INTERNAL_ERROR, "程序内部错误，请联系技术支持！");
		}
	
		int res = rentLogService.addRentLog(company, renter, purpose, rent_type, phone, rent_time, except_time);
		if(res != 0){
			return new APIResponse(APIResponse.RC_BAD_REQUEST, "新增借用单失败，请检查借用单内容！");
		}

		return new APIResponse(APIResponse.RC_OK, "操作成功！");
	}
	
	/**
	 * 获得所有用户信息
	 * @param request
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
	@ResponseBody
	public APIResponse modify(HttpServletRequest request,
			@PathVariable(value = "id") String id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "role", defaultValue = "") String role,
			@RequestParam(value = "department", defaultValue = "") String department
			){
		
		if(userService == null){
			return new APIResponse(APIResponse.RC_INTERNAL_ERROR, "程序内部错误，请联系技术支持！");
		}
	
		int res = userService.modify(id, name, role, department);
		if(res != 1){
			return new APIResponse(APIResponse.RC_BAD_REQUEST, "员工信息修改失败，请检查借用单内容！");
		}

		return new APIResponse(APIResponse.RC_OK, "操作成功！");
	}
	
	/**
	 * 获得所有用户信息
	 * @param request
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public APIResponse create(HttpServletRequest request,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "role", required = false, defaultValue = "") String role,
			@RequestParam(value = "department", required = false, defaultValue = "") String department,
			@RequestParam(value = "password", required = false, defaultValue = "") String password
			){
		
		if(userService == null){
			return new APIResponse(APIResponse.RC_INTERNAL_ERROR, "程序内部错误，请联系技术支持！");
		}
	
		int res = userService.create(name, role, department, password);
		if(res != 1){
			return new APIResponse(APIResponse.RC_BAD_REQUEST, "新增用户失败，请检查用户信息！");
		}

		return new APIResponse(APIResponse.RC_OK, "操作成功！");
	}
	
	/**
	 * 获得所有用户信息
	 * @param request
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public APIResponse delete(HttpServletRequest request,
			@PathVariable(value = "id") String id
			){
		
		if(userService == null){
			return new APIResponse(APIResponse.RC_INTERNAL_ERROR, "程序内部错误，请联系技术支持！");
		}
	
		int res = userService.delete(id);
		if(res != 1){
			return new APIResponse(APIResponse.RC_BAD_REQUEST, "删除用户失败！");
		}

		return new APIResponse(APIResponse.RC_OK, "操作成功！");
	}
}