package org.agilej.sparkle.servlet.initializer;

import java.util.Set;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.agilej.sparkle.core.engine.SparkleEngine;
import org.agilej.sparkle.servlet.ServletSpecific;
import org.agilej.sparkle.servlet.StaticWrapperServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkleWebAppInitializer implements ServletContainerInitializer {
    
    private static final String SPARKLE_SERVLET_NAME = "sparkleServlet";
    private static final String STATIC_SERVLET_NAME = "staticServlet";
    
    private final static Logger logger = LoggerFactory.getLogger(SparkleWebAppInitializer.class);
    
    public SparkleWebAppInitializer() {
        
    }

    @Override
    public void onStartup(Set<Class<?>> arg0, ServletContext servletContext) throws ServletException {
        
        SparkleEngine engine = new SparkleEngine(new ServletSpecific());
        SparkleDispatcherServlet dispatcherServlet = new SparkleDispatcherServlet(engine);
        
        ServletRegistration.Dynamic appServlet = servletContext.addServlet(SPARKLE_SERVLET_NAME, dispatcherServlet);
        appServlet.setLoadOnStartup(1);
        
        //TODO how to deal with this multipart configuration
        MultipartConfigElement mc = new MultipartConfigElement("/tmp", 1024*1024*10, -1, -1);
        appServlet.setMultipartConfig(mc);
        Set<String> mappingConflicts = appServlet.addMapping("/");
        
        appServlet.setAsyncSupported(true);
        
        ensureContainerSpecifiedEnv(mappingConflicts);
        
        StaticWrapperServlet staticWrapperServlet = new StaticWrapperServlet("/static");
        ServletRegistration.Dynamic staticServlet = servletContext.addServlet(STATIC_SERVLET_NAME, staticWrapperServlet);
        staticServlet.setLoadOnStartup(1);
        staticServlet.addMapping("/static/*");
    }

    //deal with container specified env issue, like tomcat's version support
    private void ensureContainerSpecifiedEnv(Set<String> mappingConflicts) {
        if (!mappingConflicts.isEmpty()) {
            throw new IllegalStateException(
                    "'appServlet' could not be mapped to '/' due "
                            + "to an existing mapping. This is a known issue under Tomcat versions "
                            + "<= 7.0.14;");
        }
    }

}