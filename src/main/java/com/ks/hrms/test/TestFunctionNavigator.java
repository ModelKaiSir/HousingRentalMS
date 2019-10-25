package com.ks.hrms.test;

import com.ks.hrms.core.app.DefaultFunctionNavigator;
import com.ks.hrms.core.app.DefaultToolBar;
import com.ks.hrms.core.app.Item;
import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.TableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TestFunctionNavigator extends DefaultFunctionNavigator<Test> {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public ObservableList<Test> loadData() {
        return FXCollections.observableArrayList(
                new Test("张三","0","18972101425","DEF"),
                new Test("李四","1","16620078405","ADMIN")
        );
    }

    private String userGroupType() {

        FormField.FormFieldAttribute[] attributes = new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("普通用户", "DEF"),
                new FormField.FormFieldAttribute("管理员", "ADMIN")
        };

        return FormField.putItemList(attributes);
    }

    private String sexType() {
        FormField.FormFieldAttribute[] attributes = new FormField.FormFieldAttribute[]{
                new FormField.FormFieldAttribute("男", "0"),
                new FormField.FormFieldAttribute("女", "1")
        };

        return FormField.putItemList(attributes);
    }

    @Override
    public ArrayList<FormField> getFormFields() {
        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("userName", "用户名", FormField.TEXTFIELD));
        formFields.add(new FormField("sex", "性别", FormField.COMBOBOX + sexType()));
        formFields.add(new FormField("phone", "手机号", FormField.TEXTFIELD));
        formFields.add(new FormField("userGroup", "用户组", FormField.COMBOBOX + userGroupType()));
        return formFields;
    }

    @Override
    public Item<Test> getSelectItem() {
        return new TestItem(getTableForm().getTable().getSelectionModel().getSelectedItem());
    }
}
