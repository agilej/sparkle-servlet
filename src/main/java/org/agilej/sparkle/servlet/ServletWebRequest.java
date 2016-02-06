package org.agilej.sparkle.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import org.agilej.sparkle.*;
import org.agilej.sparkle.core.request.AbstractWebRequest;
import org.agilej.fava.Function;
import org.agilej.fava.util.FLists;

/**
 * One {@link WebRequest} implementation for servlet-runtime, it an adaptor for {@link HttpServletRequest}
 */
public class ServletWebRequest extends AbstractWebRequest{

    private final WebResponse webResponse;
    private final HttpServletRequest request;
    private AsyncContext asyncContext;

    public ServletWebRequest(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.webResponse = new ServletWebResponse(response);
    }
    
    @Override
    public String getPath() {
        String pathInfo = this.request.getPathInfo();
        if (pathInfo == null) {
            return this.request.getServletPath();   //wild servlet mapping like "/" or "*.do"
        } 
        return pathInfo;        //otherwise normal mapping like "/cms/*"
    }
    
    
    @Override
    public String getBody() {
        try {
            return getBody(this.request);
        } catch (IOException e) {
            System.out.println("----------- error " +  e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static String getBody(HttpServletRequest request) throws IOException {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
    
    @Override
    public String getContextPath() {
        return this.request.getServletContext().getContextPath();
    }
    
    @Override
    public String getHeader(String name) {
        return this.request.getHeader(name);
    }
    
    @Override
    public String getParameter(String name) {
        return this.request.getParameter(name);
    }
    
    @Override
    public String getMethod() {
        return this.request.getMethod();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public HttpServletRequest getOriginalRequest() {
        return this.request;
    }
    
    @Override
    public String[] getParameterValues(String paramName) {
        return this.request.getParameterValues(paramName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public HttpServletResponse getOriginalResponse() {
        return this.webResponse.getOriginalResponse();
    }
    
    @Override
    public WebResponse getWebResponse() {
        return this.webResponse;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String attrName) {
        return (T)this.request.getAttribute(attrName);
    }
    
    @Override
    public void setAttribute(String name, Object value) {
        this.request.setAttribute(name, value);
    }
    
    @Override
    public List<Multipart> getMultiparts(){
        Collection<Part> parts = null;
        try {
            parts = this.request.getParts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return FLists.safeCreate(parts).map(new Function<Part, Multipart>() {
            @Override
            public Multipart apply(Part part) {
                return new ServletMultipartAdapter(part);
            }
        });
    }

    @Override
    public void startAsync() {
        this.asyncContext =  this.request.startAsync();
    }

    @Override
    public boolean isAsync() {
        return this.request.isAsyncStarted();
    }

    @Override
    public void completeAsync() {
        this.asyncContext.complete();
    }

    //TODO create Cookie adapter for servlet cookie directly, then user can change it's attribute
    @Override
    public Cookie[] cookies() {
        javax.servlet.http.Cookie[] _cookies = this.request.getCookies();
        if (_cookies == null) return new Cookie[0];
        Cookie[] cookies = new Cookie[_cookies.length];
        for (int i = 0; i< _cookies.length; i++){
            javax.servlet.http.Cookie _cookie = _cookies[i];
            cookies[i] = new Cookie(_cookie.getName()).value(_cookie.getValue());

            //TODO use cookie adapter
            //cookies[i] = new CookieAdapter(_cookie);
        }
        return cookies;
    }

    /**
     *
     * Get session id. If sparkle is configured to use {@link ServletVendorSessionStore} as it's session store,
     * this session id is got from servlet's session, Sparkle will not generate it anymore.
     *
     *
     * @return the session id generated by sparkle framework or from servlet.
     */
    @Override
    public String getSessionId() {
        if (sessionStore() instanceof ServletVendorSessionStore){
            return this.request.getSession().getId();
        }
        return super.getSessionId();
    }

    @Override
    public String host() {
        return this.request.getServerName();
    }

    @Override
    public int ip() {
        return this.request.getServerPort();
    }

    @Override
    public String scheme() {
        return this.request.getScheme();
    }

}



/*

class CookieAdapter extends Cookie{

    private javax.servlet.http.Cookie servletCookie;

    public CookieAdapter(String name) {
        super(name);
    }

    public CookieAdapter(javax.servlet.http.Cookie servletCookie){
        super(null);
        this.servletCookie = servletCookie;
    }


    @Override
    public String name() {
        return this.servletCookie.getName();
    }

    @Override
    public Cookie value(String value) {
        this.servletCookie.setValue(value);
        return this;
    }

    @Override
    public String value() {
        return this.servletCookie.getValue();
    }

    @Override
    public Cookie path(String path) {
        this.servletCookie.setPath(path);
        return this;
    }

    @Override
    public String path() {
        return this.servletCookie.getPath();
    }

    @Override
    public Cookie maxAge(int maxAge) {
        this.servletCookie.setMaxAge(maxAge);
        return this;
    }

    @Override
    public int maxAge() {
        return this.servletCookie.getMaxAge();
    }

    @Override
    public Cookie domain(String domain) {
        this.servletCookie.setDomain(domain);
        return this;
    }

    @Override
    public String domain() {
        return this.servletCookie.getDomain();
    }

}


 */