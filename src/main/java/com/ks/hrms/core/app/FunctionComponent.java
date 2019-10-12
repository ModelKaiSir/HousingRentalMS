package com.ks.hrms.core.app;

import javax.xml.soap.Node;
import java.security.acl.Group;

public interface FunctionComponent {

    /**
     * 初始化方法
     */
    void init();

    void setCaption(String caption);

    void setFunctionRequestListener(FunctionRequestListener listener);

    void close();

    public static interface FunctionRequestListener {

        void request(ButtonType type);
    }
}
