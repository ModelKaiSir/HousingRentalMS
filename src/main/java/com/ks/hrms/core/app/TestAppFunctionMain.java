package com.ks.hrms.core.app;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.FreeForm;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.application.Application;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class TestAppFunctionMain extends AppFunctionMain{

    private FreeForm freeForm;

    @Override
    public void init(HRMSAppFunctionContext context) {
        super.init(context);
        generateForm();
        setCaption("测试维护功能");
    }

    protected void generateForm(){
        freeForm = FreeForm.createFreeForm(getFormFields());
        addComponent(freeForm);
    }

    private ArrayList<FormField> getFormFields() {
        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("NAME", "用户名：", FormField.TEXTFIELD + FormField.putDefValue("Test")));
        formFields.add(new FormField("PASSWORD", "密码：", FormField.TEXTFIELD +FormField.READONLY + FormField.putDefValue("Test")+ FormField.BREAK));
        formFields.add(new FormField("BUTTON_OK", "test", FormField.BUTTON));

        String itemList_sex = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("男", "0"),
                new FormField.FormFieldAttribute("女", "1")
        });

        formFields.add(new FormField("SEX", "性别", FormField.RADIO_BUTTON + itemList_sex +FormField.putDefValue("1")+ FormField.putComponentAlignment(Pos.CENTER)));

        String itemList_week = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("星期一", "0"),
                new FormField.FormFieldAttribute("星期二", "1"),
                new FormField.FormFieldAttribute("星期三", "2"),
                new FormField.FormFieldAttribute("星期四", "4"),
                new FormField.FormFieldAttribute("星期五", "5"),
                new FormField.FormFieldAttribute("星期六", "6")
        });

        formFields.add(new FormField("week", "星期", FormField.CHECKBOX + itemList_week +FormField.putDefValue("1")+ FormField.putComponentAlignment(Pos.CENTER)));

        String itemList_buttons = FormField.putItemList(new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("确定", "CONFIRM"),
                new FormField.FormFieldAttribute("取消", "CANCEL")
        });

        formFields.add(new FormField("BUTTON_OK2", "", FormField.BUTTONS + itemList_buttons + FormField.putComponentAlignment(Pos.CENTER)));
        formFields.add(new FormField("XF_WEEKS", "选择日期", FormField.COMBOBOX + itemList_week + FormField.putComponentAlignment(Pos.CENTER)));

        return formFields;
    }

    @Override
    public void onClickButton(int id) {
        super.onClickButton(id);
    }
}
