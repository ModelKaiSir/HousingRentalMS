package com.ks.hrms.core.app;

import javafx.util.Callback;

public class DefaultAppFunction<T> extends AbstractAppFunction<T> implements FunctionComponent.FunctionRequestListener {

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

        FunctionMain main = getFunctionMain();
        FunctionNavigator navigator = getNavigator();

        if (component instanceof FunctionNavigator) {

            switch (type) {

                case NEW:
                    getLayout().setFocusedFunctionComponent(main);
                    main.setFunctionMode(FunctionMode.NEW);
                    main.newItem();
                    break;
                case VIEW:
                    getLayout().setFocusedFunctionComponent(main);
                    main.setFunctionMode(FunctionMode.VIEW);
                    main.setItem(navigator.getSelectItem());
                    break;
                case MODIFY:
                    getLayout().setFocusedFunctionComponent(main);
                    main.setFunctionMode(FunctionMode.MODIFY);
                    main.setItem(navigator.getSelectItem());
                    break;
            }
        } else if (component instanceof FunctionMain) {

            switch (type) {

                case EXIT:
                    main.update();
                    getLayout().setFocusedFunctionComponent(navigator);
                    break;
            }
        }
    }
}
