package com.ks.hrms.core.app;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.ks.hrms.controller.AppMenuItem;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.component.form.FreeForm;
import com.ks.hrms.core.component.form.TableForm;
import com.ks.hrms.core.context.FunctionParamter;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;

public class TestAppFunctionMain2 extends AppFunctionMain{

    private TableForm tableForm;

    @Override
    public void init(HRMSAppFunctionContext context, FunctionParamter functionParamter) {
        super.init(context, functionParamter);
        generateForm();
        setCaption("测试维护功能");
    }

    protected void generateForm() {
        tableForm = TableForm.createTableForm(getFormFields());
        addComponent(tableForm);
        RecursiveTreeItem item = (RecursiveTreeItem) tableForm.getTable().getRoot();
        FXCollections.observableArrayList(tableForm.newItem(),tableForm.newItem(),tableForm.newItem(),tableForm.newItem()).forEach(i ->{
            item.getChildren().add(new TreeItem<>(i));
        });

    }

    private ArrayList<FormField> getFormFields() {
        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("NAME", "用户名", FormField.TEXTFIELD + FormField.putDefValue("Test")));
        /*formFields.add(new FormField("BUTTON_OK", "test", FormField.BUTTON));

        String itemList_sex = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("男", "0"),
                new FormField.FormFieldAttribute("女", "1")
        });

        formFields.add(new FormField("SEX", "性别", FormField.RADIO_BUTTON + itemList_sex + FormField.putDefValue("1") + FormField.putComponentAlignment(Pos.CENTER)));

        String itemList_week = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("星期一", "0"),
                new FormField.FormFieldAttribute("星期二", "1"),
                new FormField.FormFieldAttribute("星期三", "2"),
                new FormField.FormFieldAttribute("星期四", "4"),
                new FormField.FormFieldAttribute("星期五", "5"),
                new FormField.FormFieldAttribute("星期六", "6")
        });

        formFields.add(new FormField("week", "星期", FormField.CHECKBOX + itemList_week + FormField.putDefValue("1") + FormField.putComponentAlignment(Pos.CENTER)));

        formFields.add(new FormField("XF_WEEKS", "选择日期", FormField.COMBOBOX + itemList_week + FormField.putComponentAlignment(Pos.CENTER)));
        formFields.add(new FormField("XF_DATE2", "选择日期2", FormField.DATETIME + FormField.putComponentAlignment(Pos.CENTER)));
        */
        formFields.add(new FormField("XF_DATE", "选择日期", FormField.DATE +FormField.putDefValue("2019-06-30")+ FormField.putComponentAlignment(Pos.CENTER)));
        formFields.add(new FormField("XF_CONTENT", "备注", FormField.TEXTAREA + FormField.putComponentAlignment(Pos.CENTER)));


       /* String itemList_buttons = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("确定", "CONFIRM"),
                new FormField.FormFieldAttribute("取消", "CANCEL")
        });

        formFields.add(new FormField("BUTTON_OK2", "", FormField.BUTTONS + itemList_buttons + FormField.putComponentAlignment(Pos.CENTER)));
*/
        return formFields;
    }

    @Override
    public void onClickButton(int id) {
        super.onClickButton(id);
    }

}
