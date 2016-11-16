package org.agilej.sparkle.servlet;

import javax.servlet.http.HttpServletResponse;


import org.agilej.fava.Predicate;
import org.agilej.fava.util.FLists;
import org.agilej.sparkle.core.ext.RequestLifeCycleManager;
import org.agilej.sparkle.mvc.ActionMethod;
import org.agilej.sparkle.mvc.ActionMethodParameter;

public class ServletRequestLifeCycleManager implements RequestLifeCycleManager {

    /**
     * if one action method has a HttpServletResponse argument, then suppose user want process response manually,
     * will ignore the view rendering phase.
     * @param actionMethod
     * @return
     */
    @Override
    public boolean isResponseProcessedManually(ActionMethod actionMethod) {
        return FLists.create(actionMethod.parameters()).any(new Predicate<ActionMethodParameter>() {

            @Override
            public boolean apply(ActionMethodParameter apd) {
                return apd.paramType().equals(HttpServletResponse.class);
            }
        });
    }

}
