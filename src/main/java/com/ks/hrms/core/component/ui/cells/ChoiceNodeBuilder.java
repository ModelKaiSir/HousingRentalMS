package com.ks.hrms.core.component.ui.cells;

import com.ks.hrms.core.component.ui.AbstractCustomParent;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

public interface ChoiceNodeBuilder<S,T> {

    void startEdit();

    void cancelEdit();

    void updateItem(T var1, boolean var2);

    AbstractCustomParent<T> createNode(T var1, EventHandler<KeyEvent> var2, ChangeListener<Boolean> var3);

    void setValue(T var1);

    T getValue();

    void validateValue() throws Exception;

}
