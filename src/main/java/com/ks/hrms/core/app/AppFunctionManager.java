package com.ks.hrms.core.app;

import com.ks.hrms.controller.AppMenuItem;
import com.ks.hrms.core.context.AppFunctionContext;

public class AppFunctionManager {

    public AppFunctionManager() {

    }

    public AbstractAppFunction generateAppFunction(AppMenuItem menuItem) {

        String id = menuItem.getFunctionId();
        String menuName = menuItem.getMenuName();
        String classPath = menuItem.getClassPath();

        Function function = generateObject(classPath, Function.class);
        function.init();
        function.setCaption(menuName);
        return (AbstractAppFunction) function;
    }

    private <T> T generateObject(String classPath, Class<T> clazz) {

        try {

            Class cl = Class.forName(classPath);
            Object obj = cl.newInstance();
            return (T) obj;

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
