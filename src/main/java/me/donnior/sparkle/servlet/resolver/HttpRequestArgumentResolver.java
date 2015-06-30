package me.donnior.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletRequest;

import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.core.ActionMethodParameter;
import me.donnior.sparkle.core.resolver.ArgumentResolver;

/**
 * Argument resolver for argument with type HttpServletRequest 
 *
 */
public class HttpRequestArgumentResolver implements ArgumentResolver {

    @Override
    public boolean support(ActionMethodParameter actionParamDefinition) {
        return actionParamDefinition.paramType().equals(HttpServletRequest.class);
    }

    @Override
    public Object resolve(ActionMethodParameter actionParamDefinition, WebRequest request) {
        return request.getOriginalRequest();
    }

}
