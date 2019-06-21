package com.ks.hrms.core.component.ui;

import com.ks.hrms.core.component.FormField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class CustomCheckBox extends AbstractCustomParent implements AbstractCustomParent.InitValue<String> {

    private ArrayList<CheckBox> checkBoxes;
    private Label lb;
    private SimpleObjectProperty<HBox> rootProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<String> initValueProperty = new SimpleObjectProperty<>();

    public CustomCheckBox(String fieldId, String caption) {
        this(fieldId, caption,null);
    }

    public CustomCheckBox(String id, String caption, String defaultValue) {
        super.setFieldId(id);
        super.setCaption(caption);
        setInitValue(defaultValue);
    }

    public void addSelect(List<FormField.FormFieldAttribute> values) {
        for (FormField.FormFieldAttribute item : values) {
            CheckBox box = CustomComponentFactory.generateCheckBox(item.getKey(),item.getValue(),initValueProperty.getValue());
            rootProperty.get().getChildren().add(box);
            checkBoxes.add(box);
        }
    }

    @Override
    public void init() {
        lb = CustomComponentFactory.generateCaptionLb();
        lb.textProperty().bind(captionProperty());
        HBox root = CustomComponentFactory.generateHBox(lb);
        rootProperty.set(root);
        checkBoxes = new ArrayList<>();
        initValueProperty.addListener((observableValue,oldValue,newValue) ->{
            checkBoxes.forEach(i ->{
                if(i.getUserData().equals(newValue)){
                    i.setSelected(true);
                    i.requestFocus();
                }
            });
        });
    }

    @Override
    public void afterInit() {

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
    public void setInitValue(String initValue) {
        initValueProperty.set(initValue);
    }

    @Override
    public ObjectProperty<String> initValueProperty() {
        return initValueProperty;
    }
}
