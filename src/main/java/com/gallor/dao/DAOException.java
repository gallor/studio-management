package com.gallor.dao;

/**
 * @author gallor
 * This class represents a generic DAO exception. It should wrap any exception of the underlying
 * code, such as SQLExceptions.
 *
 */

public class DAOException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a DAOException with the given detail message.
     * @param message The detail message of DAOException
     */
    public DAOException(String message) {
    	super(message);
    }
    
    /**
     * Constructs a DAOException with the given cause
     * @param cause The root cause of the DAOException
     */
    public DAOException(Throwable cause) {
    	super(cause);
    }
    
    /**
     * Constructs a DAOException with the given message and root cause
     * @param message The detail message of the DAOException
     * @param cause The root cause of the DAOException
     */
    public DAOException(String message, Throwable cause) {
    	super(message, cause);
    }

}
