package com.zclient.mapper;

import com.zclient.model.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer blogid);

    int insert(Blog record);

    Blog selectByPrimaryKey(Integer blogid);

    List<Blog> selectAll();

    int updateByPrimaryKey(Blog record);

    List<Map<String,Object>> getIndexBlog();

    int insertLookRecord(Blog blog);
}