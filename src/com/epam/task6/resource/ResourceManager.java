package com.epam.task6.resource;

import java.util.ResourceBundle;

/**
 * Created by olga on 21.04.15.
 */
public class ResourceManager {
    /* Keeps path to jsp resources */
    private static final String RESOURCE_PATH = "com.epam.task6.resource.Resource";

    /* Keeps path to logger resources */
    private static final String LOGGER_PATH = "com.epam.task6.resource.logger";

    /* Keeps path to forward pages resourses */
    private static final String FORWARD_PATH = "com.epam.task6.resource.forward";

    /* Keeps resource object */
    private static ResourceBundle resource;

    /**
     * Return value by certain key
     *
     * @param key Key
     * @return Value
     */
    public static String getProperty(String key) {
        String type = key.substring(0, key.indexOf("."));
        if (type.equals("jsp")) {
            resource = ResourceBundle.getBundle(RESOURCE_PATH);

        } else if (type.equals("logger")) {
            resource = ResourceBundle.getBundle(LOGGER_PATH);

        } else if (type.equals("forward")) {
            resource = ResourceBundle.getBundle(FORWARD_PATH);

        } else if (type.equals("redirect")) {
            resource = ResourceBundle.getBundle(FORWARD_PATH);

        }
        return resource.getString(key);
    }

}