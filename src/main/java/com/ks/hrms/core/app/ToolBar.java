package com.ks.hrms.core.app;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.omg.CORBA.INTF_REPOS;

import java.util.HashMap;

public interface ToolBar {

    void init();

    /**
     * FontAwesomeIcon.CREATIVE_COMMONS.characterToString() +":"+ FontAwesomeIcon.CREATIVE_COMMONS.getFontFamily() +"\t"+"ButtonName"  [+":choice1,choice2"]
     *
     * @param customButtons
     */
    void setCustomButtons(String... customButtons);

    void setAppButtons(ButtonType... appButtons);

    void setToolbarTypes(ButtonType[] types);

    HashMap<String, Button> getCustomButtonMap();

    HashMap<ButtonType, Button> getAppButtonMap();

    void setCustomButtonVisible(String id, boolean visible);

    void setAppButtonVisible(ButtonType type, boolean visible);

    void setCustomButtonClickListener(CustomButtonClickListener listener);

    void setButtonClickListener(ButtonClickListener listener);

    void draw();

    public static abstract interface CustomButtonClickListener{

        void onCustomClickButton(String id);

    }

    public static abstract interface ButtonClickListener {

        void onClickButton(ButtonType type);

    }
}
