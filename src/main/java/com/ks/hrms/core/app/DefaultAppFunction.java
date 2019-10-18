package com.ks.hrms.core.app;

import javafx.util.Callback;

public class DefaultAppFunction extends AbstractAppFunction implements FunctionComponent.FunctionRequestListener {

    @Override
    protected FunctionMain createFunctionMain() {
        return null;
    }

    @Override
    protected FunctionNavigator createFunctionNavigator() {
        return null;
    }

    @Override
    protected FunctionLayout createFunctionLayout() {
        return new DefaultAppFunctionLayout();
    }

    @Override
    public void onClose(Callback call) {
        System.out.println("被关闭了");
    }

    @Override
    public void onOpen(Callback call) {
        System.out.println("被打开了");
    }

    @Override
    public void request(FunctionComponent component, ButtonType type) {

        switch (type){
            case NEW:
                break;
        }
    }
}
