package com.ks.hrms.core;

import com.ks.hrms.controller.MyController;
import com.ks.hrms.core.context.AbstractAppFunctionContext;
import com.ks.hrms.core.context.DoSystemContext;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Running extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DoSystemContext systemContext = HRMSAppFunctionContext.getInstance();
        MyController c = new MyController();
        Parent p = AbstractAppFunctionContext.loadFXML("main.fxml",c);
        primaryStage.titleProperty().bind(systemContext.systemNameProperty());
        Scene scene = new Scene(p);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
