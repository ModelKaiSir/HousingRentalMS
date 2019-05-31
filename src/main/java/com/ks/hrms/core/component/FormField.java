package com.ks.hrms.core.component;

import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class FormField {

    static final String TRUE = "TRUE";

    /**
     * 组件的类型
     */
    public static final String ATTRIBUTE_TYPE = "TYPE";
    /**
     * 换行
     */
    public static final String ATTRIBUTE_BREAK = "BREAK";
    /**
     * 宽
     */
    public static final String ATTRIBUTE_WIDTH = "W";
    /**
     * 高
     */
    public static final String ATTRIBUTE_HEIGHT = "H";
    /**
     * 只读
     */
    public static final String ATTRIBUTE_READONLY = "RED";
    /**
     * 必填
     */
    public static final String ATTRIBUTE_REQ = "REQ";


    public static final String TYPE_TEXTFIELD = "TYPE=TEXTFIELD;";
    public static final String TYPE_BUTTON = "TYPE=BUTTON;";
    public static final String TYPE_SELECT_FIELD = "TYPE=SELECT;";
    public static final String TYPE_CHECKBOX = "TYPE=CHECKBOX;";
    public static final String TYPE_RADIO_BOX = "TYPE=RADIOBUTTON";

    public static final String ATTRIBUTE_VALUE_BREAK = "BREAK=TRUE;";
    public static final String ATTRIBUTE_VALUE_READONLY = "RED=TRUE;";
    public static final String ATTRIBUTE_VALUE_REQ = "REQ=TRUE;";

    /**
     * 字段
     */
    private String id;
    /**
     * 描述
     */
    private String caption;
    /**
     * 配置
     */
    private String[] attributes;

    private Parent component;

    private Object defaultValue;

    private double width;
    private double height;

    private boolean isBreak;
    private boolean isReadOnly;
    private boolean required;

    public FormField(String id,String caption,String attribute) {
        this.id = id;
        this.caption = caption;
        this.attributes = attribute.split(";");
    }

    private String getAttributeValue(String attribute){
        return attribute.split("=")[1];
    }

    private boolean isTrue(String attribute){
        if(TRUE.equals(getAttributeValue(attribute))){
            return true;
        }else{
            return false;
        }
    }

    private boolean isBreak(String attribute){
        if(attribute.contains(ATTRIBUTE_BREAK)){
            return TRUE.equals(attribute.split("=")[1]);
        }else {
            return false;
        }
    }

    public void parseAttributes(FormFieldFactory factory){
        for(String a:attributes){
             if(a.contains(ATTRIBUTE_BREAK)){
                isBreak = isBreak(a);
            }else if(a.contains(ATTRIBUTE_WIDTH)){
                width = new Double(getAttributeValue(a));
                System.out.println(width);
            }else if(a.contains(ATTRIBUTE_HEIGHT)){
                height = new Double(getAttributeValue(a));
            }else if(a.contains(ATTRIBUTE_READONLY)){
                isReadOnly = isTrue(a);
            }else if(a.contains(ATTRIBUTE_REQ)){
                required = isTrue(a);
            }else if(a.contains(ATTRIBUTE_TYPE)){
                component = factory.generateComponent(this,a+";");
            }
        }
    }

    public static String putWidth(String v){
        return ATTRIBUTE_WIDTH+"="+v+";";
    }

    public static String putHeight(String v){
        return ATTRIBUTE_HEIGHT+"="+v+";";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public Parent getComponent() {
        return component;
    }

    public void setComponent(Parent component) {
        this.component = component;
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

    public boolean isBreak() {
        return isBreak;
    }

    public void setBreak(boolean aBreak) {
        isBreak = aBreak;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public static void main(String[] args) {
    }

}
