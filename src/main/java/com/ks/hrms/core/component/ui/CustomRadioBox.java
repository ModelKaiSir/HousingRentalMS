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

public class CustomRadioBox extends AbstractCustomParent<String> implements AbstractCustomParent.InitValue<String>{

    final ToggleGroup group = new ToggleGroup();

    private ArrayList<RadioButton> radioButtonList;
    private Label captionLabel;

    private SimpleObjectProperty<HBox> rootProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<String> initValueProperty = new SimpleObjectProperty<>();

    private SimpleObjectProperty<String> valueProperty = new SimpleObjectProperty<>();

    public CustomRadioBox(String fieldId, String caption) {
        super(fieldId,caption);
    }

    public CustomRadioBox(String fieldId, String caption,String defaultValue) {
        super(fieldId,caption);
        valueProperty.set(defaultValue);
    }


    @Override
    public void setCaption(String caption) {
        super.setCaption(caption);
        captionLabel.setText(caption);
    }

    public void addSelect(List<FormField.FormFieldAttribute> itemList){

        for (FormField.FormFieldAttribute item:itemList){
            RadioButton r = CustomComponentFactory.generateRadioBox(item.getKey(),item.getValue(),group);
            r.disableProperty().bind(disableProperty());
            radioButtonList.add(r);
            rootProperty.get().getChildren().add(r);
        }
    }

    @Override
    public void init() {

        captionLabel = CustomComponentFactory.generateCaptionLabel();
        captionLabel.textProperty().bind(captionProperty());
        HBox root = CustomComponentFactory.generateHBox(captionLabel);

        rootProperty.set(root);
        radioButtonList = new ArrayList<>();

        initValueProperty.addListener((observable,old,newValue) ->{
            radioButtonList.forEach(i ->{
                if(i.getUserData().equals(newValue)){
                    i.setSelected(true);
                    i.requestFocus();
                }
            });
        });

        group.selectedToggleProperty().addListener((obs, oldValue, newValue) ->{
            RadioButton r = (RadioButton) newValue;
            valueProperty.set((String) r.getUserData());
        });

        valueProperty.addListener((obs, oldValue, newValue) ->{
            group.getToggles().stream().forEach(t ->{

                RadioButton r = (RadioButton) t;

                if(!r.isSelected() && r.getUserData().equals(newValue)){
                    r.setSelected(true);
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
}
