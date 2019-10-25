package com.ks.hrms.core.component.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;

/**
 * HRMS系统中的控件
 *
 * @author QiuKai
 */
public interface CustomParent<T> {

    /**
     * 组件的ID
     *
     * @param id
     */
    void setFieldId(String id);

    SimpleStringProperty idProperty();

    /**
     * 组件的标题
     *
     * @param caption
     */
    void setCaption(String caption);

    SimpleStringProperty captionProperty();

    /**
     * 是否可编辑
     *
     * @param b
     */
    void setEditable(boolean b);

    SimpleBooleanProperty editableProperty();

    /**
     * 是否启用
     *
     * @param b
     */
    void setDisable(boolean b);

    SimpleBooleanProperty disableProperty();

    /**
     * 是否必填
     *
     * @param r
     */
    void setRequired(boolean r);

    SimpleBooleanProperty requiredProperty();

    /**
     * 是否换行
     *
     * @param b
     */
    void setBreakable(boolean b);

    SimpleBooleanProperty breakableProperty();

    void setSelected(boolean b);

    SimpleBooleanProperty selectedProperty();

    /**
     * 返回自定义组件
     *
     * @return
     */
    Parent content();

    ObjectProperty<? extends Parent> contentProperty();

    T getValue();

    ObjectProperty<T> valueProperty();

    /**
     * 对齐属性
     *
     * @param pos
     */
    void setPos(Pos pos);

    ObjectProperty<Pos> posProperty();

    /**
     * 宽
     *
     * @param width
     */
    void setWidth(double width);

    SimpleDoubleProperty widthProperty();

    /**
     * 高
     *
     * @param height
     */
    void setHeight(double height);

    SimpleDoubleProperty heightProperty();
}
