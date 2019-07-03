package com.ks.hrms.core.app;

import javafx.scene.layout.AnchorPane;

public class AppFunctionNavigator extends AnchorPane implements App,AppPage {

    private AppFunction function;
    private AppPage children;
    private int index;

    @Override
    public void setAppManager(AppFunction appFunction) {
        function = appFunction;
    }

    @Override
    public AppPage getNext() {

        if(hasNext()){
            return children;
        }
        return null;
    }

    @Override
    public AppPage getLast() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return null != children;
    }

    @Override
    public boolean hasLast() {
        return false;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setPageIndex(int pageIndex) {
        index = function.registerPage(this);
    }

    private void close(){
        function.closePage(this);
    }
}
