package me.donnior.sparkle.servlet.resolver;

import me.donnior.sparkle.core.resolver.AbstractParamResolversManager;
import me.donnior.sparkle.core.resolver.PathVariableArgumentResolver;
import me.donnior.sparkle.core.resolver.SimpleArgumentResolver;

public class ServletParamResolversManager extends AbstractParamResolversManager {
    
    public ServletParamResolversManager() {
        registerArgumentResolver(new HttpRequestArgumentResolver());
        registerArgumentResolver(new HttpResponseArgumentResolver());
        registerArgumentResolver(new ParamInstanceArgumentResolver());
        registerArgumentResolver(new SimpleArgumentResolver());
        registerArgumentResolver(new PathVariableArgumentResolver());
    }
        
}
