package com.company.service.exception;

/**
 * Created by PC on 19.02.2017.
 */
public class ServiceException extends Exception{


/**
 * Created by PC on 19.02.2017.
 */
    public ServiceException() {

        super();
    }

    public ServiceException(String message) {

        super(message);
    }

    public ServiceException(String message, Exception e) {

        super(message, e);
    }

    public ServiceException(Exception e) {

        super(e);
    }


}
