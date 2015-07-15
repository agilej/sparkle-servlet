package me.donnior.sparkle.servlet;

import java.util.List;

import me.donnior.sparkle.core.view.ViewRender;
import me.donnior.sparkle.core.view.ViewRenderManager;
import me.donnior.sparkle.servlet.view.JSPViewRender;
import me.donnior.sparkle.servlet.view.RedirectViewRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletViewRenderManager extends ViewRenderManager {

    private final static Logger logger = LoggerFactory.getLogger(ServletViewRenderManager.class);

    @Override
    public void registerVendorViewRenders(List<ViewRender> viewRenders) {
        logger.info("register vendor view renders : [RedirectViewRender, JSPViewRender]");
        viewRenders.add(new RedirectViewRender());
        viewRenders.add(new JSPViewRender());
    }
    
}
