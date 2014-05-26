package me.donnior.sparkle.servlet.resolver;

import me.donnior.sparkle.core.resolver.AbstractArgumentResolverManager;
import me.donnior.sparkle.core.resolver.PathVariableArgumentResolver;
import me.donnior.sparkle.core.resolver.SimpleArgumentResolver;
import me.donnior.sparkle.core.resolver.WebRequestArgumentResolver;

public class ServletEnvironmentArgumentResolverManager extends AbstractArgumentResolverManager {
    
    public ServletEnvironmentArgumentResolverManager() {
        registerArgumentResolver(new WebRequestArgumentResolver());
        registerArgumentResolver(new HttpRequestArgumentResolver());
        registerArgumentResolver(new HttpResponseArgumentResolver());
        registerArgumentResolver(new ParamInstanceArgumentResolver());
        registerArgumentResolver(new SimpleArgumentResolver());
        registerArgumentResolver(new PathVariableArgumentResolver());
    }
        
}
