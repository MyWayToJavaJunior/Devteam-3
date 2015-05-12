package com.epam.task6.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * Created by olga on 30.04.15.
 */
public class RequestAttributeListener implements ServletRequestAttributeListener {
    private static Logger logger = Logger.getLogger("activity");

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        logger.info("Name: " + event.getName() + " Value: " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        logger.info("Name: " + event.getName() + " Value: " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        logger.info("Name: " + event.getName() + " Value: " + event.getValue());
    }

}