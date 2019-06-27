package com.ks.hrms.core.component.bar;

import com.jfoenix.controls.JFXButton;
import com.ks.hrms.core.component.CustomStyleClass;
import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CustomToolbar extends HBox implements Toolbar {

    public static final int EXIT = -1;

    private EventHandler<ActionEvent> eventEventHandler;

    public CustomToolbar(final ToolbarControl control) {
        getStylesheets().add(CustomStyleClass.CUSTOM_UI_CSS);
        getStyleClass().add(CustomStyleClass.CUSTOM_BTN_COLOR_WARNING);
        setPadding(new Insets(5,5,5,5));
        setAlignment(Pos.CENTER_RIGHT);
        eventEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFXButton b = (JFXButton) event.getSource();
                control.onClickButton((int) b.getUserData());
                event.isConsumed();
            }
        };
        setEffect(new DropShadow());
    }

    @Override
    public Toolbar init(){
        for (int btnId : getToolbarIds()){
            getChildren().add(generateMenuButton(btnId));
        }

        return this;
    }

    @Override
    public int[] getToolbarIds() {
        return new int[]{ EXIT };
    }

    private Button generateMenuButton(int id){
        switch (id){
            case EXIT:
                JFXButton b = new JFXButton("退出");
                b.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.SIGN_OUT));
                b.setUserData(EXIT);
                b.setOnAction(eventEventHandler);
                setMargin(b,new Insets(0,0,0,5));
                return b;
            default:
                return null;
        }
    }


}
