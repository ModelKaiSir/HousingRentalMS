package com.ks.hrms.core.app;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.component.form.TableForm;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;

public class TestAppFunctionMain2 extends AppFunctionMain {

    private TableForm tableForm;

    @Override
    public void init(HRMSAppFunctionContext context) {
        super.init(context);
        tableForm = TableForm.createTableForm(getFormFields());
        tableForm.newItem();
        tableForm.newItem();
        addComponent(tableForm);
    }

    public ArrayList<FormField> getFormFields() {

        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("NAME", "用户名", FormField.TEXTFIELD + FormField.putDefValue("Test")));
        formFields.add(new FormField("BUTTON_OK", "加载", FormField.BUTTON + FormField.putComponentAlignment(Pos.CENTER_RIGHT)));
        String itemList_week = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("星期一", "0"),
                new FormField.FormFieldAttribute("星期二", "1")
        });

        formFields.add(new FormField("XF_WEEKS", "选择日期", FormField.COMBOBOX + itemList_week + FormField.putComponentAlignment(Pos.CENTER)));
        formFields.add(new FormField("XF_DATE", "选择日期", FormField.DATE +FormField.putDefValue("2019-06-30")+ FormField.putComponentAlignment(Pos.CENTER)));
        formFields.add(new FormField("week", "星期", FormField.CHECKBOX + itemList_week + FormField.putDefValue("1") + FormField.putComponentAlignment(Pos.CENTER)));
        formFields.add(new FormField("XF_CONTENT", "备注", FormField.TEXTAREA + FormField.putComponentAlignment(Pos.CENTER)));

        return formFields;

    }
}
