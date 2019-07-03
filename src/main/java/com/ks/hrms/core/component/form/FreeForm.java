package com.ks.hrms.core.component.form;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.FormFieldFactory;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 表单组件
 * @author QiuKai
 */
public class FreeForm extends AbstractForm {


    private GridPane contentPane;
    private Set<AbstractCustomParent> fieldSet;
    private int row = 0, col = 0;

    private FreeForm(ArrayList<FormField> formFields) {
        this(null, formFields);
    }

    private FreeForm(Pos pos,ArrayList<FormField> formFields) {
        fieldFactory = new FormFieldFactory().addFormFields(formFields);
        fieldFactory.init();
        this.pos = pos;
    }

    @Override
    protected void beforeGenerateContent() {
        contentPane = new GridPane();
        fieldSet = new HashSet<>();
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

        for (Map.Entry<String, AbstractCustomParent> fieldEntry : fieldFactory.getFieldMap().entrySet()) {
            AbstractCustomParent field = fieldEntry.getValue();
            Parent component = field.content();
            contentPane.add(component, col, row);
            fieldSet.add(field);
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

    @Override
    protected void afterGenerateContent() {
        //自适应宽度
        for (ColumnConstraints column : contentPane.getColumnConstraints()) {
            column.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
        }
        getChildren().add(contentPane);
    }

    public void readOnlyAble(){
        this.readOnlyProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("readOnly "+newValue);
            fieldSet.forEach(i ->{
                i.setEditable(!newValue);
                i.setDisable(newValue);
            });
        });
    }

    @Override
    public void newItem() {
        DataItem item = new DataItem(fieldFactory);
    }

    @Override
    public void setItem(DataItem dataItem) {

    }

    @Override
    public void update() {

    }

    public static FreeForm createFreeForm(ArrayList<FormField> formFields) {
        FreeForm freeForm = new FreeForm(formFields).init();
        freeForm.readOnlyAble();
        return freeForm;
    }

    public static FreeForm createFreeForm(Pos pos, ArrayList<FormField> formFields) {
        FreeForm freeForm = new FreeForm(pos, formFields).init();
        freeForm.readOnlyAble();
        return freeForm;
    }
}
