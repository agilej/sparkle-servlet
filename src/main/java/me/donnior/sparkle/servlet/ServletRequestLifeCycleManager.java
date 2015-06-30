package me.donnior.sparkle.servlet;

import javax.servlet.http.HttpServletResponse;

import me.donnior.fava.Predicate;
import me.donnior.fava.util.FLists;
import me.donnior.sparkle.core.ActionMethod;
import me.donnior.sparkle.core.ActionMethodParameter;
import me.donnior.sparkle.engine.RequestLifeCycleManager;

public class ServletRequestLifeCycleManager implements RequestLifeCycleManager {

    /**
     * if one action method has a HttpServletResponse argument, then suppose user want process response manually,
     * will ignore the view rendering phase.
     * @param adf
     * @return
     */
    @Override
    public boolean isResponseProcessedManually(ActionMethod adf) {
        return FLists.create(adf.paramDefinitions()).any(new Predicate<ActionMethodParameter>() {
            
            @Override
            public boolean apply(ActionMethodParameter apd) {
                return apd.paramType().equals(HttpServletResponse.class);
            }
        });
    }

}
