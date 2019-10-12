package com.ks.hrms.core.component.form;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Form
 *
 * @author QiuKai
 */
public abstract class AbstractForm extends Group {

    protected SimpleBooleanProperty readOnly = new SimpleBooleanProperty(false);
    protected SimpleBooleanProperty editable = new SimpleBooleanProperty(false);
    protected SimpleStringProperty caption = new SimpleStringProperty();
    protected Pos pos;

    protected FormFieldFactory factory;

    /**
     * 构建内容
     *
     * @return
     */
    abstract Parent generateContent();

    public AbstractForm(ArrayList<FormField> formFields, Pos pos) {
        factory = new FormFieldFactory();
        factory.addFormFields(formFields);
        factory.init();
        Parent parent = generateContent();
        getChildren().add(parent);
        this.pos = pos;
    }

    public boolean isReadOnly() {
        return readOnly.get();
    }

    public SimpleBooleanProperty readOnlyProperty() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly.set(readOnly);
    }

    public boolean isEditable() {
        return editable.get();
    }

    public SimpleBooleanProperty editableProperty() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable.set(editable);
    }

    public FormFieldFactory getFormFieldFactory() {
        return factory;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public String getCaption() {
        return caption.get();
    }

    public SimpleStringProperty captionProperty() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption.set(caption);
    }
}
