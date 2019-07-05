package com.ks.hrms.core.app;

import com.ks.hrms.core.component.bar.CustomToolbar;
import com.ks.hrms.core.component.bar.ToolbarControl;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class AppFunctionMain extends AppFunctionMainBase {

    protected SimpleObjectProperty<DataItem> item = new SimpleObjectProperty<>();

    public AppFunctionMain() {
    }

    @Override
    public void init(HRMSAppFunctionContext context){
        super.init(context);
    }

    public void newItem() {
    }

    public void setItem(DataItem item){
        this.item.set(item);
    }

}
