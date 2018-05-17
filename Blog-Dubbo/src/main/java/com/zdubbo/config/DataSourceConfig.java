package com.zdubbo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zcommon.datasource.DynamicDataSource;
import com.zcommon.datasource.DynamicDataSourceTransactionManager;
import com.zdubbo.property.ReadDBProperty;
import com.zdubbo.property.WriteDBProperty;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Description: 数据源配置
 * @Author: liubin
 * @Date: Created in 22:50 2018/5/1
 * @Modified by:
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.zdubbo.mapper", sqlSessionFactoryRef = "dynamicSqlSessionFactory")
@EnableConfigurationProperties({WriteDBProperty.class , ReadDBProperty.class})
public class DataSourceConfig {

    @Autowired
    private WriteDBProperty writeDBProperty;

    @Autowired
    private ReadDBProperty readDBProperty;

    /**
     * @Description:写数据源
     * @Author: liubin
     * @Date: 2018/4/24 13:43
     * @param :
     * @return
     */
    @Primary
    @Bean(name = "writeDataSource")
    public DruidDataSource writeDataSource(){
        DruidDataSource writeDataSource = new DruidDataSource();
        writeDataSource.setUrl(writeDBProperty.getUrl());
        writeDataSource.setUsername(writeDBProperty.getUsername());
        writeDataSource.setPassword(writeDBProperty.getPassword());
        return writeDataSource;
    }

    /**
     * @Description: 读取数据源
     * @Author: liubin
     * @Date: 2018/4/24 12:59
     * @param :
     * @return
     */
    @Bean(name = "readDataSource")
    public DruidDataSource readDataSource(){
        DruidDataSource readDataSource = new DruidDataSource();
        readDataSource.setUrl(readDBProperty.getUrl());
        readDataSource.setUsername(readDBProperty.getUsername());
        return readDataSource;
    }

    /**
     * @Description: 动态设置数据源
     * @Author: liubin
     * @Date: 2018/4/24 13:44
     * @param :
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setWriteDataSource(writeDataSource());
        dynamicDataSource.setReadDataSource(readDataSource());
        return dynamicDataSource;

    }

    /**
     * @Description:设置事物
     * @Author: liubin
     * @Date: 2018/4/24 13:46
     * @param :
     * @return
     */
    @Bean(name = "dynamicTransactionManager")
    public DataSourceTransactionManager dynamicTransactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DynamicDataSourceTransactionManager(dynamicDataSource);
    }

    /**
     * @Description:设置SqlSessionFactory
     * 参考文档：https://blog.csdn.net/javahighness/article/details/53044655
     * @Author: liubin
     * @Date: 2018/4/24 13:49
     * @param :
     * @return
     */
    @Bean(name = "dynamicSqlSessionFactory")
    public SqlSessionFactory dynamicSqlSessionFactory(
            @Qualifier("dynamicDataSource") DataSource dynamicDataSource,
            MybatisProperties properties) {
        try {
            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dynamicDataSource);
            Resource[] resources = new Resource[0];
            resources = new PathMatchingResourcePatternResolver()
                    .getResources(mapperLocations);
            sessionFactory.setMapperLocations(resources);
            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;
}
