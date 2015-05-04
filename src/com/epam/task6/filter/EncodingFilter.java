package com.epam.task6.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by olga on 02.05.15.
 */

public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String requestEncoding = servletRequest.getCharacterEncoding();
        if (encoding != null && encoding.equalsIgnoreCase(requestEncoding)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
