package com.common.switchdb;


import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SwitchDataSource extends AbstractRoutingDataSource {

    /**
     * 切换数据源
     *
     * @return 数据源id
     */
    @Override
    protected Object determineCurrentLookupKey() {

        String switchStr = RuntimeDataSource.getDataSource();
        if (switchStr != null && !StringUtils.isEmpty(switchStr)) {
            super.afterPropertiesSet();
        }
        return switchStr;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(SwitchDataSource.class.getClassLoader().getResourceAsStream("testnum.txt")));


    }

}

