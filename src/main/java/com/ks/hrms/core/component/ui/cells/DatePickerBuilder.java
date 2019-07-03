package com.ks.hrms.core.component.ui.cells;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import com.ks.hrms.core.component.ui.CustomDatePicker;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * 日期cell工厂
 * @author QiuKai
 */
public class DatePickerBuilder extends AbstractChoiceNodeBuilder<String> {


    private CustomDatePicker datePicker;

    public DatePickerBuilder(FormField formField) {
        super(formField);
    }

    @Override
    public void startEdit() {
        Platform.runLater(() -> {
            datePicker.requestFocus();
        });
    }

    @Override
    public void updateItem(String item, boolean empty) {
        Platform.runLater(() -> {
            datePicker.requestFocus();
        });
    }

    private void createDatePicker(){
        switch (formField.getType()){
            case FormField.ATTRIBUTE_TYPE_DATE:
                datePicker = new CustomDatePicker(formField.getId(),null,CustomDatePicker.TYPE_DATE).hideCaption(true);
                break;
            case FormField.ATTRIBUTE_TYPE_DATETIME:
                datePicker = new CustomDatePicker(formField.getId(),null,CustomDatePicker.TYPE_DATE_TIME).hideCaption(true);
                break;
        }
    }

    @Override
    public AbstractCustomParent<String> createNode(String value, EventHandler<KeyEvent> keyEventsHandler, ChangeListener<Boolean> focusChangeListener) {
        createDatePicker();
        datePicker.init();
        FormFieldFactory.parseField(formField,datePicker);
        datePicker.afterInit();
        datePicker.setInitValue((String) formField.getDefValue());
        datePicker.setOnKeyPressed(keyEventsHandler);
        datePicker.addFocusListener(focusChangeListener);
        datePicker.content().setTranslateY(10);
        return datePicker;
    }

    @Override
    public String getValue() {
        return datePicker.getValue();
    }

}
