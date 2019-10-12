package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXComboBox;
import com.ks.hrms.core.component.ui.extend.CaptionAble;
import com.ks.hrms.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Map;

public class CustomComboBox extends AbstractCustomParent<String> implements AbstractCustomParent.InitValue<Label>,CaptionAble<CustomComboBox> {

    private ObservableMap<String,String> dataMap;
    private JFXComboBox<Label> comboBox;

    private SimpleObjectProperty<HBox> contentProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Label> initValueProperty = new SimpleObjectProperty<>();

    public CustomComboBox(String id, String caption) {
        this(id,caption,null);
    }

    public CustomComboBox(String id, String caption, String initValue) {
        super.setFieldId(id);
        super.setCaption(caption);
        if(null!=initValue){
            initValueProperty.set(new Label(initValue));
        }
    }

    public ObservableMap<String, String> getDataMap() {
        return dataMap;
    }


    public void reset(){
        comboBox.getItems().clear();
        for (Map.Entry<String ,String> er:dataMap.entrySet()){
            Label l = new Label(er.getKey()+"("+er.getValue()+")");
            l.setUserData(er.getValue());
            comboBox.getItems().add(l);
        }
    }

    @Override
    public CustomComboBox hideCaption(boolean b) {
        hideCaption = b;
        return this;
    }

    @Override
    public void init() {

        dataMap = FXCollections.observableHashMap();
        comboBox = CustomComponentFactory.generateComBox(this);
        HBox root = CustomComponentFactory.generateHBox();

        if(!hideCaption){
            Label lb = CustomComponentFactory.generateCaptionLb();
            lb.textProperty().bind(captionProperty());
            root.getChildren().add(lb);
        }

        root.getChildren().add(comboBox);

        comboBox.prefHeightProperty().bind(heightProperty());
        comboBox.prefWidthProperty().bind(widthProperty());
        bindProperty(comboBox.disableProperty(),comboBox.editableProperty());
        contentProperty.set(root);

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
    public void setInitValue(Label initValue) {
        initValueProperty.set(initValue);
    }

    @Override
    public ObjectProperty<Label> initValueProperty() {
        return initValueProperty;
    }

    @Override
    public String getValue() {
        return comboBox.getValue().getText();
    }

    @Override
    public ObjectProperty valueProperty() {
        return comboBox.valueProperty();
    }

    @Override
    public void setWidth(double width) {
        if(width <= 0){
            width = CustomComponentFactory.COMBO_BOX_MIN_WIDTH;
        }
        super.setWidth(width);
    }

    public JFXComboBox<Label> getComboBox() {
        return comboBox;
    }
}
