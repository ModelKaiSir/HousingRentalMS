package com.ks.hrms.core.component;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CustomTextField extends AbstractField{


    private Label lb;
    private JFXTextField input;
    private HBox root;
    private RequiredFieldValidator validator;

    public CustomTextField(String id,Object defValue,String caption) {
        super(id,defValue);
        setCaption(caption);
    }

    public CustomTextField(String id,String caption) {
        super(id);
        setCaption(caption);
    }

    @Override
    public void generateContent(){
        lb = CustomComponentFactory.generateCaptionLb(getCaption());
        input = CustomComponentFactory.generateTextField(getDefaultValue());
        root = CustomComponentFactory.generateHBox(lb,input);
        validator = new RequiredFieldValidator();
        validator.setMessage("不能为空！");
    }

    @Override
    public void setCaption(String caption) {
        super.setCaption(caption);
        lb.setText(caption);
    }

    @Override
    public void setWidth(double width) {
        if(width >0){
            super.setWidth(width);
            input.setPrefWidth(width);
        }
    }

    @Override
    public void setPos(Pos pos) {
        super.setPos(pos);
        root.setAlignment(pos);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        input.setEditable(readOnly);
    }

    @Override
    public void setRequired(boolean required) {
        super.setRequired(required);
        if(required){
            input.setValidators(validator);
        }else{
            input.getValidators().clear();
        }
    }

    @Override
    public void setDefaultValue(Object defaultValue) {
        super.setDefaultValue(defaultValue);
        if(null!=defaultValue){
            input.setText(defaultValue.toString());
        }

    }

    @Override
    public Parent getComponent() {
        return root;
    }
}
