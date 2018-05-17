package com.zclient.controller;

import com.zclient.api.ApiResult;
import com.zclient.kits.UserKit;
import com.zclient.model.AdminAuth;
import com.zclient.service.AdminAuthService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 后台管理
 * @Author: liubin
 * @Date: Created in 22:50 2018/3/8
 * @Modified by:
 */
@RequestMapping(value = "/admin/")
@RestController
public class AdminAuthController {
    @Autowired
    private AdminAuthService adminAuthService;

    /**
     * @Description:后台登陆
     * @Author: liubin
     * @Date: 2018/4/2 23:01
     * @param :
     * @return
    */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ApiResult<AdminAuth> login(@RequestParam String username, @RequestParam String password){
        if(StringUtils.isAnyEmpty(username,password)){
            return  new ApiResult<>(-1,"用户名或者密码不能为空");
        }

        //把用戶信息放入AuthenticationToken中
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(username,password) ;

        AdminAuth adminAuth=adminAuthService.login(username,password);
        boolean result=adminAuth!=null;
        if (!result){
            return new ApiResult<>(-1,"请输入正确的用户名或者密码");
        }else {
            subject.login(token);

            UserKit.login(adminAuth);
            return new ApiResult<>(adminAuth);
        }
    }

    /**
     * @Description:获取用户信息
     * @Author: liubin
     * @Date: 2018/4/11 20:26
     * @param :
     * @return
    */
    @RequestMapping(value = "/info",method= RequestMethod.GET)
    public ApiResult<AdminAuth> getAdminUserInfo(){
        if (!UserKit.isLogin()) {
            return new ApiResult<>(-1,"用户未登录");
        }
        AdminAuth adminAuth=adminAuthService.getAdminUserInfo(UserKit.getUserId());
        return new ApiResult<>(adminAuth);
    }
}

