package com.ks.hrms.controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.ks.hrms.core.component.CustomStyleClass;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FreeForm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyController implements Initializable {

    @FXML
    JFXTabPane body;
    @FXML
    JFXListView<String> menu;

    @FXML
    Button test;

    private String caption;
    private FreeForm freeForm;

    private Resource customResource = new ClassPathResource(CustomStyleClass.CUSTOM_UI_CSS);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            caption = "FreFor";
            body.getStylesheets().add(customResource.getURL().toExternalForm());
            body.getTabs().clear();
            body.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

            freeForm = FreeForm.createFreeForm(caption, Pos.CENTER, getFormFields());
            body.getTabs().add(freeForm);

            test.setOnAction(e ->{
                body.getTabs().add(FreeForm.createFreeForm(caption, Pos.CENTER, getFormFields()));
            });

            menu.getItems().add("test1");
            menu.getItems().add("test2");
            menu.getItems().add("test3");
            menu.getItems().add("test4");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<FormField> getFormFields() {
        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("NAME", "用户名：", FormField.ATTRIBUTE_TYPE_TEXTFIELD + FormField.putDefValue("Test")));
        formFields.add(new FormField("PASSWORD", "密码：", FormField.ATTRIBUTE_TYPE_TEXTFIELD +FormField.ATTRIBUTE_VALUE_READONLY+ FormField.putDefValue("Test")+ FormField.ATTRIBUTE_VALUE_BREAK));
        formFields.add(new FormField("BUTTON_OK", "test", FormField.ATTRIBUTE_TYPE_BUTTON));

        String itemList_sex = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("男", "0"),
                new FormField.FormFieldAttribute("女", "1")
        });

        formFields.add(new FormField("SEX", "性别", FormField.ATTRIBUTE_TYPE_GROUP_RADIO + itemList_sex +FormField.putDefValue("1")+ FormField.putComponentAlignment(Pos.CENTER)));

        String itemList_week = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("星期一", "0"),
                new FormField.FormFieldAttribute("星期二", "1"),
                new FormField.FormFieldAttribute("星期三", "2"),
                new FormField.FormFieldAttribute("星期四", "4"),
                new FormField.FormFieldAttribute("星期五", "5"),
                new FormField.FormFieldAttribute("星期六", "6")
        });

        formFields.add(new FormField("week", "星期", FormField.ATTRIBUTE_TYPE_GROUP_CHECKBOX + itemList_week +FormField.putDefValue("1")+ FormField.putComponentAlignment(Pos.CENTER)));

        String itemList_buttons = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("确定", "CONFIRM"),
                new FormField.FormFieldAttribute("取消", "CANCEL")
        });

        formFields.add(new FormField("BUTTON_OK2", "", FormField.ATTRIBUTE_TYPE_GROUP_BUTTON + itemList_buttons + FormField.putComponentAlignment(Pos.CENTER)));

        return formFields;
    }
}
