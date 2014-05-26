package me.donnior.sparkle.servlet;

import java.util.List;

import me.donnior.sparkle.core.view.ViewRender;
import me.donnior.sparkle.core.view.ViewRenderManager;
import me.donnior.sparkle.servlet.view.JSPViewRender;
import me.donnior.sparkle.servlet.view.RedirectViewRender;

public class ServletViewRenderManager extends ViewRenderManager {

    @Override
    public void registerCustomViewRenders(List<ViewRender> viewRenders) {
        viewRenders.add(new RedirectViewRender());
        viewRenders.add(new JSPViewRender());
    }
    
}
