package com.tomato.mycode.util.dataRoute;

import com.tomato.mycode.util.dataRoute.RouteHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import javax.sql.DataSource;

public class ChooseDataSource extends AbstractRoutingDataSource {
    /**
     * 获取与数据源相关的key
     * 此key是Map<String,DataSource> resolvedDataSources 中与数据源绑定的key值
     * 在通过determineTargetDataSource获取目标数据源时使用
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return RouteHolder.getRouteKey();
    }

    /**
     * 配置DataSource:defaultTargetDataSource为主数据库,targetDataSources 其他数据源
     */
    public ChooseDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
}
