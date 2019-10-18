package com.ks.hrms.core.component.form;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.*;

/**
 * 表单组件
 *
 * @author QiuKai
 */
public class FreeForm extends AbstractForm {


    private GridPane contentPane;
    private HashMap<String, AbstractCustomParent> fieldMap;
    private int row = 0, col = 0;

    private FreeForm(ArrayList<FormField> formFields) {
        this(null, formFields);
    }

    private FreeForm(Pos pos, ArrayList<FormField> formFields) {
        super(formFields, pos);
    }

    protected void beforeGenerateContent() {
        contentPane = new GridPane();
        fieldMap = new HashMap<>(16);
        contentPane.setVgap(25);
        contentPane.setHgap(5);
        contentPane.setPadding(new Insets(10, 0, 0, 10));
        contentPane.setGridLinesVisible(false);
        if (null != pos) {
            contentPane.setAlignment(pos);
        }

    }

    @Override
    public GridPane generateContent() {

        beforeGenerateContent();

        for (Map.Entry<String, AbstractCustomParent> fieldEntry : factory.getFieldMap().entrySet()) {

            AbstractCustomParent field = fieldEntry.getValue();
            Parent component = field.content();
            contentPane.add(component, col, row);
            fieldMap.put(fieldEntry.getKey(), fieldEntry.getValue());

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

        return contentPane;
    }

    protected void afterGenerateContent() {
        //自适应宽度
        for (ColumnConstraints column : contentPane.getColumnConstraints()) {
            column.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
        }

        ScrollPane sp = new ScrollPane(contentPane);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
    }

    public AbstractCustomParent getField(String key) {
        return fieldMap.get(key);
    }

    public static FreeForm createFreeForm(String caption, ArrayList<FormField> formFields) {
        FreeForm freeForm = new FreeForm(formFields);
        freeForm.setCaption(caption);
        return freeForm;
    }

    public static FreeForm createFreeForm(Pos pos, String caption, ArrayList<FormField> formFields) {
        FreeForm freeForm = new FreeForm(pos, formFields);
        freeForm.setCaption(caption);
        return freeForm;
    }
}
