package com.ks.hrms.core;

import javafx.beans.property.SimpleStringProperty;

/**
 * 系统信息
 */
public interface SystemContext {

    SimpleStringProperty aboutProperty();
    SimpleStringProperty systemNameProperty();
    SimpleStringProperty verProperty();
}
