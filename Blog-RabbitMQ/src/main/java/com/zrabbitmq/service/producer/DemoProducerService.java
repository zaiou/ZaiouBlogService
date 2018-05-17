package com.zrabbitmq.service.producer;

import com.zrabbitmq.model.MqObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Description: 生产者服务
 * @Author: liubin
 * @Date: Created in 21:04 2018/5/13
 * @Modified by:
 */
@Service
public class DemoProducerService {
    @Autowired
    @Qualifier(value = "directExChangeTemplate")
    RabbitTemplate hasExChangeTemplate;//此template指定了默认的交换机

    @Autowired
    @Qualifier(value = "noExChangeTemplate")
    RabbitTemplate noExChangeTemplate;//此template没有指定默认的交换机

    /**
     * @Description:发送消息
     * @Author: liubin
     * @Date: 2018/5/13 21:08
     * @param :
     * @return
    */
    public void sendMessage(MqObject object){
        String exchange = hasExChangeTemplate.getExchange();
        System.out.println(exchange);
        hasExChangeTemplate.setEncoding("UTF-8");
        hasExChangeTemplate.setRoutingKey("zf_direct_queue");
        System.out.println("发送消息：");
        hasExChangeTemplate.convertAndSend(object);
    }
}
