package com.zcommon.datasource;

/**
 * @Description: 动态数据源持有者
 * @Author: liubin
 * @Date: Created in 13:24 2018/4/24
 * @Modified by:
 */
public final class DynamicDataSourceHolder {
    /**
     * @Description: 动态数据源存储
     * @Author: liubin
     * @Date: 2018/4/24 13:25
     * @param :
     * @return
    */
    private static final ThreadLocal<DynamicDataSourceGlobal> DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL = new ThreadLocal<>();

    private DynamicDataSourceHolder() {
        //
    }

    /**
     * @Description: 存放数据源
     * @Author: liubin
     * @Date: 2018/4/24 13:25
     * @param : dataSource 数据源
     * @return
    */
    public static void putDataSource(DynamicDataSourceGlobal dataSource) {
        DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.set(dataSource);
    }

    /**
     * @Description: 获取数据源
     * @Author: liubin
     * @Date: 2018/4/24 13:25
     * @param :
     * @return
    */
    public static DynamicDataSourceGlobal getDataSource() {
        return DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.get();
    }

    /**
     * @Description: 清除数据源
     * @Author: liubin
     * @Date: 2018/4/24 13:26
     * @param :
     * @return
    */
    public static void clearDataSource() {
        DYNAMIC_DATA_SOURCE_GLOBAL_THREAD_LOCAL.remove();
    }
}
