package com.ks.hrms.controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTabPane;
import com.ks.hrms.core.app.AppFunctionFactory;
import com.ks.hrms.core.app.AppFunctionMain;
import com.ks.hrms.core.app.TestAppFunctionMain;
import com.ks.hrms.core.component.CustomStyleClass;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.SpinnerLayout;
import com.ks.hrms.core.component.form.FreeForm;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyController implements Initializable {

    @FXML
    AnchorPane root;
    @FXML
    JFXTabPane body;
    @FXML
    Accordion menu;

    private Resource customResource = new ClassPathResource(CustomStyleClass.CUSTOM_UI_CSS);

    private EventHandler<? super MouseEvent> menuHandler;

    private ObjectProperty<AppFunctionMain> instance = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            menuHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ListView<AppMenuItem> listView = (ListView) event.getSource();
                    if (null != listView.getSelectionModel().getSelectedItem()) {
                        AppMenuItem item = listView.getSelectionModel().getSelectedItem();
                        System.out.println(item.getMenuName());
                        System.out.println(item.getFunctionId());
                        System.out.println(item.getClassPath());
                        //
                        instance.bind(AppFunctionFactory.generate(new SpinnerLayout(root), item));

                    }
                }
            };

            instance.addListener((observable, old, newValue) -> {
                if (null != newValue) {

                    body.getTabs().add(newValue);
                }
                System.out.println("change");
            });
            body.getStylesheets().add(customResource.getURL().toExternalForm());
            body.getTabs().clear();
            body.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

            menu.getPanes().add(tenant());
            menu.getPanes().add(rent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TitledPane tenant() {
        TitledPane t = new TitledPane();
        t.setText("租户管理");
        ListView<AppMenuItem> menus = new ListView<>();
        menus.getItems().add(new AppMenuItem("租户资料维护", "tenant01", "com.ks.hrms.core.app.TestAppFunctionMain"));
        menus.getItems().add(new AppMenuItem("租户资料审核", "tenant02", "com.ks.hrms.core.app.TestAppFunctionMain"));
        menus.getItems().add(new AppMenuItem("租户资料取消审核", "tenant03", "com.ks.hrms.core.app.TestAppFunctionMain"));
        menus.setOnMouseClicked(menuHandler);
        t.setContent(menus);
        return t;
    }

    private TitledPane rent() {
        TitledPane t = new TitledPane();
        ListView<AppMenuItem> menus = new ListView<>();
        t.setText("财务管理");
        menus.getItems().add(new AppMenuItem("财务信息生成", "createrent", "com.ks.hrms.core.app.TestAppFunctionMain"));
        menus.getItems().add(new AppMenuItem("凭证列表", "rentlist", "com.ks.hrms.core.app.TestAppFunctionMain"));
        menus.getItems().add(new AppMenuItem("凭证资料维护", "rent", "com.ks.hrms.core.app.TestAppFunctionMain"));
        menus.setOnMouseClicked(menuHandler);
        t.setContent(menus);
        return t;
    }
}
