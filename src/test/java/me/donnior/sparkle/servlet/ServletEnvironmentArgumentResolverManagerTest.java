package me.donnior.sparkle.servlet;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.lang.annotation.Annotation;
import java.util.List;

import me.donnior.sparkle.annotation.Json;
import me.donnior.sparkle.annotation.Param;
import me.donnior.sparkle.core.ActionMethod;
import me.donnior.sparkle.core.ActionMethodParameter;
import me.donnior.sparkle.core.argument.ParamsArgumentResolver;
import me.donnior.sparkle.core.argument.SimpleArgumentResolver;
import me.donnior.sparkle.core.method.ActionMethodResolver;
import me.donnior.sparkle.core.method.DefaultActionMethodParameter;
import me.donnior.sparkle.servlet.ServletWebRequest;
import me.donnior.sparkle.servlet.resolver.ParamInstanceArgumentResolver;
import me.donnior.sparkle.servlet.resolver.ServletEnvironmentArgumentResolverManager;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ServletEnvironmentArgumentResolverManagerTest {
    
    private ServletEnvironmentArgumentResolverManager manager;
    
    @Before
    public void setup(){
        manager = new ServletEnvironmentArgumentResolverManager();
    }
    
    @Test
    public void testDefaultConstructor(){
        assertEquals(6, manager.registeredResolvers().size());
        assertEquals(ParamsArgumentResolver.class, manager.registeredResolvers().get(5).getClass());
        assertEquals(SimpleArgumentResolver.class, manager.registeredResolvers().get(0).getClass());
    }
    
    @Test (expected=RuntimeException.class)
    public void testCannotFindProperResolver(){
        
        List<Annotation> annotations = Lists.newArrayList();
        annotations.add(new Annotation() {
            
            @Override
            public Class<? extends Annotation> annotationType() {
                return Json.class;
            }
        });
        
        ActionMethodParameter actionParamDefinition =
                new DefaultActionMethodParameter(String.class, annotations);
        
        manager.resolve(actionParamDefinition, null);
        fail();
    }

    @Test
    public void testResolveSucceed(){
        
        ActionMethod actionParamDefinition =
                new ActionMethodResolver().find(ControllerForDefaultParamResolversManagerTest.class, "index");
        assertEquals(1, actionParamDefinition.parameters().size());
               
                
        Object obj = manager.resolve(actionParamDefinition.parameters().get(0),
                new ServletWebRequest(new HttpServletRequestAdapter(), null));
        assertNull(obj);
    }
    
    
}

class ControllerForDefaultParamResolversManagerTest{
    
    @Json
    public String index(@Param("page") Integer page){
        return null;
    }
    
    public String show(){
        return null;
    }
    
    public String show(int id){
        return null;
    }
}