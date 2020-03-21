package com.hbr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbr.mapper.AdminMapper;
import com.hbr.pojo.Admin;
import com.hbr.pojo.AdminExample;
import com.hbr.pojo.AdminExample.Criteria;
import com.hbr.service.IAdminService;
import com.hbr.utils.HbrJSONResult;
import com.hbr.utils.MD5Utils;
@Service
public class AdminServiceImpl implements IAdminService{
	@Autowired
private AdminMapper adminMapper;
	@Override
	public List<Admin> queryAdminIsLogin(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		AdminExample adminExample=new AdminExample();
		Criteria criteria = adminExample.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		List<Admin> list = adminMapper.selectByExample(adminExample);
		return list;
	}

}
