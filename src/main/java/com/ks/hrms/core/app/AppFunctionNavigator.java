package com.ks.hrms.core.app;

import com.ks.hrms.core.component.FormField;
import com.ks.hrms.core.component.bar.Toolbar;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.component.form.DataItemConverter;
import com.ks.hrms.core.component.form.TableForm;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.scene.control.TreeItem;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;

public abstract class AppFunctionNavigator<T> extends AppToolbarControlBase {

    protected TableForm tableForm;

    /**
     * init FormFields
     * @return
     */
    public abstract ArrayList<FormField> getFormFields();

    private DataItemConverter<T> converter;
    @Override
    public void init(HRMSAppFunctionContext context){
        super.init(context);
        tableForm = TableForm.createTableForm(getFormFields());
        //加载数据
        loadData();
        tableForm.setReadOnly(true);
        tableForm.setToolbarVisible(false);
        setCenter(tableForm);
    }

    /**
     * 转换器
     * @return
     */
    protected abstract DataItemConverter<T> getConverter();

    protected void loadData(T...tes){
        TreeItem<DataItem> root = tableForm.getTable().getRoot();
        for (T t : tes) {
            root.getChildren().add(new TreeItem<>(converter.toItem(t)));
        }
    }

    @Override
    public int[] getToolbarIds() {
        return new int[]{ Toolbar.EXIT,Toolbar.NEW,Toolbar.VIEW,Toolbar.MODIFY};
    }
}
