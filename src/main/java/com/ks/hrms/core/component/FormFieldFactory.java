package com.ks.hrms.core.component;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class FormFieldFactory {

    private  ArrayList<FormField> formFields;

    public void addFormFields(ArrayList<FormField> formFields){
        this.formFields = formFields;
        for (FormField f: this.formFields){
            f.parseAttributes(this);
        }
    }

    /**
     * 根据类型生成对应的组件
     * @return
     */
    public Parent generateComponent(FormField f,String attribute){
        switch (attribute){
            case FormField.TYPE_TEXTFIELD:
                return generateTextField(f);
            case FormField.TYPE_BUTTON:
                return generateButton(f);
            default:
                return null;
        }
    }

    private static Parent generateTextField(FormField f){
        HBox root = new HBox();
        Label caption = new Label(f.getCaption());
        caption.setMinWidth(60);
        caption.setMaxWidth(100);
        String defValue = "";
        if(null!=f.getDefaultValue()){
            defValue = f.getDefaultValue().toString();
        }

        TextField input = new TextField(defValue);
        if(f.getWidth() != 0){
            input.setPrefWidth(f.getWidth());
            System.out.println(f.getWidth());
        }
        root.getChildren().addAll(caption,input);
        root.setSpacing(5);
        f.setComponent(input);
        return root;
    }

    private static Parent generateButton(FormField f){
        Button button = new Button(f.getCaption());
        return button;
    }

    public ArrayList<FormField> getFormFields() {
        return formFields;
    }
}
