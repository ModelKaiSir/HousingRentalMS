package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class CustomButton extends AbstractCustomParent<String> {

    private Button button;
    private SimpleObjectProperty<Button> contentProperty = new SimpleObjectProperty<>();

    public CustomButton(String fieldId,String caption) {
        super(fieldId,caption);
    }

    public CustomButton(String fieldId) {
        super(fieldId,null);
    }

    @Override
    public void init() {

        button = CustomComponentFactory.generateButton(getCaption());
        contentProperty.set(button);

        button.prefHeightProperty().bind(heightProperty());
        button.prefWidthProperty().bind(widthProperty());
        button.textProperty().bind(captionProperty());
        button.disableProperty().bind(disableProperty());
        button.alignmentProperty().bind(posProperty());
    }

    @Override
    public Parent content() {
        return contentProperty.get();
    }

    @Override
    public ObjectProperty<Button> contentProperty() {
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

    @Override
    public void setValue(String value) {

    }
}

