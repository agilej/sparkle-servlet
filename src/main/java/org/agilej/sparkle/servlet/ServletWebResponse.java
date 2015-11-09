package org.agilej.sparkle.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.agilej.sparkle.Cookie;
import org.agilej.sparkle.WebResponse;

/**
 * One {@link WebResponse} implementation for servlet-runtime, it an adaptor for {@link HttpServletResponse}
 */
public class ServletWebResponse implements WebResponse {

    private HttpServletResponse response;

    public ServletWebResponse(HttpServletResponse response) {
        this.response = response;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getOriginalResponse() {
        return (T)this.response;
    }
    
    @Override
    public void setStatus(int sc) {
        this.response.setStatus(sc);
    }

    @Override
    public void write(String string) {
        try {
            this.response.getWriter().write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void setHeader(String name, String value) {
        this.response.setHeader(name, value);
    }
    
    @Override
    public void setContentType(String type) {
        this.response.setContentType(type);
    }
     
    @Override
    public Writer getWriter(){
        try {
            return this.response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCookie(Cookie cookie) {
        javax.servlet.http.Cookie _cookie = new javax.servlet.http.Cookie(cookie.name(),cookie.value());
        _cookie.setDomain(cookie.domain());
        _cookie.setMaxAge(cookie.maxAge());
        _cookie.setPath(cookie.path());
        this.response.addCookie(_cookie);
    }
}
