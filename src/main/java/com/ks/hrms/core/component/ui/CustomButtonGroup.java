package com.ks.hrms.core.component.ui;

import com.ks.hrms.core.component.FormField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class CustomButtonGroup extends AbstractCustomParent {

    private ArrayList<Button> buttons;
    private SimpleObjectProperty<HBox> rootProperty = new SimpleObjectProperty<>();

    public CustomButtonGroup(String fieldId) {
        super(fieldId,null);
    }


    public void addButtons(List<FormField.FormFieldAttribute> values){

        for (FormField.FormFieldAttribute v:values){
            Button button = CustomComponentFactory.generateButton(v.getKey());
            button.setId(v.getValue());
            buttons.add(button);
            rootProperty.get().getChildren().add(button);
        }
    }

    @Override
    public void init() {
        HBox root = CustomComponentFactory.generateHBox();
        root.alignmentProperty().bind(posProperty());
        rootProperty.set(root);
        buttons = new ArrayList<>();
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
}
