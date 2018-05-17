package com.zdubbo.controller;

import com.zdubbo.mapper.CrawlerBlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: liubin
 * @Date: Created in 23:27 2018/5/1
 * @Modified by:
 */
@Controller
@RestController
public class TestController {
    @Autowired
    private CrawlerBlogMapper crawlerBlogMapper;


    @RequestMapping(value = "/test")
    public void  test(){
        System.out.println("测试数据库");
        System.out.println("service测试"+crawlerBlogMapper.selectAll());
    }
}
