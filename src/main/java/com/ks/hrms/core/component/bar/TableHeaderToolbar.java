package com.ks.hrms.core.component.bar;

import com.jfoenix.controls.JFXButton;
import com.ks.hrms.core.component.CustomStyleClass;
import com.ks.hrms.core.component.form.TableForm;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class TableHeaderToolbar extends HBox implements Toolbar {

    public static final int ADD_ROW = 1;
    public static final int DEL_ROW = -1;

    public TableForm tableForm;

    private EventHandler<ActionEvent> eventEventHandler;

    public TableHeaderToolbar(final TableForm control) {
        this.tableForm = control;
        getStylesheets().add(CustomStyleClass.CUSTOM_UI_CSS);

        setPadding(new Insets(5, 5, 5, 5));
        setAlignment(Pos.CENTER_RIGHT);

        eventEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFXButton b = (JFXButton) event.getSource();
                control.onClickButton((int) b.getUserData());
                event.isConsumed();
            }
        };
    }

    @Override
    public Toolbar init() {
        for (int btnId : getToolbarIds()) {
            getChildren().add(generateMenuButton(btnId));
        }

        return this;
    }

    @Override
    public int[] getToolbarIds() {
        return new int[]{ ADD_ROW, DEL_ROW };
    }

    private Button generateMenuButton(int id) {
        switch (id) {
            case ADD_ROW:
                JFXButton b = new JFXButton();
                b.getStyleClass().add(CustomStyleClass.CUSTOM_TOOL_BAR_BACKGROUND);
                b.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.SIGN_OUT));
                b.setUserData(ADD_ROW);
                b.setOnAction(eventEventHandler);
                setMargin(b, new Insets(0, 0, 0, 5));
                return b;
            case DEL_ROW:
                b = new JFXButton();
                b.getStyleClass().add(CustomStyleClass.CUSTOM_TOOL_BAR_BACKGROUND);
                b.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.SIGN_OUT));
                b.setUserData(DEL_ROW);
                b.setOnAction(eventEventHandler);
                setMargin(b, new Insets(0, 0, 0, 5));
                return b;
            default:
                return null;
        }
    }
}
