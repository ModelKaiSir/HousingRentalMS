package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

public class CustomButton extends AbstractCustomParent {

    private SimpleObjectProperty<JFXButton> valueProperty = new SimpleObjectProperty<>();

    public CustomButton(String fieldId,String caption) {
        super(fieldId,caption);
    }

    public CustomButton(String fieldId) {
        super(fieldId,null);
    }

    @Override
    public void init() {
        valueProperty.set(CustomComponentFactory.generateButton(""));
        valueProperty.get().textProperty().bind(captionProperty());
        valueProperty.get().disableProperty().bind(editableProperty());
    }

    @Override
    public Parent value() {
        return valueProperty.get();
    }

    @Override
    public ObjectProperty<JFXButton> valueProperty() {
        return valueProperty;
    }

    @Override
    public void afterInit() {

    }
}

