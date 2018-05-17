package com.zclient.plugin;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: liubin
 * @Date: Created in 18:56 2018/4/9
 * @Modified by:
 */
public class EsRestClient {
    private final  static Logger logger= LoggerFactory.getLogger(EsRestClient.class);

    private static RestClient restClient;

    public static RestClient restClient(){
         restClient = RestClient.builder(
                new HttpHost("127.0.0.1", 9200, "http"))
                 .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(60000)
                 .setSocketTimeout(2000))
                 .build();
        return restClient;
    }

    public static RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
    }
}
