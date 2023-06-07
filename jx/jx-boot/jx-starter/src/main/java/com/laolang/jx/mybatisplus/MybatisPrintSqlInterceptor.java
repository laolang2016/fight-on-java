package com.laolang.jx.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import java.lang.reflect.Field;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.core.Ordered;
import org.springframework.util.ReflectionUtils;

/**
 * 打印可执行的 sql
 *
 * @author khlbat
 * @since 1.0
 */
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
public class MybatisPrintSqlInterceptor implements Interceptor, Ordered {

    /**
     * mybatis 配置对象
     */
    private Configuration configuration = null;

    /**
     * 时间格式化
     */
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));

    /**
     * 拦截器主方法
     *
     * @param invocation invocation
     * @return sql 执行结果
     * @throws Throwable Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = invocation.proceed();
            return result;
        } finally {
            try {
                long endTime = System.currentTimeMillis();
                long sqlCost = endTime - startTime;
                StatementHandler statementHandler = (StatementHandler) target;
                BoundSql boundSql = statementHandler.getBoundSql();
                if (configuration == null) {
                    // final DefaultParameterHandler parameterHandler = (DefaultParameterHandler) statementHandler.getParameterHandler();
                    final ParameterHandler parameterHandler = statementHandler.getParameterHandler();
                    Field configurationField = ReflectionUtils.findField(parameterHandler.getClass(), "configuration");
                    ReflectionUtils.makeAccessible(configurationField);
                    this.configuration = (Configuration) configurationField.get(parameterHandler);
                }

                // 输出 mapper id
                MetaObject metaObject = SystemMetaObject.forObject(target);
                MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
                String id = ms.getId();

                // 替换参数格式化Sql语句，去除换行符
                String sql = formatSql(boundSql, configuration).concat(";");
                String warning = "";
                // CHECKSTYLE:OFF
                if (sqlCost > 2000) {
                    warning = "[耗时过长]";
                }
                // CHECKSTYLE:ON


                // 开始输出 sql
                log.info("map-id: {}", id);
                log.info("[ {} ] [ {} ] ms {}", sql, sqlCost, warning);

                if (result instanceof List) {
                    log.info("Total: {}", ((List) result).size());
                } else {
                    log.info("Updates: {}", result);
                }
            } catch (Exception e) {
                log.error("==> 打印sql 日志异常 {0}", e);
            }
        }
    }

    /**
     * plugin
     *
     * @param target target
     * @return Object
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * setProperties
     *
     * @param properties properties
     */
    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 获取完整的sql实体的信息
     *
     * @param boundSql      boundSql
     * @param configuration configuration
     * @return 格式化后的 sql
     */
    private String formatSql(BoundSql boundSql, Configuration configuration) {
        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();
        // 输入sql字符串空判断
        if (StringUtils.isBlank(sql)) {
            return "";
        }
        if (configuration == null) {
            return "";
        }
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        sql = beautifySql(sql);
        // 参考mybatis 源码 DefaultParameterHandler
        if (parameterMappings != null) {
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    String paramValueStr = "";
                    if (value instanceof String) {
                        paramValueStr = "'" + value + "'";
                    } else if (value instanceof Date) {
                        paramValueStr = "'" + DATE_FORMAT_THREAD_LOCAL.get().format(value) + "'";
                    } else {
                        paramValueStr = value + "";
                    }
                    sql = sql.replaceFirst("\\?", paramValueStr);
                }
            }
        }
        return sql;
    }

    /**
     * 美化 sql
     *
     * @param sql sql
     * @return sql
     */
    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", " ");
        return sql;
    }

    /**
     * 修改优先级
     *
     * @return 优先级
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

