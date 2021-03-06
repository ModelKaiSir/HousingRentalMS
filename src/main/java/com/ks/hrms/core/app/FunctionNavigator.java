package com.ks.hrms.core.app;

public interface FunctionNavigator<T> extends FunctionComponent{

    void setFunction(Function function);

    void setFunctionRequestListener(FunctionRequestListener listener);

    Item<T> getSelectItem();
}
