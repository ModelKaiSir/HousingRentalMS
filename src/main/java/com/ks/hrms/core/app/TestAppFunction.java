package com.ks.hrms.core.app;

public class TestAppFunction extends AppFunction {

    @Override
    public void init() {
        super.init();
        setText("测试Function");
    }

    @Override
    public AppFunctionMain createMain() {
        AppFunctionMain main = new TestAppFunctionMain();
        main.setAppManager(this);
        return main;
    }

    @Override
    public AppFunctionNavigator createNavigator() {
        AppFunctionNavigator navigator = new AppFunctionNavigator();
        navigator.setAppManager(this);
        return navigator;
    }
}
