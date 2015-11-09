package org.agilej.sparkle.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.agilej.sparkle.WebRequest;

import org.agilej.sparkle.core.request.CookieBasedSessionStore;
import org.agilej.sparkle.core.request.SessionStoreHolder;
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

    @Test
    public void test_get_session_id(){
        final String SESSION_ID = "servlet_request_session_id";
        HttpServletRequest servletRequest = new HttpServletRequestAdapter(){
            @Override
            public HttpSession getSession() {
                return new HttpServletSessionAdapter() {
                    @Override
                    public String getId() {
                        return SESSION_ID;
                    }
                };
            }
        };

        WebRequest request = new ServletWebRequest(servletRequest, null);
        SessionStoreHolder.set(new ServletVendorSessionStore());
        String sid = request.getSessionId();
        assertEquals(SESSION_ID, sid);

        try {
            SessionStoreHolder.set(new CookieBasedSessionStore());
            sid = request.getSessionId();
            fail();
        } catch (RuntimeException re){

        }

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

