package com.ks.hrms.core.app;

public class AppFunction extends AppFunctionBase {

    /**
     * 可以为空
     * @return
     */
    @Override
    public AppFunctionNavigator createNavigator() {
        return null;
    }

    /**
     * 不能为空
     * @return
     */
    @Override
    public AppFunctionMain createMain() {
        return null;
    }
}
