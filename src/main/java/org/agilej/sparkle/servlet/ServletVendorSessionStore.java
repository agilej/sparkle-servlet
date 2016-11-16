package org.agilej.sparkle.servlet;

import org.agilej.sparkle.WebRequest;
import org.agilej.sparkle.mvc.SessionStore;

import javax.servlet.http.HttpServletRequest;


/**
 * One {@link SessionStore} implementation which delegate all session operation to servlet api
 */
public class ServletVendorSessionStore implements SessionStore {

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

    @Override
    public void remove(WebRequest webRequest, String s) {
        HttpServletRequest request = webRequest.getOriginalRequest();
        request.getSession().removeAttribute(s);
    }
}
