package com.ks.hrms.controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.component.form.TableForm;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Demo extends Application {

    TableForm tableForm;
    //JFXTreeTableView<User> tableForm;

    @Override
    public void start(Stage primaryStage) throws Exception {

        tableForm = TableForm.createTableForm(Pos.CENTER,getFormFields());
        tableForm.setData(FXCollections.observableArrayList(tableForm.newItem(),tableForm.newItem())).init();
        /*TreeItem<User> root = new RecursiveTreeItem<>(FXCollections.observableArrayList(new User("A")), RecursiveTreeObject::getChildren);
        tableForm = new JFXTreeTableView<>(root);
        JFXTreeTableColumn<User, String> col = new JFXTreeTableColumn<>("GGG");
        col.setCellValueFactory(param -> {
            if (col.validateValue(param)) {
                return param.getValue().getValue().name;
            } else {
                return col.getComputedValue(param);
            }
        });

        tableForm.getColumns().add(col);
        tableForm.setShowRoot(false);*/
        primaryStage.setScene(new Scene(tableForm));
        primaryStage.setTitle("test");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ArrayList<FormField> getFormFields() {
        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("NAME", "用户名：", FormField.TEXTFIELD + FormField.putDefValue("Test")));
        formFields.add(new FormField("XF_CONTENT", "备注", FormField.TEXTAREA+ FormField.putComponentAlignment(Pos.CENTER)));
        return formFields;
    }

    class User extends RecursiveTreeObject<User> {
        private SimpleStringProperty name = new SimpleStringProperty();

        public User(String name) {
            this.name.set(name);
        }
    }
}
