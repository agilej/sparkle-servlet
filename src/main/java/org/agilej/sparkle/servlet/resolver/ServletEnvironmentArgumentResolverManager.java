package org.agilej.sparkle.servlet.resolver;

import org.agilej.sparkle.core.argument.*;

public class ServletEnvironmentArgumentResolverManager extends AbstractArgumentResolverManager {
    
    public ServletEnvironmentArgumentResolverManager() {
        registerArgumentResolver(new SimpleArgumentResolver());
        registerArgumentResolver(new WebRequestArgumentResolver());
        registerArgumentResolver(new HttpRequestArgumentResolver());
        registerArgumentResolver(new HttpResponseArgumentResolver());
        registerArgumentResolver(new PathVariableArgumentResolver());
        registerArgumentResolver(new ParamsArgumentResolver());
        registerArgumentResolver(new CookiesArgumentResolver());
    }
        
}
