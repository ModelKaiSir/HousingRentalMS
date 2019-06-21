package com.ks.hrms.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.StringConverter;

public class AppMenuItem extends Label{

    private SimpleStringProperty menuName = new SimpleStringProperty();
    private SimpleStringProperty functionId = new SimpleStringProperty();
    private SimpleStringProperty classPath = new SimpleStringProperty();

    public AppMenuItem(String menuName, String functionId, String classPath) {
        this.menuName.set(menuName);
        this.functionId.set(functionId);
        this.classPath.set(classPath);
        setText(menuName);
        setTooltip(new Tooltip(functionId));
    }

    public String getMenuName() {
        return menuName.get();
    }

    public SimpleStringProperty menuNameProperty() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName.set(menuName);
    }

    public String getFunctionId() {
        return functionId.get();
    }

    public SimpleStringProperty functionIdProperty() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId.set(functionId);
    }

    public String getClassPath() {
        return classPath.get();
    }

    public SimpleStringProperty classPathProperty() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath.set(classPath);
    }
}
