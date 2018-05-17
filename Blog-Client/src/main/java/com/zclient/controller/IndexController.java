package com.zclient.controller;

import com.zclient.api.ApiResult;
import com.zclient.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 首页
 * @Author: liubin
 * @Date: Created in 15:00 2018/3/2
 * @Modified by:
 */
@RequestMapping(value = "/index")
@RestController
public class IndexController {
    private final  static Logger logger= LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ApiResult<List<Map<String,Object>>> index(){
        List<Map<String ,Object>> blogs=indexService.getIndexBlog();
        return new ApiResult<>(blogs);
    }
}
