package com.ks.hrms.core.component.form;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.ks.hrms.core.component.FormFieldFactory;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataItem extends RecursiveTreeObject<DataItem> implements Item {

    private Map<String,AbstractCustomParent> columns = new HashMap<>();

    public DataItem(FormFieldFactory factory) {
        factory.getFormFields().forEach(i ->{
            AbstractCustomParent parent = factory.parseAttribute(i);
            columns.put(i.getId(),parent);
        });
    }

    @Override
    public AbstractCustomParent getItemProperty(String id) {
        System.out.println(columns.get(id));
       return columns.get(id);
    }
}
