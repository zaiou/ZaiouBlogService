package com.zclient.service.impl;

import com.zclient.mapper.BlogMapper;
import com.zclient.model.Blog;
import com.zclient.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 博客相关
 * @Author: liubin
 * @Date: Created in 23:10 2018/3/5
 * @Modified by:
 */
@Service
public class BlogServiceImpl implements BlogService {
    private final static Logger logger= LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private BlogMapper blogMapper;
    /**
     * @Description: 获取博客详情
     * @Author: liubin
     * @Date: 2018/3/5 23:10
     * @param :
     * @return
    */
    @Override
    public Blog getBlogDetail(int blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId); //查询详情
        int lookid = blogMapper.insertLookRecord(blog); //添加浏览记录
        logger.info("添加浏览记录返回值:"+lookid);
        return blog;
    }
}
