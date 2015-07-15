package me.donnior.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletResponse;

import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.annotation.Param;
import me.donnior.sparkle.core.ActionMethodParameter;
import me.donnior.sparkle.core.argument.ArgumentResolver;

/**
 * Argument resolver for argument with type {@link HttpServletResponse}
 *
 */
public class HttpResponseArgumentResolver implements ArgumentResolver {

    @Override
    public boolean support(ActionMethodParameter actionMethodParameter) {
        return actionMethodParameter.paramType().equals(HttpServletResponse.class);
    }

    @Override
    public Object resolve(ActionMethodParameter actionMethodParameter, WebRequest request) {
        return request.getOriginalResponse();
    }

}
