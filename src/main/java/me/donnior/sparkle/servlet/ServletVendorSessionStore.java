package me.donnior.sparkle.servlet;

import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.core.request.SessionStore;

import javax.servlet.http.HttpServletRequest;


/**
 * One {@link SessionStore} implementation which delegate all session operation to servlet api
 */
public class ServletVendorSessionStore implements SessionStore{

    @Override
    public void set(WebRequest webRequest, String name, Object value) {
        HttpServletRequest request = webRequest.getOriginalRequest();
        request.getSession().setAttribute(name, value);
    }

    @Override
    public Object get(WebRequest webRequest, String name) {
        HttpServletRequest request = webRequest.getOriginalRequest();
        return request.getSession().getAttribute(name);
    }
}
