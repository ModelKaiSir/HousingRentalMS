package com.ks.hrms.core.app;

import com.ks.hrms.core.context.HRMSAppFunctionContext;

public interface App {

    /**
     * Manager
     * @param appFunction
     */
    void setAppManager(AppFunctionBase appFunction);

    /**
     * 程序初始化
     * @param context
     */
    void init(HRMSAppFunctionContext context);
}
