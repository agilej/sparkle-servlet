package me.donnior.sparkle.core.resolver;

public class ServletParamResolversManager extends AbstractParamResolversManager {
    
    public ServletParamResolversManager() {
        registerArgumentResolver(new HttpRequestArgumentResolver());
        registerArgumentResolver(new HttpResponseArgumentResolver());
        registerArgumentResolver(new ParamInstanceArgumentResolver());
        registerArgumentResolver(new SimpleArgumentResolver());
        registerArgumentResolver(new PathVariableArgumentResolver());
    }
        
}
