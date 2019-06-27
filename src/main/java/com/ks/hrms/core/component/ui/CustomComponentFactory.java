package com.ks.hrms.core.component.ui;

import com.jfoenix.controls.*;
import com.ks.hrms.core.component.CustomStyleClass;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import com.ks.hrms.dao.SystemDao;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.util.List;

public class CustomComponentFactory {

    public static final double DEF_SPACE = 10;

    public static final double LB_MIN_WIDTH = 60;
    public static final double LB_MAX_WIDTH = 100;


    public static final StringConverter<Label> defComboBoxConverter = new StringConverter<Label>() {
        @Override
        public String toString(Label object) {

            return null != object ? object.getText() : " ";
        }

        @Override
        public Label fromString(String string) {
            if (null != string) {
                Label l = new Label(string);
                l.setUserData(string);
                return l;
            } else {
                return null;
            }

        }
    };

    public static Label generateCaptionLb() {
        Label lb = new Label();
        lb.setMinWidth(LB_MIN_WIDTH);
        lb.setMaxWidth(LB_MAX_WIDTH);
        lb.setAlignment(Pos.CENTER);
        return lb;
    }

    public static HBox generateHBox(Node... childes) {
        HBox root = new HBox();
        root.getChildren().addAll(childes);
        root.setSpacing(DEF_SPACE);
        return root;
    }

    public static JFXButton generateButton(String caption) {
        JFXButton button = new JFXButton(caption);
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.getStyleClass().add(CustomStyleClass.CUSTOM_BTN_COLOR_PRIMARY);
        return button;
    }

    public static JFXTextField generateTextField() {
        JFXTextField textField = new JFXTextField();
        return textField;
    }

    public static JFXCheckBox generateCheckBox(String key, String value, Object defValue) {
        JFXCheckBox box = generateCheckBox(key, value);
        if (null != defValue) {
            box.setSelected(value.equals(defValue));
            box.requestFocus();
        }

        return box;
    }

    public static JFXCheckBox generateCheckBox(String key, String value) {
        JFXCheckBox box = new JFXCheckBox(key);
        box.setUserData(value);
        return box;
    }

    public static JFXRadioButton generateRadioBox(String key, String value, Object defValue, ToggleGroup group) {
        JFXRadioButton box = generateRadioBox(key, value, group);
        if (null != defValue) {
            box.setSelected(value.equals(defValue));
            box.requestFocus();
        }
        return box;
    }

    public static JFXRadioButton generateRadioBox(String key, String value, ToggleGroup group) {
        JFXRadioButton box = new JFXRadioButton(key);
        box.setUserData(value);
        if (null != group) {
            box.setToggleGroup(group);
        }
        return box;
    }

    public static JFXComboBox<Label> generateComBox(CustomComboBox comBoBox) {
        JFXComboBox<Label> box = new JFXComboBox<>();
        box.setEditable(true);
        box.setCellFactory(param -> {

            ComboBoxListCell<Label> listCell = new ComboBoxListCell<Label>();

            BackgroundFill fill = new BackgroundFill(Color.web("#03B8CF"), new CornerRadii(3), new Insets(1, 1, 1, 1));
            Background background = new Background(fill);
            listCell.setBackground(background);
            listCell.setPadding(new Insets(15, 0, 15, 0));
            listCell.setEffect(new DropShadow(5, Color.BLACK));
            listCell.setConverter(defComboBoxConverter);
            return listCell;
        });

        box.setConverter(defComboBoxConverter);

        if(comBoBox instanceof AbstractCustomParent.InitValue){
            if(null!=comBoBox.initValueProperty().get()){
                box.getSelectionModel().select(comBoBox.initValueProperty().get());
            }
        }

        return box;
    }

    public static void reloadComBoBox(CustomComboBox comBoBox, String sql) {
        SystemDao dao = HRMSAppFunctionContext.getInstance().getBean(SystemDao.class);
        reloadComBoBox(comBoBox, dao.getItemList(sql));
    }

    public static void reloadComBoBox(CustomComboBox comBoBox, List<FormField.FormFieldAttribute> itemList) {
        comBoBox.getDataMap().clear();
        for (FormField.FormFieldAttribute i : itemList) {
            comBoBox.getDataMap().put(i.getKey(), i.getValue());
        }
        comBoBox.reset();
    }


}
