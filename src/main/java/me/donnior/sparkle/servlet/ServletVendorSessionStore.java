package me.donnior.sparkle.servlet;

import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.core.request.SessionStore;

import javax.servlet.http.HttpServletRequest;


public class ServletVendorSessionStore implements SessionStore{
    @Override
    public void set(WebRequest webRequest, String s, Object o) {
        HttpServletRequest request = webRequest.getOriginalRequest();
        request.getSession().setAttribute(s, o);
    }

    @Override
    public Object get(WebRequest webRequest, String s) {
        HttpServletRequest request = webRequest.getOriginalRequest();
        return request.getSession().getAttribute(s);
    }
}
