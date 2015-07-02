package me.donnior.sparkle.servlet;

import me.donnior.sparkle.core.argument.ArgumentResolverManager;
import me.donnior.sparkle.core.view.ViewRenderManager;
import me.donnior.sparkle.engine.RequestLifeCycleManager;
import me.donnior.sparkle.ext.EnvSpecific;
import me.donnior.sparkle.servlet.resolver.ServletEnvironmentArgumentResolverManager;

public class ServletSpecific implements EnvSpecific {

    private ArgumentResolverManager prm = new ServletEnvironmentArgumentResolverManager();
    
    private ViewRenderManager vrr = new ServletViewRenderManager();
    
    private RequestLifeCycleManager rlcm = new ServletRequestLifeCycleManager();
    
    
    @Override
    public ArgumentResolverManager getArgumentResolverManager() {
        return prm;
    }

    @Override
    public ViewRenderManager getViewRendersManager() {
        return vrr;
    }

    
    @Override
    public RequestLifeCycleManager getLifeCycleManager() {
        return rlcm;
    }
}
