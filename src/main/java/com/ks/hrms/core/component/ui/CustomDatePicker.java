package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.ks.hrms.core.component.ui.extend.CaptionAble;
import com.ks.hrms.utils.Utils;
import com.sun.javaws.util.JfxHelper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期控件
 *
 * @author QiuKai
 */
public class CustomDatePicker extends AbstractCustomParent<String> implements AbstractCustomParent.InitValue<String> , CaptionAble<CustomDatePicker> {

    public static final String TYPE_DATE = "DATE";
    public static final String TYPE_DATE_TIME = "DATETIME";

    private String type;

    private JFXDatePicker datePicker;
    private JFXTimePicker timePicker;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ObjectProperty<LocalDateTime> localDateTime = new SimpleObjectProperty<>(LocalDateTime.now());
    private ObjectProperty<HBox> content = new SimpleObjectProperty<>();
    private ObjectProperty<String> value = new SimpleObjectProperty<>();

    public CustomDatePicker(String id, String caption, String type) {
        super(id, caption);
        this.type = type;
    }

    @Override
    public void init() {

        content.set(CustomComponentFactory.generateHBox());
        initDateTime();

        localDateTime.addListener((observable, oldValue, newValue) -> {
            value.set(newValue.format(dateTimeFormatter));
        });
    }

    @Override
    public CustomDatePicker hideCaption(boolean b) {
        hideCaption = b;
        return this;
    }

    private void initDateTime() {

        if(!hideCaption){
            Label lb = CustomComponentFactory.generateCaptionLb();
            lb.textProperty().bind(captionProperty());
            content.get().getChildren().add(lb);
        }

        switch (type) {
            case TYPE_DATE:
                datePicker = CustomComponentFactory.generateDatePicker();
                bindProperty(datePicker.disableProperty(),datePicker.editableProperty());
                content.get().getChildren().add(datePicker);
                break;
            case TYPE_DATE_TIME:
                datePicker = CustomComponentFactory.generateDatePicker();
                timePicker = CustomComponentFactory.generateDateTimePicker();

                bindProperty(datePicker.disableProperty(),datePicker.editableProperty());
                bindProperty(timePicker.disableProperty(),timePicker.editableProperty());
                content.get().getChildren().addAll(datePicker, timePicker);
                break;
        }

        //
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                localDateTime.set(LocalDateTime.of(newValue, localDateTime.get().toLocalTime()));
            }
        });

        if (null != timePicker) {
            //
            timePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (null != newValue) {
                    localDateTime.set(LocalDateTime.of(localDateTime.get().toLocalDate(), newValue));
                }
            });
        }

    }

    @Override
    public void afterInit() {

    }

    @Override
    public void setInitValue(String initValue) {
        value.set(initValue);
        if (!Utils.isEmpty(initValue)) {
            //转换成日期
            switch (type) {
                case TYPE_DATE:
                    datePicker.setValue(LocalDate.parse(initValue, dateFormatter));
                    break;
                case TYPE_DATE_TIME:
                    LocalDateTime dateTime = LocalDateTime.parse(initValue, dateTimeFormatter);
                    datePicker.setValue(dateTime.toLocalDate());
                    timePicker.setValue(dateTime.toLocalTime());
                    break;
            }

        }
    }

    @Override
    public ObjectProperty<String> initValueProperty() {
        return value;
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
    public String getValue() {
        return value.get();
    }

    @Override
    public ObjectProperty<String> valueProperty() {
        return value;
    }

    public void requestFocus() {
        datePicker.requestFocus();
        datePicker.requestLayout();
    }

    public void addFocusListener(ChangeListener<Boolean> focusListener) {
        datePicker.focusedProperty().addListener(focusListener);
        if (null != timePicker) {
            timePicker.focusedProperty().addListener(focusListener);
        }
    }

    public void setOnKeyPressed(EventHandler<KeyEvent> eventEventHandler) {
        datePicker.setOnKeyPressed(eventEventHandler);
        if (null != timePicker) {
            timePicker.setOnKeyPressed(eventEventHandler);
        }
    }
}
