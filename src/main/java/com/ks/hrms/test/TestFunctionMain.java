package com.ks.hrms.test;

import com.ks.hrms.core.app.ButtonType;
import com.ks.hrms.core.app.DefaultAppFunctionMain;
import com.ks.hrms.core.app.Item;
import com.ks.hrms.core.app.ToolBar;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.FreeForm;
import com.ks.hrms.core.component.form.TableForm;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.util.Callback;

import java.util.ArrayList;

public class TestFunctionMain extends DefaultAppFunctionMain<Test> {

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
        this.toolbar.setCustomButtons(createCustomButton(FontAwesomeIcon.SIGN_OUT,"测试自定义"));
        this.toolbar.setCustomButtonClickListener(id ->{
            System.out.println(id);
        });
    }

    @Override
    public void onClickButton(ButtonType type) {
        super.onClickButton(type);
    }

    @Override
    public void setItem(Item<Test> item) {
        super.setItem(item);
        Test test = item.toBean();
        freeForm.getField("userName").setValue(test.getUserName());
        freeForm.getField("sex").setValue(test.getSex());
        freeForm.getField("phone").setValue(test.getPhone());
        freeForm.getField("userGroup").setValue(test.getUserGroup());

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
        formFields.add(new FormField("userName", "用户名", FormField.TEXTFIELD));
        formFields.add(new FormField("sex", "性别", FormField.RADIO_BUTTON + sexType()));
        formFields.add(new FormField("phone", "手机号", FormField.TEXTFIELD));
        formFields.add(new FormField("userGroup", "用户组", FormField.COMBOBOX + userGroupType()));
        return formFields;
    }

    @Override
    public void onClose(Callback call) {
        super.onClose(call);
        System.out.println("被关闭了");
    }
}
