package com.tomato.mycode.untils.dataroute;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * 配置多数据源
 */
@Configuration
public class RouteHolderConfig {
    /**
     * 创建 DataSource Bean
     * */

    @Bean(name="db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    public DataSource oneDataSource(){
        DataSource oneDataSource = DruidDataSourceBuilder.create().build();
        System.out.println("RouteHolderConfig:oneDataSource:builded");
        return oneDataSource;
    }

    @Bean(name="db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
    public DataSource twoDataSource(){
        DataSource twoDataSource = DruidDataSourceBuilder.create().build();
        System.out.println("RouteHolderConfig:twoDataSource:builded");
        return twoDataSource;
    }

    /**
     * 如果还有数据源,在这继续添加 DataSource Bean
     * */
    @Bean
    @Primary
    public ChooseDataSource dataSource(@Qualifier("db1")DataSource oneDataSource, @Qualifier("db2")DataSource twoDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put("db1", oneDataSource);
        targetDataSources.put("db2", twoDataSource);
        // 还有数据源,在targetDataSources中继续添加
        System.out.println("RouteHolderConfig:DataSources:" + targetDataSources);
        return new ChooseDataSource(oneDataSource, targetDataSources);
    }
}
