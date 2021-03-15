package com.smart.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * Hello Job
 * </p>
 *
 * @package: com.xkcoding.task.quartz.job
 * @description: Hello Job
 * @author: yangkai.shen
 * @date: Created in 2018-11-26 13:22
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */

@Slf4j
@Component(JobNames.HELLOJOB)
public class HelloJob {

    @Autowired
    private SqlSession sqlSession;

    public void job() {
        log.warn("hello,quartz");
    }


    public void initMySql() {
        log.warn("开始初始化数据库啦");
        Connection connection = null;
        ScriptRunner runner = null;
        try {
            File file = ResourceUtils.getFile("classpath:dump-smart.sql");
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            connection = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
            runner = new ScriptRunner(connection);
            runner.setAutoCommit(true);
            runner.setFullLineDelimiter(false);
            runner.setDelimiter(";");
            runner.setSendFullScript(false);
            runner.setStopOnError(false);
            runner.runScript(inputStreamReader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            runner.closeConnection();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.warn("初始化数据库结束啦");
    }
}