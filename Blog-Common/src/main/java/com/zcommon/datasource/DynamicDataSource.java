package com.zcommon.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 动态数据源实现读写分离
 * @Author: liubin
 * @Date: Created in 13:17 2018/4/24
 * @Modified by:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

   /**
    * @Description: 写数据源
    * @Author: liubin
    * @Date: 2018/4/24 13:20
    * @param :
    * @return
   */
    private Object writeDataSource;

    /**
     * @Description: 读数据源
     * @Author: liubin
     * @Date: 2018/4/24 13:19
     * @param :
     * @return
    */
    private Object readDataSource;

    /**
     * @Description: 获取当前数据源
     * @Author: liubin
     * @Date: 2018/4/24 13:28
     * @param :
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {

        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

        if (dynamicDataSourceGlobal == null
                || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
            return DynamicDataSourceGlobal.WRITE.name();
        }

        return DynamicDataSourceGlobal.READ.name();
    }

    @Override
    public void afterPropertiesSet() {
        if (this.writeDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(writeDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(), writeDataSource);
        if (readDataSource != null) {
            targetDataSources.put(DynamicDataSourceGlobal.READ.name(), readDataSource);
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    public void setWriteDataSource(Object writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    public Object getWriteDataSource() {
        return writeDataSource;
    }

    public Object getReadDataSource() {
        return readDataSource;
    }

    public void setReadDataSource(Object readDataSource) {
        this.readDataSource = readDataSource;
    }
}
