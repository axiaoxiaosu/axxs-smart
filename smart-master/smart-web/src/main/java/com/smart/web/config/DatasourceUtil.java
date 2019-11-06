package com.smart.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 初始数据源
 */
@Component
public class DatasourceUtil {

    public static Map<String, DruidDataSource> druidDataSourceMap = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(DatasourceUtil.class);
    private String dataSourceUrl1 = "jdbc:mysql://localhost:3308/smart?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private String username1 = "root";
    private String password1 = "123456";

    private String dataSourceUrl2 = "jdbc:mysql://localhost:3308/smart_test_plmdatasource?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private String username2 = "root";
    private String password2 = "root";

    public DatasourceUtil() {
        afterPropertiesSet();
    }

    /**
     * 项目加载时 初始化数据源
     */
    public void afterPropertiesSet() {
        logger.info("init dataSource...");
        DruidDataSource dataSource = new DruidDataSource();
        DruidDataSource dataSource2 = new DruidDataSource();
        try {
            dataSource.setInitialSize(0);
            dataSource.setMaxActive(120);
            dataSource.setMinIdle(5);
            dataSource.setMaxWait(60000L);
            dataSource.setTestOnBorrow(false);
            dataSource.setTestOnReturn(false);
            dataSource.setTestWhileIdle(true);
            dataSource.setUrl(dataSourceUrl1);
            dataSource.setUsername(username1);
            dataSource.setPassword(password1);
            dataSource.setFilters("stat");


         /*   dataSource2.setInitialSize(0);
            dataSource2.setMaxActive(120);
            dataSource2.setMinIdle(5);
            dataSource2.setMaxWait(60000L);
            dataSource2.setTestOnBorrow(false);
            dataSource2.setTestOnReturn(false);
            dataSource2.setTestWhileIdle(true);
            dataSource2.setUrl(dataSourceUrl2);
            dataSource2.setUsername(username2);
            dataSource2.setPassword(password2);
            dataSource2.setFilters("stat");*/

        } catch (Exception e) {
            logger.error("初始化数据源失败", e);
        }
        druidDataSourceMap.put("smart_dafault_datasource", dataSource);
        //druidDataSourceMap.put("smart_routing_datasource_1", dataSource2);

        logger.info("init dataSource success");
    }
}

