package org.agilej.sparkle.servlet;

import org.agilej.sparkle.core.argument.ArgumentResolverManager;
import org.agilej.sparkle.core.view.ViewRender;
import org.agilej.sparkle.core.engine.RequestLifeCycleManager;
import org.agilej.sparkle.core.ext.EnvSpecific;
import org.agilej.sparkle.core.ext.VendorViewRenderProvider;
import org.agilej.sparkle.servlet.resolver.ServletEnvironmentArgumentResolverManager;
import org.agilej.sparkle.servlet.view.JSPViewRender;
import org.agilej.sparkle.servlet.view.RedirectViewRender;

import java.util.List;

import static org.agilej.fava.util.FStatic.list;

public class ServletSpecific implements EnvSpecific {

    private ArgumentResolverManager prm = new ServletEnvironmentArgumentResolverManager();
    
    private RequestLifeCycleManager rlcm = new ServletRequestLifeCycleManager();

    @Override
    public ArgumentResolverManager getArgumentResolverManager() {
        return prm;
    }
    
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
}
