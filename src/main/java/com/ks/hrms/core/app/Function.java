package com.ks.hrms.core.app;

import com.ks.hrms.core.context.AppFunctionContext;

public interface Function extends FunctionComponent {

    /**
     * 主界面
     *
     * @return
     */
    FunctionMain createMain();

    /**
     * 导航界面
     *
     * @return
     */
    FunctionNavigator createNavigator();

    /**
     * Layout
     *
     * @return
     */
    FunctionLayout createLayout();

    FunctionMain getMain();

    FunctionNavigator getNavigator();

}
