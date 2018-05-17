package com.zclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.zclient.model.Blog;
import com.zclient.plugin.EsRestClient;
import com.zclient.service.ElasticseachService;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Description: es服务
 * @Author: liubin
 * @Date: Created in 19:49 2018/4/8
 * @Modified by:
 */
@Service
public class ElasticseachServiceImpl implements ElasticseachService{
    private final  static Logger logger= LoggerFactory.getLogger(ElasticseachServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @Description:添加博客
     * @Author: liubin
     * @Date: 2018/4/8 22:13
     * @param :
     * @return
    */
    @Override
    public boolean addBlog(Blog blog) {
//        List<IndexQuery> queries = new ArrayList<IndexQuery>();
//        try {
//            IndexQuery indexQuery = new IndexQueryBuilder().withId(UUIDUtils.getUUID()).withObject(blog).build();
//            queries.add(indexQuery);
//            elasticsearchTemplate.bulkIndex(queries);
//        } catch (Exception e) {
//            logger.info("es插入数据异常");
//            return false;
//        }
        //指定需要插入那个es表
        StringBuilder urlBuilder = new StringBuilder()
                .append("/")
                .append("blog")
                .append("/")
                .append(blog.getTitle())
                .append("/")
                .append(blog.getBlogid());
        //blog实体类装换
        HttpEntity entity;
        try {
            entity = new NStringEntity(objectMapper.writeValueAsString(blog), ContentType.APPLICATION_JSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        //插入到es
        EsRestClient.restClient().performRequestAsync("PUT",
                urlBuilder.toString(),
                Collections.emptyMap(),
                entity,
                new ResponseListener() {
                    @Override
                    public void onSuccess(Response response) {
                        logger.info("插入成功");
                    }
                    @Override
                    public void onFailure(Exception exception) {
                        exception.printStackTrace();
                    }
                });
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @Description:查询博客
     * @Author: liubin
     * @Date: 2018/4/8 22:13
     * @param :
     * @return
    */
    @Override
    public PageInfo findBlog(Integer pageNum, Integer pageSize) {
//        if (pageNum == null || pageNum < 0) {
//            pageNum = 0;
//        }
//        // ES 首页=0
//        if (pageNum > 0) {
//            pageNum -= 1;
//        }
//        if (pageSize == null || pageSize <= 0) {
//            pageSize = 10;
//        }
//        //设置索引
//        SearchRequest searchRequest = new SearchRequest("zaiou_blog");
//        searchRequest.types("blog");
//        // 查询器
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//
//        //设置查询字段
//        String[] includes={"summary","content"};
//        String[] excludes = null;
//        //创建查询
//        builder.fetchSource(includes, excludes);
//        //分页
//        builder.from(pageNum).size(pageSize);
//        // 构建查询器
//        searchRequest.source(builder);
//        // 结果集
//        List<Blog> list = new ArrayList<>();
//        // 查询
//        SearchResponse search = esClient.search(searchRequest).actionGet();
//        // 查询结果
//        SearchHit[] hits = search.getHits().getHits();
//        // 总记录数
//        Long total = search.getHits().getTotalHits();
//        //重组搜索数据
//        if (hits != null && hits.length > 0) {
//            for (SearchHit hit : hits) {
//                Map<String, Object> entityMap = hit.getSourceAsMap();
//                JSONObject jo = new JSONObject();
//                jo.putAll(entityMap);
//                Blog obj = JSONObject.toJavaObject(jo, Blog.class);
//                list.add(obj);
//            }
//        }
//        if (list.size() > 0) {
//           return PageUtils.change(pageNum + 1, pageSize, total.intValue(), list);
//        }
//        return new PageInfo(Lists.newArrayList());

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        return null;
    }
}
