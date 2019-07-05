package com.ks.hrms.core.component.ui.cells;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import com.ks.hrms.core.component.ui.CustomButton;
import com.ks.hrms.core.component.ui.CustomCheckBox;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyEvent;

public class CheckBoxBuilder extends AbstractChoiceNodeBuilder<String> {

    private CustomCheckBox checkBox;

    public CheckBoxBuilder(FormField formField) {
        super(formField);
    }

    @Override
    public void startEdit() {
        Platform.runLater(() -> {
            checkBox.contentProperty().get().requestFocus();
        });
    }

    @Override
    public void updateItem(String item, boolean empty) {
        Platform.runLater(() -> {
            checkBox.contentProperty().get().requestFocus();
        });
    }


    @Override
    public AbstractCustomParent<String> createNode(String value, EventHandler<KeyEvent> keyEventsHandler, ChangeListener<Boolean> focusChangeListener) {
        checkBox = new CustomCheckBox(formField.getId(),formField.getCaption()).hideCaption(true);
        checkBox.init();
        checkBox.addSelect(formField.getItemList());
        FormFieldFactory.parseField(formField,checkBox);
        checkBox.afterInit();
        checkBox.updateProperty().addListener(focusChangeListener);
        checkBox.content().setTranslateY(15);
        return checkBox;
    }

    @Override
    public String getValue() {
        return checkBox.getValue();
    }
}
