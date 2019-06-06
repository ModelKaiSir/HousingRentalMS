package com.ks.hrms.core.component;

import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;

public class CustomCheckBox extends AbstractField {

    private ArrayList<CheckBox> checkBoxes;
    private Label lb;
    private HBox root;

    public CustomCheckBox(String fieldId) {
        super(fieldId);
    }

    public CustomCheckBox(String fieldId, String caption) {
        super(fieldId, caption);
    }

    public CustomCheckBox(String fieldId, Object defaultValue) {
        super(fieldId, defaultValue);
    }

    public CustomCheckBox(String fieldId, String caption, Object defaultValue) {
        super(fieldId, caption, defaultValue);
    }

    @Override
    public Parent getComponent() {
        return root;
    }

    @Override
    public void setCaption(String caption) {
        super.setCaption(caption);
        lb.setText(caption);
    }

    @Override
    public void generateContent() {
        lb = CustomComponentFactory.generateCaptionLb(getCaption());
        root = CustomComponentFactory.generateHBox(lb);
        checkBoxes = new ArrayList<>();
    }

    public void addSelect(List<FormField.FormFieldAttribute> values) {
        for (FormField.FormFieldAttribute item : values) {
            CheckBox box = CustomComponentFactory.generateCheckBox(item.getKey(),item.getValue(),getDefaultValue());
            root.getChildren().add(box);
            checkBoxes.add(box);
        }
    }
}
