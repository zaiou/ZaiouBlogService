package com.zclient.utils;

import java.util.UUID;

/**
 * @Description:UUID帮助类
 * @Author: liubin
 * @Date: Created in 20:15 2018/4/8
 * @Modified by:
 */
public class UUIDUtils {
    /**
     * @Description:获取uuid
     * @Author: liubin
     * @Date: 2018/4/8 20:17
     * @param :
     * @return
    */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
