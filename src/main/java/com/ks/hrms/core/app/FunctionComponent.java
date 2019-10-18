package com.ks.hrms.core.app;

import javafx.util.Callback;

import javax.xml.soap.Node;
import java.security.acl.Group;

public interface FunctionComponent {

    /**
     * 初始化方法
     */
    void init();

    void close();

    void onClose(Callback call);

    void onOpen(Callback call);

    interface FunctionRequestListener{

        void request(FunctionComponent component, ButtonType type);
    }
}
