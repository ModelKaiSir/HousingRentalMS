package com.ks.hrms.core.app;

import javafx.scene.Node;

public class AppFunctionMainBase extends AppToolbarControlBase {

    public void addComponent(Node node){
        setCenter(node);
    }

    public void doClose(){

    }

    /**
     * 点击退出按钮 首先进行相关操作 再清除资源 然后通知管理者关闭
     */
    protected void clearApp(){

    }

    @Override
    public void close() {
        doClose();
        clearApp();
        super.close();
    }
}
