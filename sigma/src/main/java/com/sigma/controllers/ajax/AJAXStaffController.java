package com.sigma.controllers.ajax;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sigma.api.common.APIResponse;
import com.sigma.service.IDeviceService;

@Controller
@RequestMapping({"/staff"})
public class AJAXStaffController {
	
	@Autowired @Qualifier("deviceService") private IDeviceService deviceService = null;
	
	/**
	 * 在表device中插入一条记录
	 * @param request
	 * @param name
	 * @param key
	 * @param des description
	 * @return
	 */
	@RequestMapping(value = "/rent/{id}", method = {RequestMethod.POST})
	@ResponseBody
	public APIResponse rent(HttpServletRequest request,
			@PathVariable(value = "id") String id,
			@RequestParam(value = "renter", defaultValue = "") String renter,
			@RequestParam(value = "company", defaultValue = "") String company,
			@RequestParam(value = "position", defaultValue = "") String position,
			@RequestParam(value = "phone", defaultValue = "") String phone,
			@RequestParam(value = "purpose", defaultValue = "") String purpose,
			@RequestParam(value = "rent_type", defaultValue = "") String rent_type,
			@RequestParam(value = "renttime", defaultValue = "") String rent_time,
			@RequestParam(value = "expecttime", defaultValue = "") String expect_time){
		
		int code = APIResponse.RC_BAD_REQUEST;
		Map<String, Object> result = new HashMap<String, Object>();

		if(request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("EDITOR")){
			return new APIResponse(-1, "您无此操作权限！");
		}
		
		do{
			code = deviceService.rentDevice(id, renter, company, position, phone, purpose, rent_type, rent_time, expect_time);
			if (code != 1) {
				result.put("MESSAGE", "无法出借样品！");
				code = APIResponse.RC_BAD_REQUEST;
				break;
			}
			
			code = APIResponse.RC_OK;
			result.put("MESSAGE", "出借记录添加成功！");
		}while(false);
		
		return new APIResponse(code, result);
	}
	
	@RequestMapping(value = "/instore/{id}", method = {RequestMethod.POST})
	@ResponseBody
	public APIResponse instore(HttpServletRequest request,
			@PathVariable(value = "id") String id,
			@RequestParam(value = "actualtime", defaultValue = "") String actualTime,
			@RequestParam(value = "status", defaultValue = "") String status){
		
		int code = APIResponse.RC_BAD_REQUEST;
		Map<String, Object> result = new HashMap<String, Object>();

		if(request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("EDITOR")){
			return new APIResponse(-1, "您无此操作权限！");
		}
		
		do{
			code = deviceService.instore(id, actualTime, status);
			if (code != 1) {
				result.put("MESSAGE", "操作失败！");
				code = APIResponse.RC_BAD_REQUEST;
				break;
			}
			
			code = APIResponse.RC_OK;
			result.put("MESSAGE", "成功入库！");
		}while(false);
		
		return new APIResponse(code, result);
	}
	
	/**
	 * 在表device中插入一条记录
	 * @param request
	 * @param name
	 * @param key
	 * @param des description
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = {RequestMethod.POST})
	@ResponseBody
	public APIResponse modify(HttpServletRequest request,
			@PathVariable(value = "id") String id,
			@RequestParam(value = "model", defaultValue = "") String model,
			@RequestParam(value = "assetcode", defaultValue = "") String asset_code,
			@RequestParam(value = "serialnum1", defaultValue = "") String serial_num1,
			@RequestParam(value = "serialnum2", defaultValue = "") String serial_num2,
			@RequestParam(value = "kakou1", defaultValue = "") String kakou1,
			@RequestParam(value = "kakou2", defaultValue = "") String kakou2,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "count", defaultValue = "0") Integer count,
			@RequestParam(value = "quality", defaultValue = "") String quality){
		int code = APIResponse.RC_BAD_REQUEST;
		Map<String, Object> result = new HashMap<String, Object>();

		name = name.toUpperCase();
		
		if(request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("ADMIN")){
			return new APIResponse(-1, "您无此操作权限！");
		}
		
		do{
			code = deviceService.modifyDevice(id, model, asset_code, serial_num1, serial_num2, kakou1, kakou2, name, count, quality);
			if (code != 1) {
				result.put("MESSAGE", "样品更新失败！");
				code = APIResponse.RC_BAD_REQUEST;
				break;
			}
			
			code = APIResponse.RC_OK;
			result.put("MESSAGE", "INSERT SUCCESS");
		}while(false);
		
		return new APIResponse(code, result);
	}
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.POST}, params = {"manager"})
	@ResponseBody
	public APIResponse distribute(HttpServletRequest request,
			@PathVariable(value = "id") String id,
			@RequestParam(value = "manager", defaultValue = "") String manager){
		int code = APIResponse.RC_BAD_REQUEST;
		Map<String, Object> result = new HashMap<String, Object>();

		if(request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("ADMIN")){
			return new APIResponse(-1, "您无此操作权限！");
		}
		
		do{
			code = deviceService.distribute(id, manager);
			if (code != 1) {
				result.put("MESSAGE", "样品担当更新失败！");
				code = APIResponse.RC_BAD_REQUEST;
				break;
			}
			
			code = APIResponse.RC_OK;
			result.put("MESSAGE", "样品担当变更成功！");
		}while(false);
		
		return new APIResponse(code, result);
	}
	
	/**
	 * 获取指定Device的信息
	 * @param request
	 * @param name
	 * @param key
	 * @param des description
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET})
	@ResponseBody
	public APIResponse get(HttpServletRequest request,
			@PathVariable(value = "id") String id){
		if(this.deviceService == null){
			return new APIResponse(APIResponse.RC_BAD_REQUEST, "Device service is not available!");
		}
		
		Map<String, Object> device = deviceService.getDeviceById(id);
		
		return new APIResponse(APIResponse.RC_OK, device);
	}
}