package com.ks.hrms.core.component.form;

public interface DataForm<T> {

    void newItem();

    void setItem(T t);

    void update();

}
