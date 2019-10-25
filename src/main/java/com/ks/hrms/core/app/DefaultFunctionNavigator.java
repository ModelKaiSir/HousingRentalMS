package com.ks.hrms.core.app;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DefaultFunctionNavigator<S> extends AbstractFunctionNavigator<S> {

    private FunctionRequestListener requestListener;

    @Override
    public ToolBar createToolBar() {
        SearchToolBar toolBar = new SearchToolBar();
        toolBar.setToolbarTypes(getButtonTypes());
        toolBar.init();
        return toolBar;
    }

    @Override
    public ArrayList<FormField> getFormFields() {
        return null;
    }

    @Override
    public ObservableList<S> loadData() {
        return null;
    }

    @Override
    public ButtonType[] getButtonTypes() {
        return new ButtonType[]{ ButtonType.EXIT, ButtonType.NEW, ButtonType.VIEW, ButtonType.MODIFY };
    }

    @Override
    public void onClickButton(ButtonType type) {
        requestListener.request(this, type);
    }

    @Override
    public void setFunctionRequestListener(FunctionRequestListener listener) {
        requestListener = listener;
    }

    @Override
    public Item getSelectItem() {
        return null;
    }
}
