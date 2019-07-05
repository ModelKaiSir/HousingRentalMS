package com.ks.hrms.core.app;

import com.ks.hrms.core.component.bar.CustomToolbar;
import com.ks.hrms.core.component.bar.ToolbarControl;
import com.ks.hrms.core.context.HRMSAppFunctionContext;

public class AppToolbarControlBase extends AppPageBase implements App,ToolbarControl {

    protected AppFunctionBase function;
    protected CustomToolbar toolbar;

    @Override
    public void setAppManager(AppFunctionBase appFunction) {
        this.function = appFunction;
    }

    @Override
    public void init(HRMSAppFunctionContext context) {
        this.toolbar = new CustomToolbar(this){
            @Override
            public int[] getToolbarIds() {
                int[] ids = AppToolbarControlBase.this.getToolbarIds();
                if(null== ids){
                    return super.getToolbarIds();
                }else{
                    return ids;
                }
            }
        };

        this.toolbar.init();
        setTop(toolbar);
    }

    @Override
    public void onClickButton(int id) {
        switch (id){
            case CustomToolbar.EXIT:
                close();
            break;
        }
    }

    public void close(){
        function.closePage(this);
    }

    public boolean update(){
        return true;
    }

    public int[] getToolbarIds(){
        return null;
    }

}

