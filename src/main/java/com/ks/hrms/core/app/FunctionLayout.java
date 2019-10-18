package com.ks.hrms.core.app;

/**
 * 负责管理FunctionComponent
 */
public interface FunctionLayout extends FunctionComponent{

    FunctionComponent getFocusedFunctionComponent();

    void setFocusedFunctionComponent(FunctionComponent component);
}
