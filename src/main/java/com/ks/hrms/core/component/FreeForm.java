package com.ks.hrms.core.component;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.ArrayList;

/**
 * 表单组件
 */
public class FreeForm extends Tab{

    private FormFieldFactory fieldFactory;
    private int row = 0,col = 0;

    private FreeForm(String caption, ArrayList<FormField> formFields) {
        setText(caption);
        fieldFactory = new FormFieldFactory();
        fieldFactory.addFormFields(formFields);
        generateContent();
    }

    private void generateContent(){

        GridPane body = new GridPane();

        for(FormField f:fieldFactory.getFormFields()){


            System.out.println("row = "+row+" col = "+col);
            body.add(f.getComponent(),col,row);


            if(f.isBreak()){
                col++;
            }else{
                row++;
                col = 0;
            }


        }

        body.setVgap(5);
        body.setHgap(5);
        body.setPadding(new Insets(10,0,0,10));
        for (ColumnConstraints column:body.getColumnConstraints()){
            column.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
        }
        setContent(body);
    }

    public static FreeForm createFreeForm(String caption,ArrayList<FormField> formFields){
        return new FreeForm(caption,formFields);
    }
}
