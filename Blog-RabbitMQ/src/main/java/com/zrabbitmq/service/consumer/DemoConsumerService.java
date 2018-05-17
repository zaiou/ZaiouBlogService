package com.zrabbitmq.service.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @Description: 消费者 此监听器接收多个队列的消息
 * @Author: liubin
 * @Date: Created in 21:35 2018/5/13
 * @Modified by:
 */
@Service("demoConsumerService")
public class DemoConsumerService implements MessageListener {
    /**
     * @Description: auto模式自动确认消息，可以继承MessageListener即可，不需要获取channel来确认消息
     * @Author: liubin
     * @Date: 2018/5/13 21:37
     * @param :
     * @return
    */
    public void onMessage(Message message) {
        try {
            String s =  new String(message.getBody(),"UTF-8");
            System.out.println("接收到消息");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
