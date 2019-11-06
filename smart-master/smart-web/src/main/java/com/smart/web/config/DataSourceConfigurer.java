package com.smart.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.common.switchdb.SwitchDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfigurer {

    @Autowired
    DatasourceUtil util;

    /**
     * 创建数据源
     */
    @Bean("samrtDataSource")
    public DataSource samrtDataSource() {
        Map<String, DruidDataSource> dataSourceMap = DatasourceUtil.druidDataSourceMap;
        SwitchDataSource dynamicRoutingDataSource = new SwitchDataSource();
        //默认数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceMap.get("smart_dafault_datasource"));
        //可切换的数据源
        dynamicRoutingDataSource.setTargetDataSources((HashMap) dataSourceMap);
        return dynamicRoutingDataSource;
    }

    /**
     * 数据源注入
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(samrtDataSource());
        return sqlSessionFactoryBean;
    }

    /**
     * 事务管理
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(samrtDataSource());
    }
}
