package me.donnior.sparkle.servlet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import me.donnior.sparkle.core.view.ViewRender;
import me.donnior.sparkle.servlet.view.JSPViewRender;
import me.donnior.sparkle.servlet.view.RedirectViewRender;

import org.junit.Test;

public class ServletViewRendersResovlerTest {

    @Test
    public void testResolveViewRenders(){
        ServletViewRenderManager r = new ServletViewRenderManager();
        List<Class<? extends ViewRender>> viewRenderClasses = new ArrayList<Class<? extends ViewRender>>();
        List<? extends ViewRender> resovleRegisteredViewRenders = r.getAllOrderedViewRenders();
        
        assertTrue(resovleRegisteredViewRenders.size() == 4);
        assertEquals(RedirectViewRender.class, resovleRegisteredViewRenders.get(2).getClass());
        assertEquals(JSPViewRender.class, resovleRegisteredViewRenders.get(3).getClass());
    }
    
}
