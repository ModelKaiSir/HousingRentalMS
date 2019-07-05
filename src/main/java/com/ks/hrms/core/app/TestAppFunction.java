package com.ks.hrms.core.app;

import com.ks.hrms.core.context.HRMSAppFunctionContext;

public class TestAppFunction extends AppFunction {

    @Override
    public void init(HRMSAppFunctionContext context) {
        super.init(context);
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
        TestAppFunctionNavigator navigator = new TestAppFunctionNavigator();
        navigator.setAppManager(this);
        return navigator;
    }
}
