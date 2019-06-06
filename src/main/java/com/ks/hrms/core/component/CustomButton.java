package com.ks.hrms.core.component;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;

public class CustomButton extends AbstractField {

    private JFXButton button;

    public CustomButton(String fieldId,String caption) {
        super(fieldId,caption);
    }

    private CustomButton(String fieldId, Object defaultValue) {
        super(fieldId, defaultValue);
    }

    @Override
    public void generateContent() {
       button = CustomComponentFactory.generateButton(null);
    }

    @Override
    public void setCaption(String caption) {
        super.setCaption(caption);
        button.setText(caption);
    }

    @Override
    public Parent getComponent() {
        return button;
    }
}

