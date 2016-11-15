package org.agilej.sparkle.servlet;

import org.agilej.sparkle.core.argument.ArgumentResolver;
import org.agilej.sparkle.core.view.ViewRender;
import org.agilej.sparkle.servlet.resolver.HttpRequestArgumentResolver;
import org.agilej.sparkle.servlet.resolver.HttpResponseArgumentResolver;
import org.agilej.sparkle.servlet.view.JSPViewRender;
import org.agilej.sparkle.servlet.view.RedirectViewRender;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServletSpecificTest {

    @Test
    public void testRegisterVendorViewRender() {
        ServletSpecific ss = new ServletSpecific();
        List<ViewRender> vrs = ss.vendorViewRenderProvider().vendorViewRenders();

        assertEquals(2, vrs.size());
        assertEquals(RedirectViewRender.class, vrs.get(0).getClass());
        assertEquals(JSPViewRender.class, vrs.get(1).getClass());
    }

    @Test
    public void testRegisterVendorArguemtnResolver() {
        ServletSpecific ss = new ServletSpecific();
        List<ArgumentResolver> ars = ss.vendorArgumentResolverProvider().vendorArgumentResolvers();

        assertEquals(2, ars.size());
        assertEquals(HttpRequestArgumentResolver.class, ars.get(0).getClass());
        assertEquals(HttpResponseArgumentResolver.class, ars.get(1).getClass());
    }
}
