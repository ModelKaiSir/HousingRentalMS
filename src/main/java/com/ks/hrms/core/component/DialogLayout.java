package com.ks.hrms.core.component;

import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class DialogLayout extends JFXDialog {

    public DialogLayout(StackPane dialogContainer,DialogTransition transitionType) {
        super();
        this.setDialogContainer(dialogContainer);
        this.setContent(generateContent());
        setTransitionType(transitionType);
    }

    public DialogLayout(StackPane dialogContainer) {
        this(dialogContainer,DialogTransition.CENTER);
    }

    private Region generateContent(){
        return null;
    }
}
