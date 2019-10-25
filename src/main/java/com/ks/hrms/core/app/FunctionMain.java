package com.ks.hrms.core.app;

public interface FunctionMain<T> extends FunctionComponent {

    void setFunction(Function function);

    void setFunctionRequestListener(FunctionRequestListener listener);

    void newItem();

    void setItem(Item<T> item);

    void setFunctionMode(FunctionMode mode);

    FunctionMode getFunctionMode();

    boolean update();

}
