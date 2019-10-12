package com.ks.hrms.core.app;

public class DefaultAppFunction extends AbstractAppFunction {

    @Override
    FunctionToolbar createToolbar() {
        return new DefaultAppFunctionToolbar();
    }

    @Override
    public FunctionMain createMain() {
        return null;
    }

    @Override
    public FunctionNavigator createNavigator() {
        return null;
    }

    @Override
    public FunctionLayout createLayout() {
        return new DefaultAppFunctionLayout();
    }
}
