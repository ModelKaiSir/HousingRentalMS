package com.ks.hrms.core.component.form;

public interface DataItemConverter<T> {

    T toBean(DataItem item);

    DataItem toItem(T t);
}
