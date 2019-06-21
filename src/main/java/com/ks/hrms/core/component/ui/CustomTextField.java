package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CustomTextField extends AbstractCustomParent implements AbstractCustomParent.InitValue<String> {

    private Label lb;
    private JFXTextField input;

    private SimpleObjectProperty<HBox> rootProperty = new SimpleObjectProperty<>();
    private RequiredFieldValidator validator;
    private SimpleObjectProperty<String> initValueProperty = new SimpleObjectProperty<String>();

    public CustomTextField(String id,String caption,String initValue) {
        super.setFieldId(id);
        super.setCaption(caption);
        initValueProperty.set(initValue);
        init();
    }

    public CustomTextField(String id, String caption) {
        this(id,caption,null);
    }

    @Override
    public void init(){
        lb = CustomComponentFactory.generateCaptionLb();
        lb.textProperty().bind(captionProperty());
        input = CustomComponentFactory.generateTextField();
        input.editableProperty().bind(editableProperty());
        HBox root = CustomComponentFactory.generateHBox(lb,input);

        validator = new RequiredFieldValidator();
        validator.setMessage("不能为空！");
        rootProperty.set(root);
    }

    @Override
    public void setEditable(boolean isReadOnly) {
        super.setEditable(!isReadOnly);
    }

    @Override
    public void setInitValue(String initValue) {
        initValueProperty.set(initValue);
        input.setText(initValue);
    }

    @Override
    public ObjectProperty<String> initValueProperty() {
        return initValueProperty;
    }

    @Override
    public Parent value() {
        return rootProperty.get();
    }

    @Override
    public ObjectProperty<? extends Parent> valueProperty() {
        return rootProperty;
    }

    @Override
    public void afterInit() {
        if(isRequired()){
            input.setValidators(validator);
        }
    }
}
