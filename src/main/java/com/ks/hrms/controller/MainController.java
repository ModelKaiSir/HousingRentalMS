package com.ks.hrms.controller;

import com.jfoenix.controls.*;
import com.ks.hrms.core.app.AbstractAppFunction;
import com.ks.hrms.core.component.CustomStyleClass;
import com.ks.hrms.core.context.AppFunctionContext;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author QiuKai
 * 主程序Controller
 */
public class MainController implements Initializable {

    @FXML
    StackPane container;
    @FXML
    TabPane body;
    @FXML
    Accordion menu;

    @FXML
    SplitPane point;

    @FXML
    JFXTextField searchField;

    protected AppFunctionContext context = AppFunctionContext.getInstance();
    private AppMenuAdapter appMenuAdapter;
    private EventHandler<? super MouseEvent> menuHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initHandler();
        initProperty();

        menu.getPanes().add(tenant());
        menu.getPanes().add(rent());

        appMenuAdapter = new AppMenuAdapter(menu, searchField);
        appMenuAdapter.addFilterListener(menuHandler);

        point.setDividerPosition(0, 0.1f);
        body.getStylesheets().add(CustomStyleClass.CUSTOM_UI_CSS);
        body.getStylesheets().add(AbstractAppFunction.STYLESHEET);

        body.getStyleClass().add(CustomStyleClass.HRMS_ROOT_BACKGROUND);
        body.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }

    protected void initHandler() {

        menuHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ListView<AppMenuItem> listView = (ListView) event.getSource();

                if (null != listView.getSelectionModel().getSelectedItem()) {
                    AppMenuItem item = listView.getSelectionModel().getSelectedItem();
                    context.open(item);
                }
            }
        };
    }

    protected void initProperty() {

        context.localAppProperty().addListener((observable, old, newValue) -> {
            if (null != newValue) {
                body.getTabs().add(newValue);
                body.getSelectionModel().select(newValue);
            }
        });
    }

    private TitledPane tenant() {
        TitledPane t = new TitledPane();
        t.setText("租户管理");
        ListView<AppMenuItem> menus = new ListView<>();
        menus.getItems().add(new AppMenuItem("租户资料维护", "tenant01", "com.ks.hrms.test.TestFunction"));
        menus.setOnMouseClicked(menuHandler);
        t.setContent(menus);
        return t;
    }

    private TitledPane rent() {
        TitledPane t = new TitledPane();
        ListView<AppMenuItem> menus = new ListView<>();
        t.setText("财务管理");
        menus.getItems().add(new AppMenuItem("财务信息生成", "createrent", "com.ks.hrms.test.TestFunction"));
        menus.setOnMouseClicked(menuHandler);
        t.setContent(menus);
        return t;
    }
}
