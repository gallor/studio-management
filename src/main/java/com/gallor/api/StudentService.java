package com.gallor.api;

import com.gallor.dao.DataSourceFactoryImpl;
import com.gallor.dao.StudentDAOImpl;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Created by gallor on 11/30/15.
 */
public class StudentService {

    private String dbName = "allor_studio";
    private DataSource dataSource;


    public StudentService() {
    }
}
