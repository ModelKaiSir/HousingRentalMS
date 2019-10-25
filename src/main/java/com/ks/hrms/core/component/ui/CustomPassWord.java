package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CustomPassWord extends AbstractCustomParent<String> implements AbstractCustomParent.InitValue<String> {

    private Label lb;
    private JFXPasswordField input;

    private SimpleObjectProperty<HBox> contentProperty = new SimpleObjectProperty<>();
    private RequiredFieldValidator validator;
    private SimpleObjectProperty<String> initValueProperty = new SimpleObjectProperty<String>();
    private SimpleObjectProperty<String> valueProperty = new SimpleObjectProperty<String>();

    public CustomPassWord(String id,String caption,String initValue) {
        super.setFieldId(id);
        super.setCaption(caption);
        initValueProperty.set(initValue);
        init();
    }

    public CustomPassWord(String id, String caption) {
        this(id,caption,null);
    }

    @Override
    public void init(){
        lb = CustomComponentFactory.generateCaptionLabel();
        input = CustomComponentFactory.generatePasswordField();

        lb.textProperty().bind(captionProperty());
        input.prefWidthProperty().bind(widthProperty());
        input.prefHeightProperty().bind(heightProperty());

        input.textProperty().addListener((obs,old,nv) -> {
            valueProperty.set(nv);
        });

        valueProperty.addListener((obs,old,nv) -> {
            input.setText(nv);
        });

        HBox root = CustomComponentFactory.generateHBox(lb,input);
        bindProperty(input.disableProperty(),input.editableProperty());
        validator = new RequiredFieldValidator();
        validator.setMessage("不能为空！");
        contentProperty.set(root);
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
    public Parent content() {
        return contentProperty.get();
    }

    @Override
    public ObjectProperty<? extends Parent> contentProperty() {
        return contentProperty;
    }

    @Override
    public void afterInit() {
        if(isRequired()){
            input.setValidators(validator);
        }
    }

    @Override
    public String getValue() {
        return valueProperty.get();
    }

    @Override
    public ObjectProperty valueProperty() {
        return valueProperty;
    }

    @Override
    public void setValue(String value) {
        valueProperty.set(value);
    }

    @Override
    public void setWidth(double width) {
        if(width <= 0){
            width = CustomComponentFactory.COMBO_BOX_MIN_WIDTH;
        }
        super.setWidth(width);
    }
}
