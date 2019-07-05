package com.ks.hrms.core.component.form;

import com.ks.hrms.core.component.FormFieldFactory;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Data Form
 * @author QiuKai
 */
public abstract class AbstractForm extends BorderPane implements DataForm<DataItem>{

    private SimpleBooleanProperty readOnly = new SimpleBooleanProperty(false);

    protected FormFieldFactory fieldFactory;
    protected Pos pos;

    /**
     * 创建内容
     * @return
     */
    protected abstract Parent generateContent();
    /**
     * before GenerateContent
     * @return
     */
    protected abstract void beforeGenerateContent();
    /**
     * after GenerateContent
     * @return
     */
    protected abstract void afterGenerateContent();

    public final <T extends AbstractForm> T init(){
        beforeGenerateContent();
        Parent p = generateContent();
        afterGenerateContent();
        return (T) this;
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

}
