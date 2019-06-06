package com.ks.hrms.core.component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public abstract class AbstractField {

    private String fieldId;
    private Object defaultValue;
    private String caption;

    private double width;
    private double height;

    private Pos pos;

    private boolean isBreak;
    private boolean readOnly;
    private boolean required;

    public abstract Parent getComponent();
    public abstract void generateContent();



    public AbstractField(String fieldId) {
        this(fieldId,null,null);
    }

    public AbstractField(String fieldId, String caption) {
        this(fieldId,caption,null);
    }

    public AbstractField(String fieldId, Object defaultValue) {
        this(fieldId,null,defaultValue);
    }

    public AbstractField(String fieldId, String caption, Object defaultValue) {
        this.fieldId = fieldId;
        this.defaultValue = defaultValue;
        generateContent();
        setCaption(caption);
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public boolean isBreak() {
        return isBreak;
    }

    public void setBreak(boolean aBreak) {
        isBreak = aBreak;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
