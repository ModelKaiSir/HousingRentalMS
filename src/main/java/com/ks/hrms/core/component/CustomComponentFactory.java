package com.ks.hrms.core.component;

import com.jfoenix.controls.*;
import com.jfoenix.converters.base.NodeConverter;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class CustomComponentFactory {

    public static final double DEF_SPACE = 10;

    public static final double LB_MIN_WIDTH = 60;
    public static final double LB_MAX_WIDTH = 100;

    public static Label generateCaptionLb(String caption){
        Label lb = new Label();
        lb.setMinWidth(LB_MIN_WIDTH);
        lb.setMaxWidth(LB_MAX_WIDTH);
        lb.setText(caption);
        lb.setAlignment(Pos.CENTER);
        return lb;
    }

    public static HBox generateHBox(Node...childes){
        HBox root = new HBox();
        root.getChildren().addAll(childes);
        root.setSpacing(DEF_SPACE);
        return root;
    }

    public static JFXButton generateButton(String text){
        JFXButton button = new JFXButton(text);
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.getStyleClass().add(CustomStyleClass.CUSTOM_BTN_COLOR_PRIMARY);
        return button;
    }

    public static JFXTextField generateTextField(Object text){
        JFXTextField textField = new JFXTextField(null!=text?text.toString():null);
        return textField;
    }

    public static JFXCheckBox generateCheckBox(String key,String value,Object defValue){
        JFXCheckBox box = generateCheckBox(key,value);
        if(null!=defValue){
            box.setSelected(value.equals(defValue));
            box.requestFocus();
        }
        return box;
    }

    public static JFXCheckBox generateCheckBox(String key,String value){
        JFXCheckBox box = new JFXCheckBox(key);
        box.setUserData(value);
        return box;
    }

    public static JFXRadioButton generateRadioBox(String key, String value,Object defValue, ToggleGroup group){
        JFXRadioButton box = generateRadioBox(key,value,group);
        if(null!=defValue){
            box.setSelected(value.equals(defValue));
            box.requestFocus();
        }
        return box;
    }

    public static JFXRadioButton generateRadioBox(String key, String value, ToggleGroup group){
        JFXRadioButton box = new JFXRadioButton(key);
        box.setUserData(value);
        if(null!=group){
            box.setToggleGroup(group);
        }
        return box;
    }

    public static JFXComboBox generateComBox(){
        JFXComboBox box = new JFXComboBox();
        return box;
    }
}
