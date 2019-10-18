package com.ks.hrms.core.app;

import com.ks.hrms.core.context.AppFunctionContext;

public interface Function extends FunctionComponent {

    FunctionMain getFunctionMain();

    FunctionNavigator getNavigator();
}
