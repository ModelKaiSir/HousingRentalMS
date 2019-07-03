package com.ks.hrms.core.component.ui.cells;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import com.ks.hrms.core.component.ui.CustomButton;
import com.ks.hrms.core.component.ui.CustomDatePicker;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;

public class ButtonBuilder extends AbstractChoiceNodeBuilder<String> {

    private CustomButton button;

    public ButtonBuilder(FormField formField) {
        super(formField);
    }

    @Override
    public void startEdit() {
        Platform.runLater(() -> {
            button.contentProperty().get().requestFocus();
        });
    }

    @Override
    public void updateItem(String item, boolean empty) {
        Platform.runLater(() -> {
            button.contentProperty().get().requestFocus();
        });
    }


    @Override
    public AbstractCustomParent<String> createNode(String value, EventHandler<KeyEvent> keyEventsHandler, ChangeListener<Boolean> focusChangeListener) {
        button = new CustomButton(formField.getId(),formField.getCaption());
        button.init();
        FormFieldFactory.parseField(formField,button);
        button.afterInit();

        button.contentProperty().get().focusedProperty().addListener(focusChangeListener);
        return button;
    }

    @Override
    public String getValue() {
        return button.getValue();
    }
}
