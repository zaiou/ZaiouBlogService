package com.zclient.controller;

import com.zrabbitmq.model.MqObject;
import com.zrabbitmq.service.producer.DemoProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 消息队列测试
 * @Author: liubin
 * @Date: Created in 21:40 2018/5/13
 * @Modified by:
 */
@RestController
@RequestMapping(value = "/mq")
public class TestMqController {

    @Autowired
    DemoProducerService producerService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public void  sendMessage(MqObject object){
        producerService.sendMessage(object);
    }

}
