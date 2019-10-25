package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.ks.hrms.core.component.ui.extend.CaptionAble;
import javafx.beans.property.*;
import javafx.geometry.Pos;

/**
 * 自定义控件属性
 * @author QiuKai
 */
public abstract class AbstractCustomParent<T> implements CustomParent<T> {

    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty caption = new SimpleStringProperty();

    private SimpleBooleanProperty editable = new SimpleBooleanProperty(true);
    private SimpleBooleanProperty disable = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty breakable = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty required = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty selected = new SimpleBooleanProperty();

    private SimpleDoubleProperty width = new SimpleDoubleProperty();
    private SimpleDoubleProperty height = new SimpleDoubleProperty();

    private SimpleObjectProperty<Pos> pos = new SimpleObjectProperty<>();

    /**
     * extend
     */
    protected boolean hideCaption;
    /**
     * 初始化
     */
    public abstract void init();

    /**
     * 设置完属性之后
     */
    public abstract void afterInit();

    public abstract void setValue(T value);

    public AbstractCustomParent() {

    }

    public AbstractCustomParent(String id, String caption) {
        this.id.set(id);
        this.caption.set(caption);
    }

    public String getId() {
        return id.get();
    }

    @Override
    public SimpleStringProperty idProperty() {
        return id;
    }

    @Override
    public void setFieldId(String id) {
        this.id.set(id);
    }

    public boolean isEditable() {
        return editable.get();
    }

    @Override
    public SimpleBooleanProperty editableProperty() {
        return editable;
    }

    @Override
    public void setEditable(boolean editable) {
        this.editable.set(editable);
    }

    public boolean isBreakable() {
        return breakable.get();
    }

    @Override
    public SimpleBooleanProperty breakableProperty() {
        return breakable;
    }

    @Override
    public void setBreakable(boolean breakable) {
        this.breakable.set(breakable);
    }

    public boolean isRequired() {
        return required.get();
    }

    @Override
    public SimpleBooleanProperty requiredProperty() {
        return required;
    }

    @Override
    public void setRequired(boolean required) {
        this.required.set(required);
    }

    public String getCaption() {
        return caption.get();
    }

    @Override
    public void setCaption(String caption) {
        this.caption.set(caption);
    }

    @Override
    public SimpleStringProperty captionProperty() {
        return caption;
    }

    public Pos getPos() {
        return pos.get();
    }

    @Override
    public ObjectProperty<Pos> posProperty() {
        return pos;
    }

    @Override
    public void setWidth(double width) {
        this.width.set(width);
    }

    @Override
    public SimpleDoubleProperty widthProperty() {
        return width;
    }

    @Override
    public void setHeight(double height) {
        this.height.set(height);
    }

    @Override
    public SimpleDoubleProperty heightProperty() {
        return height;
    }

    @Override
    public void setPos(Pos pos) {
        this.pos.set(pos);
    }

    @Override
    public void setDisable(boolean disable) {
        this.disable.set(disable);
    }

    @Override
    public SimpleBooleanProperty disableProperty() {
        return disable;
    }

    @Override
    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public void bindProperty(BooleanProperty disable, BooleanProperty editable){
        disable.bind(this.disable);
        editable.bind(this.editable);
    }

    /**
     * 支持设置默认值 如 TextField等Control控件
     * @param <T>
     */
    public interface InitValue<T> {

        /**
         * 默认值
         * @param initValue
         */
        void setInitValue(T initValue);

        /**
         * 返回默认值
         * @return
         */
        ObjectProperty<T> initValueProperty();
    }


}
