package com.zcommon.datasource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 动态数据源Mybatis拦截器插件
 * @Author: liubin
 * @Date: Created in 9:32 2018/4/27
 * @Modified by:
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class})})
public class DynamicDataSourcePlugin implements Interceptor {
    /**
     * Logger
     */
    protected static final Logger logger = LoggerFactory.getLogger(DynamicDataSourcePlugin.class);

    /**
     * 拦截SQL表达式
     */
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    /**
     * 动态数据源缓存
     */
    private static final Map<String, DynamicDataSourceGlobal> CACHE_MAP = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        //如果没有事务
        if (!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement) objects[0];

            DynamicDataSourceGlobal dynamicDataSourceGlobal;

            if ((dynamicDataSourceGlobal = CACHE_MAP.get(ms.getId())) == null) {
                dynamicDataSourceGlobal = getDynamicDataSource(ms, objects[1]);
                logger.warn("设置方法[{}] use [{}] Strategy, SqlCommandType [{}]..", ms.getId(), dynamicDataSourceGlobal.name(), ms.getSqlCommandType().name());
                CACHE_MAP.put(ms.getId(), dynamicDataSourceGlobal);
            }
            DynamicDataSourceHolder.putDataSource(dynamicDataSourceGlobal);
        }

        return invocation.proceed();
    }

    /**
     * 获得动态数据源
     *
     * @param ms              MappedStatement
     * @param parameterObject Parameter
     * @return DynamicDataSourceGlobal
     */
    private DynamicDataSourceGlobal getDynamicDataSource(MappedStatement ms, Object parameterObject) {
        logger.info("获取动态数据源888888888888888888888888888888888888888888888888888");
        DynamicDataSourceGlobal dynamicDataSourceGlobal;
        //读方法
        if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
            //!selectKey 为自增id查询主键(SELECT LAST_INSERT_ID() )方法，使用主库
            if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
            } else {
                BoundSql boundSql = ms.getSqlSource().getBoundSql(parameterObject);
                String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                if (sql.matches(REGEX)) {
                    dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
                } else {
                    dynamicDataSourceGlobal = DynamicDataSourceGlobal.READ;
                }
            }
        } else {
            dynamicDataSourceGlobal = DynamicDataSourceGlobal.WRITE;
        }
        return dynamicDataSourceGlobal;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        //
    }
}
