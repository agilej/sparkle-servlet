package me.donnior.sparkle.servlet;

import me.donnior.sparkle.core.resolver.ServletParamResolversManager;
import me.donnior.sparkle.core.resolver.ParamResolversManager;
import me.donnior.sparkle.core.view.ViewRendersResovler;
import me.donnior.sparkle.engine.RequestLifeCycleManager;
import me.donnior.sparkle.ext.EnvSpecific;

public class ServletSpecific implements EnvSpecific {

    private ParamResolversManager prm = new ServletParamResolversManager();
    
    private ViewRendersResovler vrr = new ServletViewRendersResovler();
    
    private RequestLifeCycleManager rlcm = new ServletRequestLifeCycleManager();
    
    
    @Override
    public ParamResolversManager getParamsResolverManager() {
        return prm;
    }

    @Override
    public ViewRendersResovler getViewRendersResovler() {
        return vrr;
    }

    
    @Override
    public RequestLifeCycleManager getLifeCycleManager() {
        return rlcm;
    }
}
