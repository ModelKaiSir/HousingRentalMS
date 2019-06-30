package com.ks.hrms.core.component.form;

import com.ks.hrms.core.component.ui.AbstractCustomParent;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

/**
 * 表单组件
 */
public class FreeForm extends AnchorPane {

    private FormFieldFactory fieldFactory;
    private int row = 0, col = 0;
    private Pos pos;

    private FreeForm(ArrayList<FormField> formFields) {
        this(null, formFields);
    }

    private FreeForm(Pos pos,ArrayList<FormField> formFields) {
        fieldFactory = new FormFieldFactory().addFormFields(formFields);
        fieldFactory.init();
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
        for (Map.Entry<String, AbstractCustomParent> fieldEntry : fieldFactory.getFieldMap().entrySet()) {
            AbstractCustomParent field = fieldEntry.getValue();
            Parent component = field.content();
            body.add(component, col, row);

            if (null != field.getPos()) {
                GridPane.setHalignment(component, field.getPos().getHpos());
                GridPane.setValignment(component, field.getPos().getVpos());
            }

            if (field.isBreakable()) {
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
        getChildren().add(body);
    }

    public static FreeForm createFreeForm(ArrayList<FormField> formFields) {
        return new FreeForm(formFields);
    }

    public static FreeForm createFreeForm(String caption, Pos pos, ArrayList<FormField> formFields) {
        return new FreeForm(pos, formFields);
    }
}
