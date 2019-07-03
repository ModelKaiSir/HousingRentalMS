package com.ks.hrms.core.component.ui.cells;

import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.EditorNodeBuilder;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import com.ks.hrms.core.component.ui.CustomCheckBox;
import com.ks.hrms.core.component.ui.CustomComboBox;
import com.ks.hrms.core.component.ui.CustomComponentFactory;
import com.ks.hrms.utils.Utils;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

public class ComboBoxEditBuilder implements EditorNodeBuilder<String> {

    private CustomComboBox customComboBox;
    private FormField formField;

    public ComboBoxEditBuilder(FormField formField) {
        this.formField = formField;
    }

    @Override
    public void startEdit() {
        Platform.runLater(() -> {
            customComboBox.getComboBox().requestFocus();
        });
    }

    @Override
    public void cancelEdit() {

    }

    @Override
    public void updateItem(String s, boolean b) {
        Platform.runLater(() -> {
            customComboBox.getComboBox().requestFocus();
        });
    }

    @Override
    public Region createNode(String s, EventHandler<KeyEvent> eventHandler, ChangeListener<Boolean> changeListener) {
        customComboBox = new CustomComboBox(formField.getId(),formField.getCaption(),s).hideCaption(true);
        customComboBox.init();
        CustomComponentFactory.reloadComBoBox(customComboBox, formField.getItemList());
        FormFieldFactory.parseField(formField,customComboBox);
        customComboBox.afterInit();
        customComboBox.getComboBox().setOnKeyPressed(eventHandler);
        customComboBox.getComboBox().focusedProperty().addListener(changeListener);
        return customComboBox.getComboBox();
    }

    @Override
    public void setValue(String s) {

    }

    @Override
    public String getValue() {
        return customComboBox.getComboBox().getValue().getText();
    }

    @Override
    public void validateValue() throws Exception {

    }
}
