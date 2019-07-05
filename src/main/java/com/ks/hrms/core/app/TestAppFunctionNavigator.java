package com.ks.hrms.core.app;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.component.form.DataItemConverter;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class TestAppFunctionNavigator extends AppFunctionNavigator<TestBean> {

    @Override
    protected DataItemConverter<TestBean> getConverter() {
        return new DataItemConverter<TestBean>() {
            @Override
            public TestBean toBean(DataItem item) {
                return null;
            }

            @Override
            public DataItem toItem(TestBean testBean) {
                tableForm.newItem();
                return null;
            }
        };
    }

    @Override
    public ArrayList<FormField> getFormFields() {

        ArrayList<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField("USERNAME", "用户名", FormField.TEXTFIELD + FormField.putDefValue("Test")));
        formFields.add(new FormField("SEX", "性别", FormField.TEXTFIELD + FormField.putDefValue("Test")));
        formFields.add(new FormField("AGE", "年龄", FormField.TEXTFIELD + FormField.putDefValue("Test")));
        return formFields;

    }

}
