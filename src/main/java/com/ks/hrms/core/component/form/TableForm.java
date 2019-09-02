package com.ks.hrms.core.component.form;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * table AbstractForm
 * @author QiuKai
 */
public class TableForm extends AbstractForm implements ToolbarControl{

    private JFXTreeTableView<DataItem> tableView;
    private SimpleBooleanProperty toolbarVisible = new SimpleBooleanProperty(true);

    private TableHeaderToolbar tableHeaderToolbar;

    private TableForm(ArrayList<FormField> formFields) {
        this(null,formFields);
    }

    private TableForm(Pos pos, ArrayList<FormField> formFields) {
        fieldFactory = new FormFieldFactory().addFormFields(formFields);
        this.pos = pos;
    }

    @Override
    protected Parent generateContent() {
        List<JFXTreeTableColumn<DataItem, Object>> cols = fieldFactory.getFormFields().stream().map(item -> {
            JFXTreeTableColumn<DataItem, Object> col = new JFXTreeTableColumn<>(item.getCaption());
            generateCellValueFactory(col, item.getId());
            generateCellFactory(col, item);
            return col;
        }).collect(Collectors.toList());

        tableView.getColumns().addAll(cols);
        return tableView;
    }

    @Override
    protected void beforeGenerateContent() {
        tableHeaderToolbar = new TableHeaderToolbar(this);
        tableHeaderToolbar.init();
        tableView = new JFXTreeTableView<>();
        tableView.setShowRoot(false);
        tableView.editableProperty().bind(Bindings.not(readOnlyProperty()));
        tableView.setColumnResizePolicy(JFXTreeTableView.CONSTRAINED_RESIZE_POLICY);
        tableHeaderToolbar.visibleProperty().bind(toolbarVisible);

    }

    @Override
    protected void afterGenerateContent() {
        setTop(tableHeaderToolbar);
        setCenter(tableView);
        AnchorPane.setTopAnchor(tableView, 5.0);
        AnchorPane.setBottomAnchor(tableView, 1.0);
        AnchorPane.setLeftAnchor(tableView, 1.0);
        AnchorPane.setRightAnchor(tableView, 1.0);
    }

    public TableForm setData(ObservableList<DataItem> data) {
        TreeItem<DataItem> root = new RecursiveTreeItem<DataItem>(data,RecursiveTreeObject::getChildren);
        tableView.setRoot(root);
        return this;
    }

    public void setToolbarVisible(boolean toolbarVisible) {
        this.toolbarVisible.set(toolbarVisible);
    }

    public JFXTreeTableView<DataItem> getTable() {
        return tableView;
    }

    @Override
    public void newItem() {
        DataItem item = new DataItem(fieldFactory);
        TreeItem<DataItem> item1 = new RecursiveTreeItem<>(item,RecursiveTreeObject::getChildren);
        getTable().getRoot().getChildren().add(item1);
    }

    @Override
    public void setItem(DataItem dataItem) {

    }

    @Override
    public void update() {

    }

    @Override
    public void onClickButton(int id) {

        switch (id){
            case TableHeaderToolbar.ADD_ROW:
                this.newItem();
                break;
            case TableHeaderToolbar.DEL_ROW:
                TreeItem item = tableView.getSelectionModel().getSelectedItem();
                if(null!=item){
                    tableView.getRoot().getChildren().remove(item);
                }
                break;
        }
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
        TableForm tableForm = new TableForm(formFields).init();
        return tableForm.setData(FXCollections.observableArrayList());
    }

    public static TableForm createTableForm(Pos pos, ArrayList<FormField> formFields) {
        TableForm tableForm = new TableForm(pos,formFields).init();
        return tableForm.setData(FXCollections.observableArrayList());
    }

}
