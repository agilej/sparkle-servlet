package me.donnior.sparkle.servlet;

import me.donnior.sparkle.core.argument.ArgumentResolverManager;
import me.donnior.sparkle.core.view.ViewRender;
import me.donnior.sparkle.core.view.ViewRenderManager;
import me.donnior.sparkle.engine.RequestLifeCycleManager;
import me.donnior.sparkle.ext.EnvSpecific;
import me.donnior.sparkle.ext.VendorViewRenderProvider;
import me.donnior.sparkle.servlet.resolver.ServletEnvironmentArgumentResolverManager;
import me.donnior.sparkle.servlet.view.JSPViewRender;
import me.donnior.sparkle.servlet.view.RedirectViewRender;
import org.agilej.fava.util.FLists;

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
