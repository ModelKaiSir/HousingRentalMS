package com.ks.hrms.test;

import com.ks.hrms.core.app.DefaultAppFunction;
import com.ks.hrms.core.app.FunctionMain;
import com.ks.hrms.core.app.FunctionNavigator;

public class TestFunction extends DefaultAppFunction {

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected FunctionMain createFunctionMain() {
        FunctionMain main = new TestFunctionMain();
        return main;
    }

    @Override
    protected FunctionNavigator createFunctionNavigator() {
        FunctionNavigator navigator = new TestFunctionNavigator();
        return navigator;
    }
}
