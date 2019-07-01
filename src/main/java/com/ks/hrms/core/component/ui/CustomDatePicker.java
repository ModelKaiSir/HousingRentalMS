package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.ks.hrms.utils.Utils;
import com.sun.javaws.util.JfxHelper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期控件
 * @author QiuKai
 */
public class CustomDatePicker extends AbstractCustomParent<DateTimeValue> implements AbstractCustomParent.InitValue<String> {

    public static final String TYPE_DATE = "DATE";
    public static final String TYPE_DATE_TIME = "DATETIME";

    private String type;

    private ObjectProperty<HBox> content = new SimpleObjectProperty<>();
    private ObjectProperty<DateTimeValue> value = new SimpleObjectProperty<>();
    private ObjectProperty<String> localDateStr = new SimpleObjectProperty();

    public CustomDatePicker(String id, String caption, String type) {
        super(id, caption);
        this.type = type;
    }

    @Override
    public void init() {

        content.set(CustomComponentFactory.generateHBox());
        Label lb = CustomComponentFactory.generateCaptionLb();
        lb.textProperty().bind(captionProperty());

        switch (type){
            case TYPE_DATE:
                JFXDatePicker datePicker = CustomComponentFactory.generateDatePicker();
                datePicker.editableProperty().bind(editableProperty());
                content.get().getChildren().addAll(lb, datePicker);
                value.set(new DateTimeValue(datePicker));
                break;
            case TYPE_DATE_TIME:
                datePicker = CustomComponentFactory.generateDatePicker();
                datePicker.editableProperty().bind(editableProperty());
                JFXTimePicker timePicker = CustomComponentFactory.generateDateTimePicker();
                timePicker.editableProperty().bind(editableProperty());
                content.get().getChildren().addAll(lb, datePicker, timePicker);
                value.set(new DateTimeValue(datePicker,timePicker));
                break;
        }

        localDateStr.addListener((obs,old,nv) ->{
            if(!Utils.isEmpty(nv)){
                DateTimeValue dateTimeValue = value.get();
                //转换成日期
                switch (type){
                    case TYPE_DATE:
                        dateTimeValue.setValue(LocalDateTime.of(dateTimeValue.toLocalDate(nv),LocalTime.now()));
                        break;
                    case TYPE_DATE_TIME:
                        dateTimeValue.setValue(dateTimeValue.toLocalDateTime(nv));
                        break;
                }

            }
        });
    }

    @Override
    public void afterInit() {

    }

    @Override
    public void setInitValue(String initValue) {
        localDateStr.set(initValue);
    }

    @Override
    public ObjectProperty<String> initValueProperty() {
        return localDateStr;
    }

    @Override
    public Parent content() {
        return content.get();
    }

    @Override
    public ObjectProperty<? extends Parent> contentProperty() {
        return content;
    }

    @Override
    public DateTimeValue getValue() {
        return value.get();
    }

    @Override
    public ObjectProperty<DateTimeValue> valueProperty() {
        return value;
    }


}
