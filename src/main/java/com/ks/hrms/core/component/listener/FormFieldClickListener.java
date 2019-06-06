package com.ks.hrms.core.component.listener;

import javafx.scene.Parent;

public interface FormFieldClickListener {

    void click(ClickEvent event);

    class ClickEvent{
        Parent source;

    }
}
