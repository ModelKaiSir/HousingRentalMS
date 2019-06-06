package com.ks.hrms.core.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

/**
 * 表单组件
 */
public class FreeForm extends Tab {

    private FormFieldFactory fieldFactory;
    private int row = 0, col = 0;
    private Pos pos;

    private FreeForm(String caption, ArrayList<FormField> formFields) {
        this(caption, null, formFields);
    }

    private FreeForm(String caption, Pos pos,ArrayList<FormField> formFields) {
        setText(caption);
        fieldFactory = new FormFieldFactory();
        fieldFactory.addFormFields(formFields);
        this.pos = pos;
        generateContent();
    }

    private GridPane beforeGenerateContent() {
        GridPane body = new GridPane();
        body.setVgap(25);
        body.setHgap(5);
        body.setPadding(new Insets(10, 0, 0, 10));
        body.setGridLinesVisible(false);
        if (null != pos) {
            body.setAlignment(pos);
        }
        return body;
    }

    private void generateContent() {

        GridPane body = beforeGenerateContent();
        for (Map.Entry<String, AbstractField> fieldEntry : fieldFactory.getFieldMap().entrySet()) {
            System.out.println("row = " + row + " col = " + col);
            AbstractField field = fieldEntry.getValue();
            Parent component = field.getComponent();
            body.add(component, col, row);

            if (null != field.getPos()) {
                GridPane.setHalignment(component, field.getPos().getHpos());
                GridPane.setValignment(component, field.getPos().getVpos());
            }

            if (field.isBreak()) {
                col++;
            } else {
                row++;
                col = 0;
            }
        }

        afterGenerateContent(body);
    }

    private void afterGenerateContent(GridPane body) {
        //自适应宽度
        for (ColumnConstraints column : body.getColumnConstraints()) {
            column.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
        }
        setContent(body);
    }

    public static FreeForm createFreeForm(String caption, ArrayList<FormField> formFields) {
        return new FreeForm(caption, formFields);
    }

    public static FreeForm createFreeForm(String caption, Pos pos, ArrayList<FormField> formFields) {
        return new FreeForm(caption, pos, formFields);
    }
}
