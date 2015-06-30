package me.donnior.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletRequest;

import me.donnior.sparkle.Params;
import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.core.ActionMethodParameter;
import me.donnior.sparkle.core.resolver.ArgumentResolver;

/**
 * Argument resolver for argument  with type {@link Params} 
 *
 */
public class ParamInstanceArgumentResolver implements ArgumentResolver {

    @Override
    public boolean support(ActionMethodParameter actionParamDefinition) {
        return Params.class.equals(actionParamDefinition.paramType());
    }

    @Override
    public Object resolve(ActionMethodParameter actionParamDefinition, WebRequest request) {
      return new HttpRequestParamsWraper((HttpServletRequest)request.getOriginalRequest());
    }

}
