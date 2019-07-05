package com.ks.hrms.core.component.bar;

public interface Toolbar {

    public static final int EXIT = -1;
    public static final int NEW = 0;
    public static final int VIEW = 1;
    public static final int MODIFY = 2;

    Toolbar init();
    int[] getToolbarIds();
}
