package me.donnior.sparkle.servlet;

import me.donnior.sparkle.core.resolver.ServletParamResolversManager;
import me.donnior.sparkle.core.resolver.ParamResolversManager;
import me.donnior.sparkle.core.view.ViewRendersResovler;
import me.donnior.sparkle.ext.EnvSpecific;

public class ServletSpecific implements EnvSpecific {

    @Override
    public ParamResolversManager getParamsResolverManager() {
        return new ServletParamResolversManager();
    }

    @Override
    public ViewRendersResovler getViewRendersResovler() {
        return new ServletViewRendersResovler();
    }

}
