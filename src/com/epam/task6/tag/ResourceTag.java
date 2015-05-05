package com.epam.task6.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by olga on 05.05.15.
 */
public class ResourceTag extends TagSupport {
    private static final String RESOURCE_PATH = "com.epam.task6.resource.Resource";
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int doStartTag() throws JspException {
        ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_PATH, Locale.forLanguageTag("en"));
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(resource.getString(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}


