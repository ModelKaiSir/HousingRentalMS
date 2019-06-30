package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.ks.hrms.utils.Utils;
import com.sun.javaws.util.JfxHelper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CustomDatePicker extends AbstractCustomParent<Object> implements AbstractCustomParent.InitValue<String> {

    public static final String TYPE_DATE = "DATE";
    public static final String TYPE_DATE_TIME = "DATETIME";

    private String type;

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private ObjectProperty<HBox> content = new SimpleObjectProperty<>();
    private ObjectProperty<DateTimeValue> value = new SimpleObjectProperty<>();
    private ObjectProperty<String> localDateStr = new SimpleObjectProperty();

    public CustomDatePicker(String id, String caption,String type) {
        super(id, caption);
        this.type = type;
    }

    @Override
    public void init() {
        content.set(CustomComponentFactory.generateHBox());
        Label lb = new Label();
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
                //转换成日期
                value.get().setValue(LocalDateTime.parse(nv,timeFormatter));
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
    public Object getValue() {
        return null;
    }

    @Override
    public ObjectProperty valueProperty() {
        return null;
    }

    class DateTimeValue{

        private JFXDatePicker datePicker;
        private JFXTimePicker timePicker;

        public DateTimeValue(JFXDatePicker date, JFXTimePicker time) {
            this.datePicker = date;
            this.timePicker = time;
        }

        public DateTimeValue(JFXDatePicker date) {
            this(date,null);
        }

        public void setValue(LocalDateTime dateTime){
            datePicker.setValue(dateTime.toLocalDate());
            if(null!=timePicker){
                timePicker.setValue(dateTime.toLocalTime());
            }
        }

        public Object getValue(){

            if(timePicker == null){
                return datePicker.getValue();
            }else{
                LocalDate d = datePicker.getValue();
                LocalTime t = timePicker.getValue();

                return LocalDateTime.of(d,t);
            }
        }
    }
}
