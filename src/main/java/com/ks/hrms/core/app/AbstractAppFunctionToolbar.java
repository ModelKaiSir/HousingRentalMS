package com.ks.hrms.core.app;

import com.ks.hrms.core.component.CustomStyleClass;
import com.sun.javafx.font.Glyph;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.awt.geom.RectangularShape;
import java.util.*;

public abstract class AbstractAppFunctionToolbar extends HBox implements FunctionToolbar {

    public static final String STYLE_FUNCTION_TOOLBAR = "function_toolbar";
    public static final Insets PADDING = new Insets(3, 5, 1, 5);

    static final double MIN_HEIGHT = 30;



    public AbstractAppFunctionToolbar() {

    }

    @Override
    public void init() {

        setMinHeight(MIN_HEIGHT);
        setPrefHeight(USE_COMPUTED_SIZE);
        getStyleClass().add(STYLE_FUNCTION_TOOLBAR);
        setAlignment(Pos.BASELINE_RIGHT);
        setPadding(PADDING);
    }

    @Override
    public void setCaption(String caption) {

    }


}
