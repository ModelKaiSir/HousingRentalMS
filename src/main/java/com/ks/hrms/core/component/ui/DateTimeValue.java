package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期控件数据
 * @author QiuKai
 */
public class DateTimeValue {

    private JFXDatePicker datePicker;
    private JFXTimePicker timePicker;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public DateTimeValue(JFXDatePicker date, JFXTimePicker time) {
        this.datePicker = date;
        this.timePicker = time;
    }

    public DateTimeValue(JFXDatePicker date) {
        this(date, null);
    }

    public void setValue(LocalDateTime dateTime) {
        datePicker.setValue(dateTime.toLocalDate());
        if (null != timePicker) {
            timePicker.setValue(dateTime.toLocalTime());
        }
    }

    public Object getValue() {

        if (timePicker == null) {
            return datePicker.getValue();
        } else {
            LocalDate d = datePicker.getValue();
            LocalTime t = timePicker.getValue();

            return LocalDateTime.of(d, t);
        }
    }

    public LocalDate toLocalDate(String str){
        return LocalDate.parse(str,dateFormatter);
    }

    public LocalDateTime toLocalDateTime(String str){
        return LocalDateTime.parse(str,dateTimeFormatter);
    }

    public void requestFocus() {
        datePicker.requestFocus();
        datePicker.requestLayout();
    }

    @Override
    public String toString() {
        Object object = getValue();
        if (object instanceof LocalDateTime) {
            return ((LocalDateTime) object).format(dateTimeFormatter);
        } else if (object instanceof LocalDate) {
            return ((LocalDate) object).format(dateFormatter);
        } else {
            return null;
        }
    }

    public ReadOnlyBooleanProperty focusProperty() {
        return datePicker.focusedProperty();
    }

    public void setOnKeyPressed(EventHandler<KeyEvent> keyEventsHandler) {
        datePicker.setOnKeyPressed(keyEventsHandler);
    }
}
