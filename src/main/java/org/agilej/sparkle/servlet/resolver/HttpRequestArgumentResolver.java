package org.agilej.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletRequest;

import org.agilej.sparkle.WebRequest;
import org.agilej.sparkle.mvc.ActionMethodParameter;
import org.agilej.sparkle.mvc.ArgumentResolver;


/**
 * Argument resolver for argument with type {@link HttpServletRequest}
 *
 */
public class HttpRequestArgumentResolver implements ArgumentResolver {

    @Override
    public boolean support(ActionMethodParameter actionMethodParameter) {
        return actionMethodParameter.paramType().equals(HttpServletRequest.class);
    }

    @Override
    public Object resolve(ActionMethodParameter actionMethodParameter, WebRequest request) {
        return request.getOriginalRequest();
    }

}
