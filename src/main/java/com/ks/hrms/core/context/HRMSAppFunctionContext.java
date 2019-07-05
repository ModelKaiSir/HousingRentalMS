package com.ks.hrms.core.context;

import com.ks.hrms.core.app.AppFunctionBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HRMSAppFunctionContext extends AbstractAppFunctionContext {

    private ObjectProperty<AppFunctionBase> localApp = new SimpleObjectProperty<>();

    public static HRMSAppFunctionContext getInstance(){
        return Factory.init();
    }

    public AppFunctionBase getLocalApp() {
        return localApp.get();
    }

    public ObjectProperty<AppFunctionBase> localAppProperty() {
        return localApp;
    }

    public void setLocalApp(AppFunctionBase localApp) {
        this.localApp.set(localApp);
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
