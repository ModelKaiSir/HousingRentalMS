package com.ks.hrms.core.component.ui.cells;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.cells.editors.base.EditorNodeBuilder;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import com.ks.hrms.core.component.ui.CustomDatePicker;
import com.ks.hrms.core.component.ui.DateTimeValue;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

public class DatePickerBuilder implements ChoiceNodeBuilder<DateTimeValue> {


    private FormField formField;
    private CustomDatePicker datePicker;

    public DatePickerBuilder(FormField formField) {
        this.formField = formField;
    }

    @Override
    public void startEdit() {
        Platform.runLater(() -> {
            datePicker.getValue().requestFocus();
        });
    }

    @Override
    public void cancelEdit() {
    }

    @Override
    public void updateItem(DateTimeValue item, boolean empty) {
        Platform.runLater(() -> {
            datePicker.getValue().requestFocus();
        });
    }

    @Override
    public AbstractCustomParent<DateTimeValue> createNode(DateTimeValue value, EventHandler<KeyEvent> keyEventsHandler, ChangeListener<Boolean> focusChangeListener) {
        switch (formField.getType()){
            case FormField.ATTRIBUTE_TYPE_DATE:
                datePicker = new CustomDatePicker(formField.getId(),null,CustomDatePicker.TYPE_DATE);
                break;
            case FormField.ATTRIBUTE_TYPE_DATETIME:
                datePicker = new CustomDatePicker(formField.getId(),null,CustomDatePicker.TYPE_DATE_TIME);
                break;
        }

        datePicker.init();
        datePicker.afterInit();
        datePicker.getValue().setOnKeyPressed(keyEventsHandler);
        datePicker.getValue().focusProperty().addListener(focusChangeListener);
        return datePicker;
    }

    @Override
    public void setValue(DateTimeValue value) {
        System.out.println("setValue "+value);
        datePicker.valueProperty().set(value);
    }

    @Override
    public DateTimeValue getValue() {
        System.out.println("getValue "+datePicker.getValue().getValue());
        return datePicker.getValue();
    }

    @Override
    public void validateValue() throws Exception {
        // Do nothing because there is no validation constraints
    }

}
