package org.agilej.sparkle.servlet;

import org.agilej.sparkle.core.argument.ArgumentResolver;
import org.agilej.sparkle.core.argument.ArgumentResolverManager;
import org.agilej.sparkle.core.view.ViewRender;
import org.agilej.sparkle.core.engine.RequestLifeCycleManager;
import org.agilej.sparkle.core.ext.EnvSpecific;
import org.agilej.sparkle.core.ext.VendorViewRenderProvider;
import org.agilej.sparkle.core.ext.VendorArgumentResolverProvider;
import org.agilej.sparkle.servlet.view.JSPViewRender;
import org.agilej.sparkle.servlet.view.RedirectViewRender;
import org.agilej.sparkle.servlet.resolver.HttpResponseArgumentResolver;
import org.agilej.sparkle.servlet.resolver.HttpRequestArgumentResolver;

import java.util.List;

import static org.agilej.fava.util.FStatic.list;

public class ServletSpecific implements EnvSpecific {


    private RequestLifeCycleManager rlcm = new ServletRequestLifeCycleManager();

    @Override
    public RequestLifeCycleManager getLifeCycleManager() {
        return rlcm;
    }

    @Override
    public VendorViewRenderProvider vendorViewRenderProvider() {
        return new VendorViewRenderProvider() {
            @Override
            public List<ViewRender> vendorViewRenders() {
                return list(new RedirectViewRender(), new JSPViewRender());
            }
        };
    }

    @Override
    public VendorArgumentResolverProvider vendorArgumentResolverProvider() {
        return new VendorArgumentResolverProvider() {
            @Override
            public List<ArgumentResolver> vendorArgumentResolvers() {
                return list(new HttpRequestArgumentResolver(), new HttpResponseArgumentResolver());
            }
        };
    }
}
