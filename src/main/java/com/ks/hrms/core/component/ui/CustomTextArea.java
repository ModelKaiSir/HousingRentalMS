package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CustomTextArea extends AbstractCustomParent implements AbstractCustomParent.InitValue<String> {

    private JFXTextArea area;
    private SimpleObjectProperty<HBox> contentProperty = new SimpleObjectProperty<>();
    private ObjectProperty value = new SimpleObjectProperty();
    private ObjectProperty initValue = new SimpleObjectProperty();

    public CustomTextArea(String id, String caption) {
        super(id, caption);
    }

    @Override
    public void init() {
        contentProperty.set(CustomComponentFactory.generateHBox());
        Label lb = CustomComponentFactory.generateCaptionLb();
        area = CustomComponentFactory.generateTextAreaField();
        contentProperty.get().getChildren().addAll(lb,area);

        lb.textProperty().bind(captionProperty());
        area.prefHeightProperty().bind(heightProperty());
        area.prefWidthProperty().bind(widthProperty());
        area.textProperty().addListener((observable,old,nv) ->{
            value.set(nv);
        });

        value.addListener((observable,old,nv) ->{
            area.setText(nv.toString());
        });
    }

    @Override
    public void afterInit() {

    }

    @Override
    public Parent content() {
        return contentProperty.get();
    }

    @Override
    public ObjectProperty<? extends Parent> contentProperty() {
        return contentProperty;
    }

    @Override
    public Object getValue() {
        return value.get();
    }

    @Override
    public ObjectProperty valueProperty() {
        return value;
    }

    @Override
    public void setInitValue(String initValue) {
        this.initValue.set(initValue);
        area.setText(initValue);
    }

    @Override
    public ObjectProperty<String> initValueProperty() {
        return initValue;
    }

    @Override
    public void setHeight(double height) {
        if(height <= 0){
            height = CustomComponentFactory.TEXTAREA_MIN_HEIGHT;
        }
        super.setHeight(height);
    }

    @Override
    public void setWidth(double width) {
        if(width <= 0){
            width = CustomComponentFactory.TEXTAREA_MIN_WIDTH;
        }
        super.setWidth(width);
    }
}
