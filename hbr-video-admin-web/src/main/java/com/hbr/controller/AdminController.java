package com.hbr.controller;

import java.util.List;
import java.util.UUID;

import javax.jws.HandlerChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbr.bean.AdminUser;
import com.hbr.pojo.Admin;
import com.hbr.pojo.vo.AdminVO;
import com.hbr.service.IAdminService;
import com.hbr.utils.HbrJSONResult;

@Controller
@RequestMapping("users")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	
	
	
@GetMapping("/login")
public String login() {
	return "login";
}

//@PostMapping("login")
//@ResponseBody
//public HbrJSONResult login(Admin admin) throws Exception {
//	String username=admin.getUsername();
//	String password=admin.getPassword();
//	if(StringUtils.isBlank(username)||StringUtils.isBlank(password)) {
//		HbrJSONResult.errorMsg("用户名或者密码不能为空");
//	}
//	List<Admin> list = adminService.queryAdminIsLogin(username, password);
//	if(list==null||list.size()==0) {
//		HbrJSONResult.errorMsg("用户名或者密码错误");
//	}
//	Admin admin1 = list.get(0);
//	String adminToken=UUID.randomUUID().toString();
//	jedisClient.set(ADMIN_REDIS_SESSION + ":" + admin1.getId(), adminToken);
//	jedisClient.expire(ADMIN_REDIS_SESSION + ":" + admin1.getId(), 1000 * 60 * 30);
//	AdminVO adminVO=new AdminVO();
//	BeanUtils.copyProperties(admin1, adminVO);
//	adminVO.setAdminToken(adminToken);
//	return HbrJSONResult.ok(adminVO);
//
	
	
//}
@PostMapping("login")
@ResponseBody
public HbrJSONResult userLogin(Admin admin,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	
	if (StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())) {
		return HbrJSONResult.errorMap("用户名和密码不能为空");
	} 
	List<Admin> list = adminService.queryAdminIsLogin(admin.getUsername(), admin.getPassword());
	if(list==null||list.size()==0) {
		return HbrJSONResult.errorMsg("用户名或者密码不正确");
	}
	Admin admin1 = list.get(0);
	request.getSession().setAttribute("sessionUser", admin1);
	admin.setPassword("");
	
	return HbrJSONResult.ok();
}
@GetMapping("logout")
public String logout(HttpServletRequest request) {
	request.getSession().removeAttribute("sessionUser");
	return "login";
}
}
