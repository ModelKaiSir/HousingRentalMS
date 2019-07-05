package com.ks.hrms.core.app;

import com.ks.hrms.controller.AppMenuItem;
import com.ks.hrms.core.component.SpinnerLayout;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

public class AppFunctionFactory {

    public static ReadOnlyObjectProperty<AppFunctionBase> generate(final SpinnerLayout spinnerLayout, final AppMenuItem item) {

        //打开spinnerLayout

        Task<AppFunctionBase> task = new Task() {
            @Override
            protected AppFunctionBase call() throws Exception {
                running();
                AppFunctionBase app = generateApp(item);
                updateValue(app);
                succeeded();
                return app;
            }
        };

        task.setOnRunning(e ->{
            spinnerLayout.show();
        });

        task.setOnSucceeded(e ->{
            System.out.println(task.getValue());
            spinnerLayout.hide();
        });

        Thread t = new Thread(task);
        t.start();

        return task.valueProperty();
    }

    private static AppFunctionBase generateApp(AppMenuItem item) {
        try {
            Class clazz = Class.forName(item.getClassPath());
            HRMSAppFunctionContext context = HRMSAppFunctionContext.getInstance();
            Object object = clazz.newInstance();
            if (null != object) {
                ((AppFunctionBase) object).init(context);
                TimeUnit.SECONDS.sleep(1);
                return (AppFunctionBase) object;
            } else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
