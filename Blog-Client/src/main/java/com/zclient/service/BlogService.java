package com.zclient.service;

import com.zclient.model.Blog;

/**
 * @Description: 博客相关
 * @Author: liubin
 * @Date: Created in 23:09 2018/3/5
 * @Modified by:
 */
public interface BlogService {
    Blog getBlogDetail(int blogId);
}
