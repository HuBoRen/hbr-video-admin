package com.hbr.service;

import com.hbr.pojo.Users;
import com.hbr.utils.PagesResult;

public interface IUserService {
public PagesResult queryUsers(Users users,Integer page,Integer pageSize);
public void delUser(String userId);
public boolean queryUserIsExit(String username);
public void addUsers(Users users);
}
