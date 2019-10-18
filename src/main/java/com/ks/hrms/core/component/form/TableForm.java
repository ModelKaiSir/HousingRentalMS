package com.ks.hrms.core.component.form;

import com.ks.hrms.core.app.AbstractToolbar;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

public class TableForm<S> extends AbstractForm {

    private TableView<S> table;
    private ObservableList<S> data;

    private TableForm(ArrayList<FormField> formFields) {
        this(null, formFields);
    }

    private TableForm(Pos pos, ArrayList<FormField> formFields) {
        super(formFields, pos);
    }

    private void generateColumn() {

        for (FormField field : factory.getFormFields()) {
            TableColumn col = new TableColumn();
            col.setText(field.getCaption());
            col.setCellValueFactory(new PropertyValueFactory<>(field.getId()));
            col.setCellFactory(factory.generateColumnCellFactory(col, field));
            table.getColumns().add(col);
        }
    }

    @Override
    Parent generateContent() {
        table = new TableView<>();
        generateColumn();
        table.editableProperty().bind(editable);
        return table;
    }

    public TableView<S> getTable() {
        return table;
    }

    public void setData(ObservableList<S> data) {
        this.data = data;
        table.setItems(data);
    }

    public static TableForm createTableForm(String caption, ArrayList<FormField> formFields, boolean editable) {
        TableForm tableForm = new TableForm(formFields);
        tableForm.setCaption(caption);
        tableForm.setEditable(editable);
        return tableForm;
    }

    public static TableForm createTableForm(Pos pos, String caption, ArrayList<FormField> formFields, boolean editable) {
        TableForm tableForm = new TableForm(pos, formFields);
        tableForm.setCaption(caption);
        tableForm.setEditable(editable);
        return tableForm;
    }
}
