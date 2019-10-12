package com.ks.hrms.test;

import com.ks.hrms.core.app.DefaultAppFunction;
import com.ks.hrms.core.app.DefaultAppFunctionMain;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.FreeForm;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.util.ArrayList;

public class TestFunctionMain extends DefaultAppFunctionMain {

    protected FreeForm freeForm;
    protected ArrayList<FormField> formFields;

    @Override
    public void init() {

        super.init();
        initCustomButton();
        formFields = createFormFields();
        freeForm = FreeForm.createFreeForm("用户资料", formFields);
        addComponent(0, 0, 0, freeForm);
        draw();
    }

    protected void initCustomButton() {
        this.toolBar.setCustomButtons(createCustomButton(FontAwesomeIcon.SIGN_OUT,"测试自定义"));
    }

    private String sexType() {
        FormField.FormFieldAttribute[] attributes = new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("男", "0"),
                new FormField.FormFieldAttribute("女", "1")
        };

        return FormField.putItemList(attributes);
    }

    private String userGroupType() {

        FormField.FormFieldAttribute[] attributes = new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("普通用户", "DEF"),
                new FormField.FormFieldAttribute("管理员", "ADMIN")
        };

        return FormField.putItemList(attributes);
    }

    protected ArrayList<FormField> createFormFields() {

        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("xf_user", "用户名", FormField.TEXTFIELD));
        formFields.add(new FormField("xf_password", "密码", FormField.TEXTFIELD));
        formFields.add(new FormField("xf_sex", "性别", FormField.RADIO_BUTTON + sexType()));
        formFields.add(new FormField("xf_phone", "手机号", FormField.TEXTFIELD));
        formFields.add(new FormField("xf_userGroup", "用户组", FormField.COMBOBOX + userGroupType()));
        return formFields;
    }
}
