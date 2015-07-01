package me.donnior.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletRequest;

import me.donnior.sparkle.Params;

@Deprecated
public class HttpRequestParamsWraper implements Params {

    private HttpServletRequest request;

    public HttpRequestParamsWraper(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String get(String name) {
        return request.getParameter(name);
    }

    @Override
    public String get(String s, String s1) {
        return null;
    }

    @Override
    public String[] gets(String name) {
        return request.getParameterValues(name);
    }

    @Override
    public <T> T get(String name, Class<T> clz) {
        return null;
    }

    @Override
    public Integer getInt(String s) {
        return null;
    }

    @Override
    public Integer getInt(String s, Integer integer) {
        return null;
    }

    @Override
    public Float getFloat(String s) {
        return null;
    }

    @Override
    public Float getFloat(String s, Float aFloat) {
        return null;
    }


}
