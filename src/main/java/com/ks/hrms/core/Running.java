package com.ks.hrms.core;

import com.jfoenix.controls.JFXDecorator;
import com.ks.hrms.controller.MainController;
import com.ks.hrms.core.component.CustomStyleClass;
import com.ks.hrms.core.context.AppContext;
import com.ks.hrms.core.context.AppFunctionContext;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Running extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        AppContext context = AppFunctionContext.getInstance();

        MainController c = new MainController();
        Parent p = AppFunctionContext.loadFXML("main.fxml",c);

        primaryStage.titleProperty().bind(context.getAppName());
        JFXDecorator decorator = new JFXDecorator(primaryStage, p);
        decorator.getStylesheets().add(CustomStyleClass.CUSTOM_UI_CSS);
        decorator.setCustomMaximize(true);
        Scene scene = new Scene(decorator,900,700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
