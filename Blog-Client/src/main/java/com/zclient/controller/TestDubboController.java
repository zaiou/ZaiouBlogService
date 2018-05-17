package com.zclient.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zcommon.dubboserviice.TestDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:  https://blog.csdn.net/yfz792178428/article/details/78321328#commentBox
 * @Author: liubin
 * @Date: Created in 17:08 2018/5/6
 * @Modified by:
 */
//@RestController
public class TestDubboController {

    /**
     * @Description: dubbo注解 远程调用分布
     * @Author: liubin
     * @Date: 2018/5/6 9:44
     * @param :
     * @return
     */
//    @Reference
    @Autowired
    private TestDubboService testDubboService;



    @RequestMapping(value = "/testdubbo")
    public void setTestDubbo(){
        System.out.println("dubbo消费者测试");
        String str=testDubboService.testdubbo();
        System.out.println("测试："+str);
    }
}
