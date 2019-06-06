package com.ks.hrms.core.component;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class CustomButtonGroup extends AbstractField {

    private ArrayList<Button> buttons;
    private HBox root;

    public CustomButtonGroup(String fieldId) {
        super(fieldId);
    }

    @Override
    public Parent getComponent() {
        return root;
    }

    @Override
    public void generateContent() {
        root = CustomComponentFactory.generateHBox();
        buttons = new ArrayList<>();
    }

    @Override
    public void setPos(Pos pos) {
        super.setPos(pos);
        root.setAlignment(pos);
    }

    public void addButtons(List<FormField.FormFieldAttribute> values){

        for (FormField.FormFieldAttribute v:values){
            Button button = CustomComponentFactory.generateButton(v.getKey());
            button.setId(v.getValue());
            buttons.add(button);
            root.getChildren().add(button);
        }
    }
}
