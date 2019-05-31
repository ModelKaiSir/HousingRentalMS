package com.ks.hrms.controller;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FreeForm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyController implements Initializable {

    @FXML
    TabPane body;

    private String caption;
    private FreeForm freeForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        caption = "FreFor";
        body.getTabs().clear();
        freeForm = FreeForm.createFreeForm(caption,getFormFields());
        body.getTabs().add(freeForm);
    }

    private ArrayList<FormField> getFormFields(){
        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("NAME","用户名：", FormField.TYPE_TEXTFIELD + FormField.putWidth("300")));
        formFields.add(new FormField("PASSWORD","密码：",FormField.TYPE_TEXTFIELD+FormField.ATTRIBUTE_VALUE_BREAK));
        formFields.add(new FormField("BUTTON_OK","test",FormField.TYPE_BUTTON));
        formFields.add(new FormField("BUTTON_OK","确定",FormField.TYPE_BUTTON+ FormField.ATTRIBUTE_VALUE_BREAK));
        formFields.add(new FormField("BUTTON_OK","取消",FormField.TYPE_BUTTON));
        return formFields;
    }
}
