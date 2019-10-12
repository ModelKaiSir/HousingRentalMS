package com.ks.hrms.core.context;

import com.ks.hrms.controller.AppMenuItem;
import com.ks.hrms.core.app.AbstractAppFunction;
import com.ks.hrms.core.app.AppFunctionManager;
import com.ks.hrms.core.app.Function;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppFunctionContext extends DoAppFunctionContext {

    private AppFunctionManager functionManager = new AppFunctionManager();
    private ObjectProperty<AbstractAppFunction> localApp = new SimpleObjectProperty<>();

    public Function getLocalApp() {
        return localApp.get();
    }

    public ObjectProperty<AbstractAppFunction> localAppProperty() {
        return localApp;
    }

    public void setLocalApp(AbstractAppFunction localApp) {
        this.localApp.set(localApp);
    }

    public void open(AppMenuItem menuItem) {
        localApp.set(functionManager.generateAppFunction(menuItem));
    }

    public static AppFunctionContext getInstance() {
        return Factory.init();
    }

    static class Factory {

        private static AppFunctionContext app = null;

        public static AppFunctionContext init() {
            if (null == app) {
                ApplicationContext context = new ClassPathXmlApplicationContext(applicationContextConfig);
                app = context.getBean("appFunctionContext", AppFunctionContext.class);
                app.init();
                return app;
            } else {
                return app;
            }

        }
    }
}
