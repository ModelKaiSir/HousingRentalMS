package com.ks.hrms.core.app;

import com.ks.hrms.core.context.AppFunctionContext;

public interface Function<T> extends FunctionComponent {

    FunctionMain getFunctionMain();

    FunctionNavigator getNavigator();
}
