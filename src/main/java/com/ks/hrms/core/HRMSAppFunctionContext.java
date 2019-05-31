package com.ks.hrms.core;

import com.sun.deploy.ui.AppInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HRMSAppFunctionContext extends AbstractAppFunctionContext {

    public static HRMSAppFunctionContext getInstance(){
        return Factory.init();
    }

    static class Factory{

        private static HRMSAppFunctionContext app = null;

        public static HRMSAppFunctionContext init(){
            if(null==app){
                ApplicationContext context = new ClassPathXmlApplicationContext(applicationContextConfig);
                app = context.getBean("appFunctionContext",HRMSAppFunctionContext.class);
                app.init();
                return app;
            }else{
                return app;
            }

        }
    }
}
