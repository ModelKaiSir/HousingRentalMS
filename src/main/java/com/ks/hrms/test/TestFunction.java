package com.ks.hrms.test;

import com.ks.hrms.core.app.DefaultAppFunction;
import com.ks.hrms.core.app.FunctionMain;

public class TestFunction extends DefaultAppFunction {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public FunctionMain createMain() {
        FunctionMain main = new TestFunctionMain();
        return main;
    }
}
