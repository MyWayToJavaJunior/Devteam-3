package com.epam.task6.filter;

import com.epam.task6.domain.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by olga on 01.05.15.
 */

@WebFilter(dispatcherTypes = {DispatcherType.FORWARD},
        urlPatterns = {"/"})
public class AuthorizedRedirectFilter implements Filter {
    private User user;
    private static final String FORWARD = "controller?executionCommand=REDIRECT";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        user = (User)request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath() + FORWARD);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        user = null;
    }

}
