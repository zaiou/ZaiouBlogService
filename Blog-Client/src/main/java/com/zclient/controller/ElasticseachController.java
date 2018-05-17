package com.zclient.controller;

import com.github.pagehelper.PageInfo;
import com.zclient.api.ApiResult;
import com.zclient.model.Blog;
import com.zclient.service.ElasticseachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:es控制器
 * 参考文档 https://blog.csdn.net/u011781521/article/details/77848489
 *          https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/transport-client.html
 * @Author: liubin
 * @Date: Created in 20:27 2018/4/8
 * @Modified by:
 */
@RestController
@RequestMapping(value = "es")
public class ElasticseachController {
    private final  static Logger logger= LoggerFactory.getLogger(ElasticseachController.class);

    @Autowired
    private ElasticseachService elasticseachService;

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ApiResult<Boolean> index(@RequestBody Blog blog){
        boolean flag=elasticseachService.addBlog(blog);
        return new ApiResult<>(flag);
    }

    @RequestMapping(value = "/info/pageNum/pageSize", method = RequestMethod.GET)
    public ApiResult<PageInfo> index(@PathVariable Integer pageNum,
                                     @PathVariable Integer pageSize){
        PageInfo pageInfo=elasticseachService.findBlog(pageNum,pageSize);
        return new ApiResult<>(pageInfo);
    }
}
