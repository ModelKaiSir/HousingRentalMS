package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXCheckBox;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.ui.extend.CaptionAble;
import com.ks.hrms.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomCheckBox extends AbstractCustomParent<String> implements AbstractCustomParent.InitValue<String>,CaptionAble<CustomCheckBox> {

    private ArrayList<CheckBox> checkBoxes;
    private SimpleBooleanProperty update = new SimpleBooleanProperty();
    private SimpleObjectProperty<HBox> contentProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<String> value = new SimpleObjectProperty<>();

    /**
     * check box selectValueChange 更新数据
     */
    private ChangeListener<Boolean> selectListener = (obs,old,nv) ->{
        update.set(true);
        value.set(checkBoxes.stream().filter(i ->{
            return i.isSelected();
        }).map(i ->{
            return Utils.getValue(String.class,i.getUserData());
        }).collect(Collectors.joining(",")));
        update.set(false);
    };

    public CustomCheckBox(String id, String caption) {
        super.setFieldId(id);
        super.setCaption(caption);
    }

    public void addSelect(List<FormField.FormFieldAttribute> values) {
        for (FormField.FormFieldAttribute item : values) {
            JFXCheckBox box = CustomComponentFactory.generateCheckBox(item.getKey(),item.getValue());
            box.selectedProperty().addListener(selectListener);
            box.disableProperty().bind(disableProperty());
            contentProperty.get().getChildren().add(box);
            checkBoxes.add(box);
        }
    }

    @Override
    public CustomCheckBox hideCaption(boolean b) {
        hideCaption = b;
        return this;
    }

    @Override
    public void init() {
        HBox root = CustomComponentFactory.generateHBox();

        if(!hideCaption){
            Label lb = CustomComponentFactory.generateCaptionLb();
            lb.textProperty().bind(captionProperty());
            root.getChildren().add(lb);
        }

        contentProperty.set(root);
        checkBoxes = new ArrayList<>();
    }

    @Override
    public void afterInit() {

    }

    @Override
    public Parent content() {
        return contentProperty.get();
    }

    @Override
    public ObjectProperty<? extends Parent> contentProperty() {
        return contentProperty;
    }

    @Override
    public void setInitValue(String initValue) {
        value.set(initValue);
        if(null!=checkBoxes && !Utils.isEmpty(initValue)){
            for (String select:initValue.split(",")){

                checkBoxes.forEach(i ->{
                    if(i.getUserData().equals(select)){
                        i.setSelected(true);
                    }else{
                        i.setSelected(false);
                    }

                    i.requestLayout();
                });
            }
        }

    }

    @Override
    public ObjectProperty<String> initValueProperty() {
        return value;
    }

    @Override
    public String getValue() {
        return value.get();
    }

    @Override
    public ObjectProperty valueProperty() {
        return value;
    }

    public SimpleBooleanProperty updateProperty() {
        return update;
    }
}
