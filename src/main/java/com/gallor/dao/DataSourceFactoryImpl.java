package com.gallor.dao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Created by gallor on 11/5/15.
 */
public class DataSourceFactoryImpl implements DataSourceFactory {

    private String dbName;

    public DataSourceFactoryImpl(String dbName) {
        this.dbName = dbName;
    }

    public DataSource getDataSource() throws DataSourceRetrievalException {

        try {
            Context envCx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource dataSource = (DataSource) envCx.lookup("jdbc/" + dbName);

            return dataSource;

        } catch (NamingException e) { //catch specific one here
            throw new DataSourceRetrievalException(e);
        }

    }
}
