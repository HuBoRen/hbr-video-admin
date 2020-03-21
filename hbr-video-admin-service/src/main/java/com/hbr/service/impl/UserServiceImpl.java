package com.hbr.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbr.mapper.UsersMapper;
import com.hbr.pojo.Users;
import com.hbr.pojo.UsersExample;
import com.hbr.pojo.UsersExample.Criteria;
import com.hbr.service.IUserService;
import com.hbr.utils.PagesResult;
@Service
public class UserServiceImpl implements IUserService{
@Autowired
private UsersMapper usersMapper;
@Autowired 
private Sid sid;
	@Override
	public PagesResult queryUsers(Users users, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		String username="";
		String nickname="";
		if(users!=null) {
			username=users.getUsername();
			nickname=users.getNickname();
		}
		PageHelper.startPage(page, pageSize);
		UsersExample example=new UsersExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(username)) {
			criteria.andUsernameLike("%"+username+"%");
		}
		if(StringUtils.isNotBlank(nickname)) {
			criteria.andNicknameLike("%"+nickname+"%");
		}
		List<Users> list = usersMapper.selectByExample(example);
		PageInfo<Users> pageList=new PageInfo<Users>(list);
		PagesResult result=new PagesResult();
		result.setPage(page);
		result.setTotal(pageList.getPages());
		result.setRecords(pageList.getTotal());
		result.setRows(list);
		return result;
	}
	@Override
	public void delUser(String userId) {
		// TODO Auto-generated method stub
		usersMapper.deleteByPrimaryKey(userId);
	}
	@Override
	public boolean queryUserIsExit(String username) {
		// TODO Auto-generated method stub
		UsersExample example=new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<Users> list = usersMapper.selectByExample(example);
		if(list!=null && list.size()>0) {
			return true;
		}
		return false;
	}
	@Override
	public void addUsers(Users users) {
		// TODO Auto-generated method stub
		String id=sid.nextShort();
		users.setId(id);
		users.setFansCounts(0);
		users.setReceiveLikeCounts(0);
		usersMapper.insert(users);
	}

}
