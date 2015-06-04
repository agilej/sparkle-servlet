package me.donnior.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletRequest;

import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.core.ActionMethodParamDefinition;
import me.donnior.sparkle.core.resolver.ArgumentResolver;

/**
 * Argument resolver for argument with type HttpServletRequest 
 *
 */
public class HttpRequestArgumentResolver implements ArgumentResolver {

    @Override
    public boolean support(ActionMethodParamDefinition actionParamDefinition) {
        return actionParamDefinition.paramType().equals(HttpServletRequest.class);
    }

    @Override
    public Object resolve(ActionMethodParamDefinition actionParamDefinition, WebRequest request) {
        return request.getOriginalRequest();
    }

}
