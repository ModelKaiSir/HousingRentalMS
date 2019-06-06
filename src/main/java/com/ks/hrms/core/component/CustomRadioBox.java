package com.ks.hrms.core.component;

import com.ks.hrms.core.component.AbstractField;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class CustomRadioBox extends AbstractField {

    final ToggleGroup group = new ToggleGroup();
    private ArrayList<RadioButton> radioButtons;
    private Label lb;
    private HBox root;

    public CustomRadioBox(String fieldId) {
        super(fieldId);
    }

    public CustomRadioBox(String fieldId, String caption) {
        super(fieldId,caption);
    }

    public CustomRadioBox(String fieldId, String caption,Object defaultValue) {
        super(fieldId,caption,defaultValue);
    }

    @Override
    public Parent getComponent() {
        return root;
    }

    @Override
    public void generateContent() {
        lb = CustomComponentFactory.generateCaptionLb(getCaption());
        root = CustomComponentFactory.generateHBox(lb);
        radioButtons = new ArrayList<>();
    }

    @Override
    public void setCaption(String caption) {
        super.setCaption(caption);
        lb.setText(caption);
    }

    public void addSelect(List<FormField.FormFieldAttribute> itemList){

        for (FormField.FormFieldAttribute item:itemList){
            RadioButton r =CustomComponentFactory.generateRadioBox(item.getKey(),item.getValue(),getDefaultValue(),group);
            radioButtons.add(r);
            root.getChildren().add(r);

        }
    }
}
