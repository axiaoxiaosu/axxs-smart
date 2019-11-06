package com.common.switchdb;


/**
 * 为当前线程设置指定数据源  key
 *
 * @author lwq
 */
public class RuntimeDataSource {

    /**
     * 绑定到当前线程
     */
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

    /**
     * 设置数据源
     */
    public static void setDataSourceKey(String dataSourceKey) {
        clearDataSource();
        dataSources.set(dataSourceKey);
    }

    /**
     * 获取当前绑定的数据源
     */
    public static String getDataSource() {
        return dataSources.get();
    }

    /**
     * 清除当前绑定数据源
     */
    static void clearDataSource() {
        dataSources.remove();
    }
}

