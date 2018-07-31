package com.gallor.dao;

/**
 * Created by gallor on 11/5/15.
 */
public class DataSourceRetrievalException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public DataSourceRetrievalException(String message) {
        super(message);
    }

    public DataSourceRetrievalException(Throwable cause) {
        super(cause);
    }

    public DataSourceRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }

}
