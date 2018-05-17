package com.zclient.kits;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description:缓存相关信息
 * @Author: liubin
 * @Date: Created in 22:38 2018/4/3
 * @Modified by:
 */
public class SessionKit {
    /**
     * @Description:设置sesson属性
     * @Author: liubin
     * @Date: 2018/4/3 22:47
     * @param :
     * @return
    */
    public static void setSessionAttr(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * @Description:获取session属性
     * @Author: liubin
     * @Date: 2018/4/11 20:15
     * @param :
     * @return
    */
    public static <T> T getSessionAttr(String key) {
        return (T) getSession().getAttribute(key);
    }

    /**
     * @Description:获取session
     * @Author: liubin
     * @Date: 2018/4/3 22:46
     * @param :
     * @return
    */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * @Description:获取request
     * @Author: liubin
     * @Date: 2018/4/3 22:45
     * @param :
     * @return
    */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
