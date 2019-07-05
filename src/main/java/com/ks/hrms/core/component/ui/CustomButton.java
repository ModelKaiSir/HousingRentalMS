package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;

public class CustomButton extends AbstractCustomParent<String> {

    private SimpleObjectProperty<JFXButton> contentProperty = new SimpleObjectProperty<>();

    public CustomButton(String fieldId,String caption) {
        super(fieldId,caption);
    }

    public CustomButton(String fieldId) {
        super(fieldId,null);
    }

    @Override
    public void init() {
        contentProperty.set(CustomComponentFactory.generateButton(""));
        contentProperty.get().prefHeightProperty().bind(heightProperty());
        contentProperty.get().prefWidthProperty().bind(widthProperty());
        contentProperty.get().textProperty().bind(captionProperty());
        contentProperty.get().disableProperty().bind(disableProperty());
        contentProperty.get().alignmentProperty().bind(posProperty());
    }

    @Override
    public Parent content() {
        return contentProperty.get();
    }

    @Override
    public ObjectProperty<JFXButton> contentProperty() {
        return contentProperty;
    }

    @Override
    public void afterInit() {

    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public ObjectProperty<String> valueProperty() {
        return null;
    }
}

