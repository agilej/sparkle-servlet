package me.donnior.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletResponse;

import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.annotation.Param;
import me.donnior.sparkle.core.ActionMethodParameter;
import me.donnior.sparkle.core.resolver.ArgumentResolver;

/**
 * Argument resolver for argument annotated with {@link Param} 
 *
 */
public class HttpResponseArgumentResolver implements ArgumentResolver {

    @Override
    public boolean support(ActionMethodParameter actionParamDefinition) {
        return actionParamDefinition.paramType().equals(HttpServletResponse.class);
    }

    @Override
    public Object resolve(ActionMethodParameter actionParamDefinition, WebRequest request) {
        //TODO introduce response as param or use simply solution?
        return request.getOriginalResponse();
    }

}
