package me.donnior.sparkle.servlet;

import me.donnior.sparkle.Session;

import javax.servlet.http.HttpServletRequest;


public class ServletSessionAdapter implements Session {


    private final HttpServletRequest request;

    public ServletSessionAdapter(HttpServletRequest request){
        this.request = request;
    }

    @Override
    public Session set(String s, Object o) {
        this.request.getSession().setAttribute(s, o);
        return this;
    }

    @Override
    public <T> T get(String s) {
        return (T)this.request.getSession().getAttribute(s);
    }

    @Override
    public String id() {
        return this.request.getSession().getId();
    }
}
