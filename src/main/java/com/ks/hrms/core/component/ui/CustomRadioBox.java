package com.ks.hrms.core.component.ui;

import com.ks.hrms.core.component.FormField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class CustomRadioBox extends AbstractCustomParent implements AbstractCustomParent.InitValue<String>{

    final ToggleGroup group = new ToggleGroup();
    private ArrayList<RadioButton> radioButtons;
    private Label lb;
    private SimpleObjectProperty<HBox> rootProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<String> initValueProperty = new SimpleObjectProperty<>();

    public CustomRadioBox(String fieldId, String caption) {
        super(fieldId,caption);
    }

    public CustomRadioBox(String fieldId, String caption,String defaultValue) {
        super(fieldId,caption);
    }


    @Override
    public void setCaption(String caption) {
        super.setCaption(caption);
        lb.setText(caption);
    }

    public void addSelect(List<FormField.FormFieldAttribute> itemList){

        for (FormField.FormFieldAttribute item:itemList){
            RadioButton r =CustomComponentFactory.generateRadioBox(item.getKey(),item.getValue(),group);
            radioButtons.add(r);
            rootProperty.get().getChildren().add(r);
        }
    }

    @Override
    public void init() {
        lb = CustomComponentFactory.generateCaptionLb();
        lb.textProperty().bind(captionProperty());
        HBox root = CustomComponentFactory.generateHBox(lb);

        rootProperty.set(root);
        radioButtons = new ArrayList<>();
        initValueProperty.addListener((observable,old,newValue) ->{
            radioButtons.forEach(i ->{
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
    public Parent content() {
        return rootProperty.get();
    }

    @Override
    public ObjectProperty<? extends Parent> contentProperty() {
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

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public ObjectProperty valueProperty() {
        return null;
    }
}
