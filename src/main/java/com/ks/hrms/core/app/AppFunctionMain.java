package com.ks.hrms.core.app;

import com.ks.hrms.core.component.bar.CustomToolbar;
import com.ks.hrms.core.component.bar.Toolbar;
import com.ks.hrms.core.component.bar.ToolbarControl;
import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class AppFunctionMain extends Tab implements ToolbarControl {

    private BorderPane rootPane;
    private CustomToolbar toolbar;
    private SimpleStringProperty caption = new SimpleStringProperty();

    public AppFunctionMain() {
        rootPane = new BorderPane();
        super.textProperty().bind(caption);
        toolbar = (CustomToolbar) new CustomToolbar(this).init();
        rootPane.setTop(toolbar);
        setContent(rootPane);

        setOnClosed(value ->{
            closed();
        });

        setOnCloseRequest(value ->{
            doClose();
        });
    }

    public void init(HRMSAppFunctionContext context){
    }

    public void setCaption(String caption) {
        this.caption.set(caption);
    }

    @Override
    public void onClickButton(int id) {
        System.out.println(id);
        close();
    }

    public void addComponent(Node node){
        rootPane.setCenter(node);
    }

    private void close(){
        this.getOnCloseRequest().handle(new Event(Tab.TAB_CLOSE_REQUEST_EVENT));
        getTabPane().getTabs().remove(this);
        this.getOnClosed().handle(new Event(Tab.CLOSED_EVENT));
    }

    public void doClose(){

    }

    public void closed(){

    }
}
