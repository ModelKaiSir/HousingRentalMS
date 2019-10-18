package com.ks.hrms.core.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

public abstract class AbstractToolbar extends HBox implements ToolBar {

    public static final String STYLE_FUNCTION_TOOLBAR = "function_toolbar";
    public static final Insets PADDING = new Insets(3, 5, 1, 5);
    public static final Insets MARGIN = new Insets(0, 0, 0, 8);

    static final double MIN_HEIGHT = 30;

    /**
     * 自定义按钮
     */
    protected HashMap<String, Button> customButtonMap;

    /**
     * 系统按钮
     */
    protected HashMap<ButtonType, Button> appButtonMap;

    protected String[] customButtonIds;
    protected ButtonType[] appButtonTypes;

    protected CustomButtonClickListener customButtonClickListener;
    protected ButtonClickListener buttonClickListener;

    public AbstractToolbar() {
        super();
        setMinHeight(MIN_HEIGHT);
        setPrefHeight(USE_COMPUTED_SIZE);
        setAlignment(Pos.BASELINE_RIGHT);
        setPadding(PADDING);

        getStyleClass().add(STYLE_FUNCTION_TOOLBAR);
    }

    protected Button generateCustomButton(String[] iconConfig, String text) {

        Text label = new Text(iconConfig[0]);
        label.getStyleClass().add("glyph-icon");
        label.setStyle(String.format("-fx-font-family: %s; -fx-font-size: %s;", iconConfig[1], "1em"));

        final Button button = new Button(text);
        button.setGraphic(label);
        button.setUserData(text);

        button.setOnAction(event -> {
            if (null != customButtonClickListener) {
                customButtonClickListener.onCustomClickButton((String) button.getUserData());
            }
        });
        return button;
    }

    @Override
    public void draw() {

        for (ButtonType type : appButtonTypes) {
            Button button = appButtonMap.get(type);
            getChildren().add(button);
            setMargin(button, MARGIN);

            //input CustomButton to EXIT next
            if (type == ButtonType.EXIT && null != customButtonIds) {
                for (String cid : customButtonIds) {
                    Button customButton = customButtonMap.get(cid);
                    getChildren().add(customButton);
                    setMargin(customButton, MARGIN);
                }
            }
        }
    }

    @Override
    public void init() {
        customButtonMap = new HashMap<>(16);
        appButtonMap = new HashMap<>(16);
        setAppButtons(appButtonTypes);
    }

    @Override
    public void setCustomButtons(String... buttons) {

        if (null != buttons) {
            customButtonIds = new String[buttons.length];

            for (int i = 0; i < customButtonIds.length; i++) {

                String[] config = buttons[i].split("\t");
                String[] iconConfig = config[0].split(":");

                customButtonIds[i] = config[1];
                customButtonMap.put(config[1], generateCustomButton(iconConfig, config[1]));
            }


        }
    }

    @Override
    public void setAppButtons(ButtonType... appButtons) {

        if (null != appButtons) {
            for (ButtonType type : appButtons) {
                Button button = generateButton(type);
                button.setOnAction(event -> {
                    buttonClickListener.onClickButton(type);
                });

                appButtonMap.put(type, button);
            }
        }

    }

    @Override
    public void setToolbarTypes(ButtonType[] types) {
        appButtonTypes = types;
    }

    @Override
    public void setCustomButtonVisible(String id, boolean visible) {
        Button button = customButtonMap.get(id);
        if (null != button) {
            button.setVisible(visible);
        }
    }

    @Override
    public void setAppButtonVisible(ButtonType type, boolean visible) {
        Button button = appButtonMap.get(type);
        if (null != button) {
            button.setVisible(visible);
        }
    }

    @Override
    public void setCustomButtonClickListener(CustomButtonClickListener listener) {
        customButtonClickListener = listener;
    }

    @Override
    public void setButtonClickListener(ButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    @Override
    public HashMap<ButtonType, Button> getAppButtonMap() {
        return appButtonMap;
    }

    @Override
    public HashMap<String, Button> getCustomButtonMap() {
        return customButtonMap;
    }

    abstract Button generateButton(ButtonType type);
}
