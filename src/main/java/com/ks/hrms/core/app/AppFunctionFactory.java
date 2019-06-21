package com.ks.hrms.core.app;

import com.ks.hrms.controller.AppMenuItem;
import com.ks.hrms.core.component.SpinnerLayout;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Task;

import java.lang.reflect.Constructor;

public class AppFunctionFactory {

    public static ReadOnlyObjectProperty<AppFunctionMain> generate(final SpinnerLayout spinnerLayout, final AppMenuItem item) {

        //打开spinnerLayout

        Task<AppFunctionMain> task = new Task() {
            @Override
            protected AppFunctionMain call() throws Exception {
                running();
                AppFunctionMain app = generateApp(item);
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

    private static AppFunctionMain generateApp(AppMenuItem item) {
        try {
            Class clazz = Class.forName(item.getClassPath());
            HRMSAppFunctionContext context = HRMSAppFunctionContext.getInstance();
            Object object = clazz.newInstance();
            if (null != object) {
                ((AppFunctionMain) object).init(context);
                return (AppFunctionMain) object;
            } else {
                return null;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
