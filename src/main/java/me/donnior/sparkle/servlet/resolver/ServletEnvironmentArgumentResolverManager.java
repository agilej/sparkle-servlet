package me.donnior.sparkle.servlet.resolver;

import me.donnior.sparkle.core.resolver.*;

public class ServletEnvironmentArgumentResolverManager extends AbstractArgumentResolverManager {
    
    public ServletEnvironmentArgumentResolverManager() {
        registerArgumentResolver(new SimpleArgumentResolver());
        registerArgumentResolver(new WebRequestArgumentResolver());
        registerArgumentResolver(new HttpRequestArgumentResolver());
        registerArgumentResolver(new HttpResponseArgumentResolver());
        registerArgumentResolver(new PathVariableArgumentResolver());
        registerArgumentResolver(new ParamsArgumentResolver());
    }
        
}
