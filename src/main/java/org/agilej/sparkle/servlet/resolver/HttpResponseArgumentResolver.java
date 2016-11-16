package org.agilej.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletResponse;

import org.agilej.sparkle.WebRequest;
import org.agilej.sparkle.annotation.Param;
import org.agilej.sparkle.mvc.ActionMethodParameter;
import org.agilej.sparkle.mvc.ArgumentResolver;

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
