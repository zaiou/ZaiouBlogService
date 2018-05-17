package com.zbuddo;

import com.zdubbo.BlogDubboApplication;
import com.zdubbo.mapper.CrawlerBlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Description: 数据源测试
 * @Author: liubin
 * @Date: Created in 23:04 2018/5/1
 * @Modified by:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogDubboApplication.class)
@WebAppConfiguration
public class DataSourceTest {

    @Autowired
    private CrawlerBlogMapper crawlerBlogMapper;

    @Test
    public void dataSourceTest(){
        System.out.println("数据源测试"+crawlerBlogMapper.selectAll());;
    }

}
