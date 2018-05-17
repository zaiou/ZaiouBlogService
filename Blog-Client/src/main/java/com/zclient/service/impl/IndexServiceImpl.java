package com.zclient.service.impl;

import com.zclient.mapper.BlogMapper;
import com.zclient.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: liubin
 * @Date: Created in 15:33 2018/3/4
 * @Modified by:
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private BlogMapper blogMapper;

    /**
     * @Description: 获取首页博客信息
     * @Author: liubin
     * @Date: 2018/3/4 16:02
     * @param :
     * @return
    */
    @Override
    public List<Map<String,Object>> getIndexBlog() {
        List<Map<String,Object>> blogs=blogMapper.getIndexBlog();
        return blogs;
    }

}
