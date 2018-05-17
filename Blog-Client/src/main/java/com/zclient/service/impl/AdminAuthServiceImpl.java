package com.zclient.service.impl;

import com.zclient.mapper.AdminAuthMapper;
import com.zclient.mapper.AdminPermissionMapper;
import com.zclient.mapper.AdminRoleMapper;
import com.zclient.model.AdminAuth;
import com.zclient.service.AdminAuthService;
import com.zclient.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Description: 后台用户管理
 * @Author: liubin
 * @Date: Created in 22:53 2018/3/8
 * @Modified by:
 */
@Service
public class AdminAuthServiceImpl implements AdminAuthService {
    @Autowired
    private AdminAuthMapper adminAuthMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private AdminPermissionMapper adminPermissionMapper;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * @Description: 用户登录
     * @Author: liubin
     * @Date: 2018/4/2 14:42
     * @param :
     * @return
    */
    @Override
    public AdminAuth login(String username, String password) {
        AdminAuth adminAuth =  adminAuthMapper.login(username,password);
        redisUtils.set("adminAuth",adminAuth);
        return adminAuth;
    }

    /**
     * @Description:获取用户信息 通过用户id
     * @Author: liubin
     * @Date: 2018/4/22 11:56
     * @param :
     * @return
    */
    @Override
    public AdminAuth getAdminUserInfo(Integer id) {
        return adminAuthMapper.selectByPrimaryKey(id);
    }

    /**
     * @Description: 获取用户信息 通过用户名
     * @Author: liubin
     * @Date: 2018/4/22 11:57
     * @param :
     * @return
    */
    @Override
    public AdminAuth getAdminUserInfoByUsername(String username) {
        return adminAuthMapper.getAdminUserInfoByUsername(username);
    }

    /**
     * @Description:获取当前用户的所有角色
     * @Author: liubin
     * @Date: 2018/4/22 18:44
     * @param :
     * @return
    */
    @Override
    public Set<String> getRolesByUsername(String username) {
        return adminRoleMapper.getRolesByUsername(username);
    }

    /**
     * @Description:获取当前用户的所有权限
     * @Author: liubin
     * @Date: 2018/4/22 18:45
     * @param :
     * @return
    */
    @Override
    public Set<String> getPermissionsByUsername(String username) {
        return adminPermissionMapper.getPermissionsByUsername(username);
    }


}
