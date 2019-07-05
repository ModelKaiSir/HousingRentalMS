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
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.HashMap;

public class CustomToolbar extends HBox implements Toolbar {

    private EventHandler<ActionEvent> eventEventHandler;
    private HashMap<Integer,Button> buttonMap;
    private static final Insets insets = new Insets(0,0,0,5);
    public CustomToolbar(final ToolbarControl control) {
        getStylesheets().add(CustomStyleClass.CUSTOM_UI_CSS);
        getStyleClass().add(CustomStyleClass.CUSTOM_TOOL_BAR);

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
        buttonMap = new HashMap<>();
        for (int btnId : getToolbarIds()){
            Button button = generateMenuButton(btnId);
            buttonMap.put(btnId,button);
            getChildren().add(button);
        }

        return this;
    }

    public void setToolbarVisible(int id,boolean visible){
        Button b = buttonMap.get(id);
        if(null!=b){
            b.setVisible(visible);
        }
    }

    public void setToolbarDisable(int id,boolean disable){
        Button b = buttonMap.get(id);
        if(null!=b){
            b.setDisable(disable);
        }
    }

    @Override
    public int[] getToolbarIds() {
        return new int[]{ EXIT };
    }

    private Button generateMenuButton(int id){
        switch (id){
            case EXIT:
                return generateButton(EXIT,GlyphsDude.createIcon(FontAwesomeIcon.SIGN_OUT),"退出");
            case NEW:
                return generateButton(NEW,GlyphsDude.createIcon(FontAwesomeIcon.SIGN_OUT),"新增");
            case VIEW:
                return generateButton(VIEW,GlyphsDude.createIcon(FontAwesomeIcon.SIGN_OUT),"查阅");
            case MODIFY:
                return generateButton(MODIFY,GlyphsDude.createIcon(FontAwesomeIcon.SIGN_OUT),"修改");
            default:
                return null;
        }
    }

    private JFXButton generateButton(int id, Text icon, String desc){
        JFXButton b = new JFXButton(desc);
        b.setGraphic(icon);
        b.setUserData(id);
        b.setOnAction(eventEventHandler);
        setMargin(b,insets);
        return b;
    }
}
