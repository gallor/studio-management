package com.gallor.dao;

import javax.sql.DataSource;

/**
 * Created by gallor on 11/6/15.
 */
public interface DataSourceFactory {

    public DataSource getDataSource();
}

