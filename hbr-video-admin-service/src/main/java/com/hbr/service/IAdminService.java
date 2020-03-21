package com.hbr.service;

import java.util.List;

import com.hbr.pojo.Admin;
import com.hbr.utils.HbrJSONResult;

public interface IAdminService {
public List<Admin> queryAdminIsLogin(String username,String password)throws Exception;


}
