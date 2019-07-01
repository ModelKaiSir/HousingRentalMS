package com.ks.hrms.core.component.form;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableForm extends AnchorPane {

    private FormFieldFactory fieldFactory;
    private Pos pos;

    private JFXTreeTableView<DataItem> tableView;

    private TableForm(Pos pos, ArrayList<FormField> formFields) {
        fieldFactory = new FormFieldFactory().addFormFields(formFields);
        this.pos = pos;
    }

    private TableForm(ArrayList<FormField> formFields) {
        this(null,formFields);
    }

    public TableForm init() {

        List<JFXTreeTableColumn<DataItem, Object>> cols = fieldFactory.getFormFields().stream().map(item -> {
            JFXTreeTableColumn<DataItem, Object> col = new JFXTreeTableColumn<>(item.getCaption());
            generateCellValueFactory(col, item.getId());
            generateCellFactory(col, item);
            return col;
        }).collect(Collectors.toList());

        tableView.getColumns().addAll(cols);
        this.getChildren().add(tableView);

        AnchorPane.setTopAnchor(tableView, 5.0);
        AnchorPane.setBottomAnchor(tableView, 1.0);
        AnchorPane.setLeftAnchor(tableView, 1.0);
        AnchorPane.setRightAnchor(tableView, 1.0);

        return this;
    }

    public TableForm setData(ObservableList<DataItem> data) {

        if(null != data){
            TreeItem<DataItem> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
            tableView = new JFXTreeTableView<>(root);
        }else{
            tableView = new JFXTreeTableView<>();
        }

        tableView.setShowRoot(false);
        tableView.setEditable(true);
        tableView.setColumnResizePolicy(JFXTreeTableView.CONSTRAINED_RESIZE_POLICY);

        return this;
    }

    public JFXTreeTableView<DataItem> getTable() {
        return tableView;
    }

    public DataItem newItem() {
        DataItem item = new DataItem(fieldFactory);
        return item;
    }

    private void generateCellValueFactory(JFXTreeTableColumn<DataItem, Object> col, final String id) {
        col.setCellValueFactory(param -> {
            if (col.validateValue(param)) {
                return param.getValue().getValue().getItemProperty(id).valueProperty();
            } else {
                return col.getComputedValue(param);
            }
        });

        col.setOnEditCommit(value ->{
            ObjectProperty v = col.getTreeTableView().getTreeItem(value.getTreeTablePosition().getRow()).getValue().getItemProperty(id).valueProperty();
            v.set(value.getNewValue());
        });
    }

    private void generateCellFactory(JFXTreeTableColumn<DataItem, Object> col, final FormField formField) {
        col.setCellFactory((TreeTableColumn<DataItem, Object> p) -> {
            return fieldFactory.generateFieldBuilder(formField);
        });
    }

    public static TableForm createTableForm(ArrayList<FormField> formFields) {
        return new TableForm(formFields).setData(FXCollections.observableArrayList()).init();
    }

    public static TableForm createTableForm(Pos pos, ArrayList<FormField> formFields) {
        return new TableForm(pos, formFields).setData(FXCollections.observableArrayList()).init();
    }

}
