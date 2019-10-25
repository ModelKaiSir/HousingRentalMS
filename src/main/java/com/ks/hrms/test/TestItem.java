package com.ks.hrms.test;

import com.ks.hrms.core.app.Item;

public class TestItem implements Item<Test> {

    private Test t;

    public TestItem(Test t) {
        this.t = t;
    }

    @Override
    public Test toBean() {
        return t;
    }

}
