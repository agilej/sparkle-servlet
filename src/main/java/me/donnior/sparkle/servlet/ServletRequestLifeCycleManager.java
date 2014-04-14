package me.donnior.sparkle.servlet;

import javax.servlet.http.HttpServletResponse;

import me.donnior.fava.Predicate;
import me.donnior.fava.util.FLists;
import me.donnior.sparkle.core.ActionMethodDefinition;
import me.donnior.sparkle.core.ActionMethodParamDefinition;
import me.donnior.sparkle.engine.RequestLifeCycleManager;

public class ServletRequestLifeCycleManager implements RequestLifeCycleManager {

    /**
     * if one action method has a HttpServletResponse argument, then suppose user want process response manually,
     * will ignore the view rendering phase.
     * @param adf
     * @return
     */
    @Override
    public boolean isResponseProcessedManually(ActionMethodDefinition adf) {
        return FLists.create(adf.paramDefinitions()).any(new Predicate<ActionMethodParamDefinition>() {
            
            @Override
            public boolean apply(ActionMethodParamDefinition apd) {
                return apd.paramType().equals(HttpServletResponse.class);
            }
        });
    }

}
