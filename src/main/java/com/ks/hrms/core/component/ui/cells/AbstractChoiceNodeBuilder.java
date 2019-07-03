package com.ks.hrms.core.component.ui.cells;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.component.ui.AbstractCustomParent;

public abstract class AbstractChoiceNodeBuilder<T> implements ChoiceNodeBuilder<DataItem,T> {

    protected FormField formField;

    public AbstractChoiceNodeBuilder(FormField formField) {
        this.formField = formField;
    }

    @Override
    public void validateValue() throws Exception {
        // Do nothing because there is no validation constraints
    }

    @Override
    public void setValue(T value) {
        //返回的数据不准确
        System.out.println("setValue "+value);
    }

    @Override
    public void cancelEdit() {
    }

}
