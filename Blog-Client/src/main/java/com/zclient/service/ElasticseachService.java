package com.zclient.service;

import com.github.pagehelper.PageInfo;
import com.zclient.model.Blog;

/**
 * @Description:es操作
 * @Author: liubin
 * @Date: Created in 19:46 2018/4/8
 * @Modified by:
 */
public interface ElasticseachService {
    boolean addBlog(Blog blog);

    PageInfo findBlog(Integer pageNum, Integer pageSzie);
}
