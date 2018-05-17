package com.zclient.controller;

import com.zclient.api.ApiResult;
import com.zclient.model.Blog;
import com.zclient.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:博客相关
 * @Author: liubin
 * @Date: Created in 23:14 2018/3/5
 * @Modified by:
 */
@RequestMapping(value = "/blog")
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/info/{blogId}",method = RequestMethod.GET)
    private ApiResult<Blog> getBlogDetail(@PathVariable int blogId){
        Blog blog=blogService.getBlogDetail(blogId);
        return new ApiResult<>(blog);
    }
}
