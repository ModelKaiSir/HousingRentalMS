package com.ks.hrms.core.app;

import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import org.omg.CORBA.NVList;

/**
 * AppFunction是一个
 * @author QiuKai
 */
public class AppFunction extends Tab {

    private Pagination pagination;
    private boolean sign = true;

    public AppFunction() {
    }

    public void init(){
        HRMSAppFunctionContext context = HRMSAppFunctionContext.getInstance();
        AppFunctionNavigator navigator = createNavigator();
        AppFunctionMain main = createMain();

        sign = null == navigator;

        pagination = new Pagination(sign?1:2);
        pagination.setPageFactory(param -> {
            if(param == 0){
                if(sign){
                    main.setPageIndex(param);
                    return main;
                }else{
                    navigator.setPageIndex(param);
                    return navigator;
                }
            }else{
                main.setPageIndex(param);
                return main;
            }
        });

    }

    public AppFunctionMain createMain(){
        return null;
    }

    public AppFunctionNavigator createNavigator(){
        return null;
    }

    public void closePage(AppPage page){
        if(page.hasLast()){
            pagination.setCurrentPageIndex(page.getIndex());
        }else{
            close();
        }
    }

    private void close(){
        this.getOnCloseRequest().handle(new Event(Tab.TAB_CLOSE_REQUEST_EVENT));
        getTabPane().getTabs().remove(this);
        this.getOnClosed().handle(new Event(Tab.CLOSED_EVENT));
    }
}
