package com.ks.hrms.core.component;

import com.jfoenix.controls.JFXSpinner;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SpinnerLayout extends BorderPane {

    private StackPane root;
    private JFXSpinner spinner = new JFXSpinner();

    public SpinnerLayout(StackPane root) {
        this.root = root;
        BackgroundFill fill = new BackgroundFill(Color.web("E9E9E9",0.6),CornerRadii.EMPTY,Insets.EMPTY);
        Background background = new Background(fill);
        setBackground(background);
        spinner.setMaxSize(50,50);
        spinner.setPrefSize(50,50);
        setCenter(spinner);
    }

    public void show(){
        root.getChildren().add(this);
        AnchorPane.setLeftAnchor(this,0.0);
        AnchorPane.setRightAnchor(this,0.0);
        AnchorPane.setTopAnchor(this,0.0);
        AnchorPane.setBottomAnchor(this,0.0);
    }

    public void hide(){
        root.getChildren().remove(this);
    }
}
