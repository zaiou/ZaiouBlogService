package com.zclient.kits;

import com.zclient.model.AdminAuth;

/**
 * @Description:用户帮助类
 * @Author: liubin
 * @Date: Created in 22:24 2018/4/3
 * @Modified by:
 */
public class UserKit {
    public static final String ID = "userId";
    public static final String USERNAME = "userName";

    /**
     * @Description: 用户登录
     * @Author: liubin
     * @Date: 2018/4/3 22:37
     * @param :
     * @return
    */
    public static void login(AdminAuth adminAuth){
        SessionKit.setSessionAttr(ID,adminAuth.getId());
        SessionKit.setSessionAttr(USERNAME,adminAuth.getAdminname());
    }

    /**
     * @Description:判断用户是否登录
     * @Author: liubin
     * @Date: 2018/4/11 20:24
     * @param :
     * @return
    */
    public static boolean isLogin() {
        return SessionKit.getSessionAttr(ID)!=null;
    }

    /**
     * @Description:获取用户id
     * @Author: liubin
     * @Date: 2018/4/11 20:26
     * @param :
     * @return
    */
    public static Integer getUserId(){
        return SessionKit.getSessionAttr(ID);
    }

}
