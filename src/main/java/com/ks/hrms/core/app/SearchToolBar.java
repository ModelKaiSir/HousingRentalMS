package com.ks.hrms.core.app;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.HashMap;

public class SearchToolBar extends DefaultToolBar {

    private HBox placeholder;
    protected ButtonType[] searchButtonTypes;
    private HashMap<ButtonType, Button> searchButtonMap;

    @Override
    public void init() {
        super.init();
        generatePlaceHolder();
        searchButtonMap = new HashMap<>(16);
        searchButtonTypes = getSearchButtonTypes();
    }

    @Override
    public void draw() {

        setSearchButtons(getSearchButtonTypes());

        for (ButtonType type : searchButtonTypes) {
            Button button = searchButtonMap.get(type);
            getChildren().add(button);
            setMargin(button, MARGIN);
        }

        getChildren().add(placeholder);
        super.draw();
    }

    protected void setSearchButtons(ButtonType... types) {
        if (null != types) {
            for (ButtonType type : types) {
                Button button = generateButton(type);
                button.setOnAction(event -> {
                    buttonClickListener.onClickButton(type);
                });
                searchButtonMap.put(type, button);
            }
        }
    }

    @Override
    public void setButtonClickListener(ButtonClickListener buttonClickListener) {
        super.setButtonClickListener(buttonClickListener);
    }

    protected ButtonType[] getSearchButtonTypes() {
        return new ButtonType[]{ ButtonType.SEARCH_PARAMETER, ButtonType.SEARCH };
    }

    private void generatePlaceHolder() {
        placeholder = new HBox();
        setHgrow(placeholder, Priority.ALWAYS);
    }
}
