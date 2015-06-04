package me.donnior.sparkle.servlet.resolver;

import javax.servlet.http.HttpServletRequest;

import me.donnior.sparkle.Params;
import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.core.ActionMethodParamDefinition;
import me.donnior.sparkle.core.resolver.ArgumentResolver;

/**
 * Argument resolver for argument  with type {@link Params} 
 *
 */
public class ParamInstanceArgumentResolver implements ArgumentResolver {

    @Override
    public boolean support(ActionMethodParamDefinition actionParamDefinition) {
        return Params.class.equals(actionParamDefinition.paramType());
    }

    @Override
    public Object resolve(ActionMethodParamDefinition actionParamDefinition, WebRequest request) {
      return new HttpRequestParamsWraper((HttpServletRequest)request.getOriginalRequest());
    }

}
