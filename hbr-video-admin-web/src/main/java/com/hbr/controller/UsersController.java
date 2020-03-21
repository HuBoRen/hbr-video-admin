package com.hbr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbr.enums.VideoStatusEnum;
import com.hbr.pojo.Users;
import com.hbr.service.IUserService;
import com.hbr.service.IVideoService;
import com.hbr.service.impl.UserServiceImpl;
import com.hbr.service.impl.VideoServiceImpl;
import com.hbr.utils.HbrJSONResult;
import com.hbr.utils.MD5Utils;
import com.hbr.utils.PagesResult;

@Controller
@RequestMapping("users")
public class UsersController {
@Autowired
private IUserService userService;
@Autowired
private IVideoService videoService;
	@GetMapping("/showList")
	public String showList() {
		return "users/usersList";
	}
	@GetMapping("/showAddUser")
	public String showAddUser() {
		return "users/addUser";
	}
	
	
	@PostMapping("addUser")
	@ResponseBody
	public HbrJSONResult addUser(String username,String password,String nickname) throws Exception {
		boolean flag = userService.queryUserIsExit(username);
		if(!flag) {
			Users users = new Users();
			users.setUsername(username);
			users.setPassword(MD5Utils.getMD5Str(password));
			users.setNickname(nickname);
			userService.addUsers(users);
			return HbrJSONResult.ok();
		}
		return HbrJSONResult.errorMsg("您好，该用户名已经存在，请核对再添加");
	}
	@PostMapping("/list")
	@ResponseBody
	public PagesResult list(Users users,Integer page) {
		PagesResult result = userService.queryUsers(users, page == null ? 1 : page, 10);
		return result;
	}
	
	@PostMapping("/delUser")
	@ResponseBody
	public HbrJSONResult delUser(String userId) {
		userService.delUser(userId);
		return HbrJSONResult.ok();
	}
	
	@PostMapping("/queryUserName")
	@ResponseBody
	public HbrJSONResult queryUserName(String username) {
		boolean flag = userService.queryUserIsExit(username);
		if(flag) {
			return HbrJSONResult.errorMsg("对不起，请您换个用户名，该用户名已经存在");
		}
		return HbrJSONResult.ok();
	}
}
