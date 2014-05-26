package demo;

import me.donnior.sparkle.config.Application;
import me.donnior.sparkle.config.Config;

public class DemoApplicationConfig implements Application{

    @Override
    public void config(Config config) {
        config.registerInterceptor(new DemoAppInterceptor());
    }

}
