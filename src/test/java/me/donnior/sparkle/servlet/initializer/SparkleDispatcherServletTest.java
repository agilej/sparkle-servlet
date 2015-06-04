package me.donnior.sparkle.servlet.initializer;

import java.io.IOException;

import javax.servlet.ServletException;

import me.donnior.sparkle.HTTPMethod;
import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.engine.SparkleEngine;
import me.donnior.sparkle.servlet.initializer.SparkleDispatcherServlet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SparkleDispatcherServletTest {

    private SparkleDispatcherServlet servlet;
    
    @Before
    public void setup(){
        servlet = new SparkleDispatcherServlet(new MockSparkleEngine());
    }
    
    @Test
    @Ignore
    public void test() throws ServletException, IOException{
        servlet.doGet(null, null);
        
        servlet.doHead(null, null);
        
        
        servlet.doPost(null, null);
        
        servlet.doDelete(null, null);
        
        servlet.doPut(null, null);
        
        servlet.doOptions(null, null);
        
        servlet.doTrace(null, null);
    }
    
}

class MockSparkleEngine extends SparkleEngine{

    public MockSparkleEngine() {
        super(null);
    }
    
    @Override
    protected void startup() {

    }

    @Override
    public void doService(WebRequest request, HTTPMethod method) {
        
    }
    
    
    
    
}