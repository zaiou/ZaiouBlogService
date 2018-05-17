package com.zdubbo.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zcommon.dubboserviice.TestDubboService;
import org.springframework.stereotype.Component;


/**
 * @Description:
 * @Author: liubin
 * @Date: Created in 17:05 2018/5/6
 * @Modified by:
 */
@Service(protocol = "dubbo")
@Component("testDubboService")
public class TestDubboServiceimpl implements TestDubboService {

    @Override
    public String testdubbo() {
        System.out.println("dubbo提供者测试");
        return "dubbo测试成功";
    }
}
