package me.donnior.sparkle.servlet;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import me.donnior.sparkle.WebRequest;

import org.junit.Test;

public class ServletWebRequestTest{
    
    @Test
    public void test_extract_normal_path(){
        WebRequest request = new ServletWebRequest(normalPathRequest(), null);
        assertEquals("/pathinfo", request.getPath());
    }
    
    @Test
    public void test_extract_wild_path(){
        WebRequest request = new ServletWebRequest(wildPathRequest(), null);
        assertEquals("/servletPath", request.getPath());
    }

    private HttpServletRequest normalPathRequest() {
        return new HttpServletRequestAdapter(){
            @Override
            public String getPathInfo() {
                return "/pathinfo";
            }
            
            @Override
            public String getServletPath() {
                return "/servletPath";
            }
            
        };
    }

    private HttpServletRequest wildPathRequest() {
        return new HttpServletRequestAdapter(){
            @Override
            public String getPathInfo() {
                return null;
            }
            
            @Override
            public String getServletPath() {
                return "/servletPath";
            }
            
        };
    }
    
    
}

