package com.ks.hrms.core.app;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.TableForm;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.util.ArrayList;

public abstract class AbstractFunctionNavigator<S> extends SplitPane implements FunctionNavigator, FunctionComponent, ToolBar.ButtonClickListener {

    protected Function function;

    protected ToolBar toolBar;
    private TableForm<S> tableForm;
    private String caption;

    public AbstractFunctionNavigator() {
        setOrientation(Orientation.VERTICAL);
    }

    @Override
    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public void init() {

        toolBar = createToolBar();
        toolBar.setButtonClickListener(this);
        toolBar.draw();
        tableForm = TableForm.createTableForm(caption, getFormFields(), false);
        tableForm.setData(loadData());

        getItems().add((Parent)toolBar);
        getItems().add(tableForm);
        setDividerPositions(0.1);

    }

    @Override
    public void close() {

    }

    @Override
    public void onClose(Callback call) {

    }

    @Override
    public void onOpen(Callback call) {

    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public TableForm<S> getTableForm() {
        return tableForm;
    }

    public abstract ToolBar createToolBar();

    public abstract ArrayList<FormField> getFormFields();

    public abstract ObservableList<S> loadData();

    public abstract ButtonType[] getButtonTypes();

    protected void fitToParent(Parent parent) {
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }
}
