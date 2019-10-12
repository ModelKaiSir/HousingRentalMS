package com.ks.hrms.core.component.form;

import com.ks.hrms.core.component.ui.AbstractCustomParent;

public interface Item<T> {

    /**
     * 根据Id获取值
     * @param id
     * @return
     */
    AbstractCustomParent getItemProperty(String id);

    /**
     * Item to Object
     * @return
     */
    T convertBean();
}
