package com.ks.hrms.core.app;

public interface AppPage {

    AppPage getNext();

    AppPage getLast();

    boolean hasNext();

    boolean hasLast();

    int getIndex();

    void setPageIndex(int pageIndex);
}
