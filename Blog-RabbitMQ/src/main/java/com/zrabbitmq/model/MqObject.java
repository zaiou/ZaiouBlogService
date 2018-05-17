package com.zrabbitmq.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: liubin
 * @Date: Created in 21:23 2018/5/13
 * @Modified by:
 */
public class MqObject implements Serializable {
    private static final long serialVersionUID = 5779637824662535426L;

    private String message;

    private String exchange;

    private String routingKey;

    private String queue;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }


}
