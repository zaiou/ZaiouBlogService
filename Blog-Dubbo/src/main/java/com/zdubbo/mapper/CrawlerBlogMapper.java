package com.zdubbo.mapper;

import com.zdubbo.domain.CrawlerBlog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("crawlerBlogMapper")
public interface CrawlerBlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrawlerBlog record);

    List<CrawlerBlog> selectAll();
}