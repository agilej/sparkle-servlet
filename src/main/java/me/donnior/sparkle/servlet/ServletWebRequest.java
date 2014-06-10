package me.donnior.sparkle.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import me.donnior.fava.Function;
import me.donnior.fava.util.FLists;
import me.donnior.sparkle.Multipart;
import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.WebResponse;

public class ServletWebRequest implements WebRequest{

    private final WebResponse webResponse;
    private final HttpServletRequest request;

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
        System.out.println("----- begin read");
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
    
}
