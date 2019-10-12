package com.ks.hrms.core.app;

import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsDude;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

public abstract class AbstractToolbar extends HBox implements ToolBar {

    public static final Insets MARGIN = new Insets(0, 0, 0, 8);

    static ButtonType[] disableButtonType = {
            ButtonType.NEW,
            ButtonType.MODIFY,
            ButtonType.VIEW
    };

    /**
     * 自定义按钮
     */
    private HashMap<String, Button> customButtonMap;

    /**
     * 系统按钮
     */
    private HashMap<ButtonType, Button> appButtonMap;

    private String[] customButtonIds;
    private ButtonType[] appButtonIds;
    private CustomButtonClickListener customButtonClickListener;
    private ButtonClickListener buttonClickListener;

    private Button generateCustomButton(String[] iconConfig, String text) {

        Text label = new Text(iconConfig[0]);
        label.getStyleClass().add("glyph-icon");
        label.setStyle(String.format("-fx-font-family: %s; -fx-font-size: %s;", iconConfig[1], "1em"));

        final Button button = new Button(text);
        button.setGraphic(label);
        button.setUserData(text);

        button.setOnAction(event -> {
            customButtonClickListener.onCustomClickButton((String) button.getUserData());
        });
        return button;
    }

    private void draw() {

        for (ButtonType type : appButtonIds) {
            Button button = appButtonMap.get(type);
            getChildren().add(button);
            setMargin(button, MARGIN);

            //input CustomButton to EXIT next
            if (type == ButtonType.EXIT) {
                for (String cid : customButtonIds) {
                    Button customButton = customButtonMap.get(cid);
                    getChildren().add(customButton);
                    setMargin(button, MARGIN);
                }
            }
        }
    }

    @Override
    public void init() {
        customButtonMap = new HashMap<>(16);
        appButtonMap = new HashMap<>(16);
        setAppButtons(appButtonIds);
        draw();
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
                Button button = ToolbarAppButtonFactory.generateButton(type);
                button.setOnAction(event -> {
                    buttonClickListener.onClickButton(type);
                });
                appButtonMap.put(type, button);
            }
        }

    }

    @Override
    public void setToolbarIds(ButtonType[] types) {
        appButtonIds = types;
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

    static class ToolbarAppButtonFactory {

        public static Button generateButton(ButtonType type) {

            switch (type) {
                case EXIT:
                    return newInstance("退出", type.getIcons(), type.getStyle());
                case NEW:
                    return newInstance("新增", type.getIcons(), type.getStyle());
                case VIEW:
                    return newInstance("查阅", type.getIcons(), type.getStyle());
                case MODIFY:
                    return newInstance("修改", type.getIcons(), type.getStyle());
                case UPDATE:
                    return newInstance("更新", type.getIcons(), type.getStyle());
                case DELETE:
                    return newInstance("删除", type.getIcons(), type.getStyle());
                case UNDO:
                    return newInstance("还原", type.getIcons(), type.getStyle());
                case CLEAR:
                    return newInstance("清除", type.getIcons(), type.getStyle());
                case SEARCH:
                    return newInstance("搜索", type.getIcons(), type.getStyle());
                case SEARCH_PARAMETER:
                    return newInstance("搜索条件", type.getIcons(), type.getStyle());

            }

            return null;
        }

        static Button newInstance(String text, GlyphIcons icons, String style) {
            Button result = GlyphsDude.createIconButton(icons, text);
            result.setStyle(style);
            return result;
        }

    }
}
