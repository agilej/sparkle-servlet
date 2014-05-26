package me.donnior.sparkle.servlet;

import java.util.List;

import me.donnior.sparkle.core.view.ViewRender;
import me.donnior.sparkle.core.view.ViewRendersResovler;
import me.donnior.sparkle.servlet.view.JSPViewRender;
import me.donnior.sparkle.servlet.view.RedirectViewRender;

public class ServletViewRendersResovler extends ViewRendersResovler {

    @Override
    public void registerCustomViewRenders(List<ViewRender> viewRenders) {
        viewRenders.add(new RedirectViewRender());
        viewRenders.add(new JSPViewRender());
    }
    
}
