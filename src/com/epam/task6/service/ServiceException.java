package com.epam.task6.service;

import com.epam.task6.exception.ProjectException;

/**
 * Created by olga on 06.05.15.
 */
public class ServiceException extends ProjectException {
    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Exception e) {
        super(msg, e);
    }
}
