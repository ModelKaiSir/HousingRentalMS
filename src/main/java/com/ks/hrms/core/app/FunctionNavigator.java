package com.ks.hrms.core.app;

public interface FunctionNavigator extends FunctionComponent{

    void setFunction(Function function);

    void setFunctionRequestListener(FunctionRequestListener listener);
}
