package com.ks.hrms.core.app;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public abstract class AbstractAppFunctionLayout extends BorderPane implements FunctionComponent, FunctionLayout {

    private FunctionToolbar functionToolbar;
    private SimpleObjectProperty<FunctionComponent> focusedComponent = new SimpleObjectProperty<>();

    public AbstractAppFunctionLayout() {

    }

    @Override
    public void init() {
        setCenter((TabPane) focusedComponent.get());
        setTop((HBox) functionToolbar);
    }

    @Override
    public void setFocusedFunctionComponent(FunctionComponent component) {
        focusedComponent.set(component);
    }

    @Override
    public FunctionComponent getFocusedFunctionComponent() {
        return focusedComponent.get();
    }

    @Override
    public void setFunctionToolbar(FunctionToolbar functionToolbar) {
        this.functionToolbar = functionToolbar;
    }

    @Override
    public void setCaption(String caption) {

    }
}
