package com.tqg.zhao.mybatisPlugin.page;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

@Intercepts({  
    @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),  
    @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,  
            RowBounds.class, ResultHandler.class }) }) 
public class SqlStatementInterceptor implements Interceptor{
	private static Logger logger = LoggerFactory.getLogger(SqlStatementInterceptor.class);
	  
    @SuppressWarnings("unused")  
    private Properties properties;  
      
    @Override  
    public Object intercept(Invocation arg0) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) arg0.getArgs()[0];
        Object parameter = null;  
        if (arg0.getArgs().length > 1) {  
            parameter = arg0.getArgs()[1];  
        }
        String sqlId = mappedStatement.getId();  
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);  
        Configuration configuration = mappedStatement.getConfiguration();
        //logger.debug("*******************************sqlId="+sqlId);
        //logger.debug("*******************************parameter="+parameter+",arg0="+arg0);
        Object returnValue = null;  
        long start = System.currentTimeMillis();  
        returnValue = arg0.proceed();
        //logger.debug("*******************************returnValue="+returnValue+",=configuration"+configuration);
        long end = System.currentTimeMillis();  
        long time = (end - start);

        if (time > 1) {  
            String sql = getSql(configuration, boundSql, sqlId, time);  
            logger.debug("*******************************sql="+sql);
            //PointLogger.daoLog(sql);
        }  
          
        return returnValue;  
    }  
      
    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {  
        String sql = showSql(configuration, boundSql);  
        StringBuilder str = new StringBuilder(100);  
        str.append(sqlId);  
        str.append(":");  
        str.append(sql);  
        str.append(":");  
        str.append(time);  
        str.append("ms");  
        return str.toString();  
    }  
      
    public static String showSql(Configuration configuration, BoundSql boundSql) {  
        Object parameterObject = boundSql.getParameterObject();  
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");  
        if (parameterMappings.size() > 0 && parameterObject != null) {  
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();  
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {  
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
   
            } else {  
                MetaObject metaObject = configuration.newMetaObject(parameterObject);  
                for (ParameterMapping parameterMapping : parameterMappings) {  
                    String propertyName = parameterMapping.getProperty();  
                    if (metaObject.hasGetter(propertyName)) {  
                        Object obj = metaObject.getValue(propertyName);  
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {  
                        Object obj = boundSql.getAdditionalParameter(propertyName);  
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    }  
                }  
            }  
        }  
        return sql;  
    }  
      
    private static String getParameterValue(Object obj) {  
        String value = null;  
        if (obj instanceof String || obj instanceof Enum) {  
            value = "'" + obj.toString() + "'";  
        } else if (obj instanceof Date) {  
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);  
            value = "'" + formatter.format(new Date()) + "'";  
        } else {  
            if (obj != null) {  
                value = obj.toString();  
            } else {  
                value = "";  
            }  
   
        }  
        return value;  
    }  
  
    @Override  
    public Object plugin(Object arg0) {  
        return Plugin.wrap(arg0, this);  
    }  
  
    @Override  
    public void setProperties(Properties arg0) {  
        this.properties = arg0;  
    }  
}
