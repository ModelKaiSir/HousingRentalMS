package com.ks.hrms.core.app;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public abstract class AbstractAppFunctionLayout extends AnchorPane implements FunctionComponent, FunctionLayout {

    private SimpleObjectProperty<FunctionComponent> focusedComponent = new SimpleObjectProperty<>();

    public AbstractAppFunctionLayout() {

    }

    @Override
    public void init() {
    }

    @Override
    public void setFocusedFunctionComponent(FunctionComponent c) {
        getChildren().clear();
        focusedComponent.set(c);
        getChildren().add((Parent) c);
        fitToParent((Parent) c);
    }

    @Override
    public FunctionComponent getFocusedFunctionComponent() {
        return focusedComponent.get();
    }

    @Override
    public void close() {

    }

    @Override
    public void onClose(Callback call) {

    }

    @Override
    public void onOpen(Callback call) {

    }

    protected void fitToParent(Parent parent) {
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }
}
