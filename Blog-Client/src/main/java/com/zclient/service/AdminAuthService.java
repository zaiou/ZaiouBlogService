package com.zclient.service;

import com.zclient.model.AdminAuth;

import java.util.Set;

/**
 * @Description:
 * @Author: liubin
 * @Date: Created in 22:52 2018/3/8
 * @Modified by:
 */
public interface AdminAuthService {
    /**
     * @Description: 用户登录
     * @Author: liubin
     * @Date: 2018/4/2 14:42
     * @param :
     * @return
    */
    AdminAuth login(String username, String password);

    /**
     * @Description:获取用户信息 通过用户id
     * @Author: liubin
     * @Date: 2018/4/11 20:22
     * @param :
     * @return
    */
    AdminAuth getAdminUserInfo(Integer id);

    /**
     * @Description:获取用户信息 通过用户用户名
     * @Author: liubin
     * @Date: 2018/4/22 14:26
     * @param :
     * @return
    */
    AdminAuth getAdminUserInfoByUsername(String username);

    /**
     * @Description:通过用户名获取用户角色
     * @Author: liubin
     * @Date: 2018/4/22 14:28
     * @param :
     * @return
    */
    public Set<String> getRolesByUsername(String username);

    /**
     * @Description:获取当前用户的所有权限
     * @Author: liubin
     * @Date: 2018/4/22 18:45
     * @param :
     * @return
    */
    public Set<String> getPermissionsByUsername(String username);
}
