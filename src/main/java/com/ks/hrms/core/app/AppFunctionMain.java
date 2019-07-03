package com.ks.hrms.core.app;

import com.ks.hrms.core.component.bar.CustomToolbar;
import com.ks.hrms.core.component.bar.ToolbarControl;
import com.ks.hrms.core.component.form.DataItem;
import com.ks.hrms.core.context.FunctionParamter;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AppFunctionMain extends BorderPane implements ToolbarControl,App,AppPage {

    private AppFunction function;

    private CustomToolbar toolbar;
    private SimpleStringProperty caption = new SimpleStringProperty();

    protected SimpleObjectProperty<DataItem> item = new SimpleObjectProperty<>();

    public AppFunctionMain() {

        toolbar = (CustomToolbar) new CustomToolbar(this).init();
        setTop(toolbar);

    }

    @Override
    public void setAppManager(AppFunction appFunction) {
        this.function = appFunction;
    }

    public void init(HRMSAppFunctionContext context, FunctionParamter functionParamter){

    }

    @Override
    public void onClickButton(int id) {
        close();
    }

    public void addComponent(Node node){
        setCenter(node);
    }

    @Override
    public AppPage getNext() {
        return null;
    }

    @Override
    public AppPage getLast() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasLast() {
        return false;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public void setPageIndex(int pageIndex) {

    }

    /**
     * 点击退出按钮 首先进行相关操作 再清除资源 然后通知管理者关闭
     */
    private void close(){
        update();
        clearApp();
        doClose();
        closed();
        //function.closePage(this);
    }

    public void doClose(){

    }

    public void closed(){

    }

    protected boolean update(){
        return true;
    }

    protected void clearApp(){

    }

    public void newItem(){

    }

    public void setItem(DataItem item){
        this.item.set(item);
    }

    public void setCaption(String caption) {
        this.caption.set(caption);
    }
}
