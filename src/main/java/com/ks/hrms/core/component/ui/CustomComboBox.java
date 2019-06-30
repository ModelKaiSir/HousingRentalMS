package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.JFXComboBox;
import com.ks.hrms.utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Map;

public class CustomComboBox extends AbstractCustomParent implements AbstractCustomParent.InitValue<Label> {

    private ObservableMap<String,String> dataMap;
    private JFXComboBox<Label> comboBox;
    private Label lb;

    private SimpleObjectProperty<HBox> contentProperty = new SimpleObjectProperty<>();
    private SimpleObjectProperty<Label> initValueProperty = new SimpleObjectProperty<>();

    public CustomComboBox(String id, String caption) {
        this(id,caption,null);
    }

    public CustomComboBox(String id, String caption, String initValue) {
        super.setFieldId(id);
        super.setCaption(caption);
        if(null!=initValue)
        {
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
    public void init() {
        lb = CustomComponentFactory.generateCaptionLb();
        lb.textProperty().bind(captionProperty());
        dataMap = FXCollections.observableHashMap();
        comboBox = CustomComponentFactory.generateComBox(this);
        HBox root = CustomComponentFactory.generateHBox(lb,comboBox);
        contentProperty.set(root);

        this.comboBox.valueProperty().addListener((ob,old,nv) ->{
            this.comboBox.setItems(this.comboBox.getItems().filtered(e ->{
                if(Utils.isEmpty(nv) || Utils.isEmpty(nv.getUserData())){
                    return false;
                }
                System.out.println(e.getUserData()+":"+nv.getUserData());
                if(e.getUserData().toString().contains(nv.getUserData().toString())){
                    return true;
                }
                return false;
            }));
        });
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
    public Object getValue() {
        return null;
    }

    @Override
    public ObjectProperty valueProperty() {
        return null;
    }
}
