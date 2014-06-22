package me.donnior.sparkle.servlet.initializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.donnior.sparkle.HTTPMethod;
import me.donnior.sparkle.engine.SparkleEngine;
import me.donnior.sparkle.servlet.ServletSpecific;
import me.donnior.sparkle.servlet.ServletWebRequest;

@MultipartConfig(maxFileSize=-1, fileSizeThreshold=1024 * 1024 * 10, location="/tmp")
public class SparkleDispatcherServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    
    private SparkleEngine sparkle;
    
    public SparkleDispatcherServlet() {
        this(new SparkleEngine(new ServletSpecific()));
    }

    public SparkleDispatcherServlet(SparkleEngine engine) {
        this.sparkle = engine;
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        this.doService0(req, resp, HTTPMethod.GET);
    }
    
    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doService0(req, resp, HTTPMethod.HEAD);
    }
    
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doService0(req, resp, HTTPMethod.DELETE);
    }
    
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doService0(req, resp, HTTPMethod.OPTIONS);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doService0(req, resp, HTTPMethod.POST);
    }
    
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doService0(req, resp, HTTPMethod.PUT);
    }
    
    
    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doService0(req, resp, HTTPMethod.TRACE);
    }
    
    private void doService0(HttpServletRequest req, HttpServletResponse resp, HTTPMethod method) 
            throws UnsupportedEncodingException{
        req.setCharacterEncoding("UTF-8");
        this.sparkle.doService(new ServletWebRequest(req, resp), method);
    }
    

}
