package demo;

import me.donnior.sparkle.WebRequest;
import me.donnior.sparkle.WebResponse;
import me.donnior.sparkle.interceptor.Interceptor;

public class DemoAppInterceptor implements Interceptor {

    @Override
    public void afterHandle(WebRequest request, WebResponse response) {
        System.out.println("do after action");
    }

    @Override
    public boolean preHandle(WebRequest request, WebResponse response) {
        System.out.println("do before action");
        if("404".equals(request.getParameter("status"))){
            response.setStatus(404);
            return false;
        }
        return true;
    }

}
